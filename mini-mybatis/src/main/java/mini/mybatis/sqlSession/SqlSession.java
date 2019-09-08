package mini.mybatis.sqlSession;

import java.util.List;

public interface SqlSession {
	// 查询单条记录
	<T> T selectOne(String statement, Object parameter);
	// 查询多条记录
	<E> List<E> selectList(String statement, Object parameter);
	// 根据动态代理获取mapper
	<T> T getMapper(Class<T> type);
}