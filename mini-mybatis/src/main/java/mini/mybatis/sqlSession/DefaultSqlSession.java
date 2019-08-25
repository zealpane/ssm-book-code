package mini.mybatis.sqlSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import mini.mybatis.config.Configuration;
import mini.mybatis.config.MappedStatement;
import mini.mybatis.executor.Executor;
import mini.mybatis.executor.SimpleExecutor;

public class DefaultSqlSession implements SqlSession {

private Configuration configuration;
	
	private Executor executor;
	
	
	public DefaultSqlSession(Configuration configuration) {
		super();
		this.configuration = configuration;
		executor = new SimpleExecutor(configuration);
	}

	@Override
	public <T> T selectOne(String statement,Object parameter) {
		List<Object> selectList = this.selectList(statement, parameter);
		if (selectList == null || selectList.isEmpty()) return null;
		return (T)selectList.get(0);//返回第一个
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter)  {
		MappedStatement ms = configuration.getMappedStatement(statement);
		try {
			return executor.query(ms, parameter);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public <T> T getMapper(Class<T> type) {
		return configuration.getMapper(type, this);
	}


}
