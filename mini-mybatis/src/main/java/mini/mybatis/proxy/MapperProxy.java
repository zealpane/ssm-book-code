package mini.mybatis.proxy;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

import mini.mybatis.sqlSession.SqlSession;

/**
 * Mapper接口的代理类
 */
public class MapperProxy<T> implements InvocationHandler{

	private SqlSession sqlSession;
	
	private final Class<T> mapperInterface;
	
	public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface) {
		super();
		this.sqlSession = sqlSession;
		this.mapperInterface = mapperInterface;//被代理的对象
	}

	private <T> boolean isCollection(Class<T> type) {
		return Collection.class.isAssignableFrom(type);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		Object result = null;
		//Object类的方法，不处理
		if (Object.class.equals(method.getDeclaringClass())) {
			return method.invoke(this, args);
		}
		Class<?> returnType = method.getReturnType();
		if (isCollection(returnType)) {
			result = sqlSession.selectList(mapperInterface.getName()+"."+method.getName(), args);
		}else {
			result = sqlSession.selectOne(mapperInterface.getName()+"."+method.getName(), args);
		}
		return result;
	}
	
}