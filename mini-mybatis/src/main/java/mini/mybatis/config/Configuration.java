package mini.mybatis.config;

import java.util.HashMap;
import java.util.Map;

import lombok.Data;
import mini.mybatis.proxy.MapperProxyFactory;
import mini.mybatis.sqlSession.SqlSession;

@Data
public class Configuration {

	private String jdbcDriver;
	private String jdbcUrl;
	private String username;
	private String password;

	// 用于存放解析的mapper中的sql操作语句
	private final Map<String, MappedStatement> mappedStatements = new HashMap<String, MappedStatement>();

	public MappedStatement getMappedStatement(String key) {
		return mappedStatements.get(key);
	}
	
	public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
		return MapperProxyFactory.getMapperProxy(sqlSession, type);
	}
}
