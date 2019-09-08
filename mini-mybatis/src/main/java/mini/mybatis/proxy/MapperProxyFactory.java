package mini.mybatis.proxy;

import java.lang.reflect.Proxy;

import mini.mybatis.sqlSession.SqlSession;

/**
 * 动态代理类的生产工厂
 */
public class MapperProxyFactory {

	public static <T> T getMapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		MapperProxy<T> mapperProxy = new MapperProxy<>(sqlSession, mapperInterface);
		return (T) Proxy.newProxyInstance(mapperInterface.getClassLoader(), new Class[] { mapperInterface },
				mapperProxy);
	}
}