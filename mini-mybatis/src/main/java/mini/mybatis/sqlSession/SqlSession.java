package mini.mybatis.sqlSession;

import java.io.Closeable;
import java.util.List;

import mini.mybatis.RowBounds;

public interface SqlSession {

	<T> T selectOne(String statement, Object parameter);

	<E> List<E> selectList(String statement, Object parameter);

	<T> T getMapper(Class<T> type);
}