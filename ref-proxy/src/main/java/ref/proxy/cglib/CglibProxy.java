package ref.proxy.cglib;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

@Slf4j
public class CglibProxy implements MethodInterceptor {

	public Object newProxyObj(Class<?> clazz) {
		Enhancer enhancer = new Enhancer();
		enhancer.setSuperclass(clazz);
		enhancer.setCallback(this);
		return enhancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
		log.info("目标方法执行前");
		Object ret = proxy.invokeSuper(obj, args);
		log.info("目标方法执行后");
		return ret;
	}

}
