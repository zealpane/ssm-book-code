package mini.mybatis;

import java.io.IOException;
import java.util.List;

public class DefaultSqlSession implements SqlSession {

	private final Configuration configuration;
	private final Executor executor;

	public DefaultSqlSession(Configuration configuration, Executor executor) {
		this.configuration = configuration;
		this.executor = executor;
	}

	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T selectOne(String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T selectOne(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <E> List<E> selectList(String statement, Object parameter, RowBounds rowBounds) {
		// TODO Auto-generated method stub
		return null;
	}

}
