package ref.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DeviceProxy implements InvocationHandler {

	// 代理的目标对象
	private Object targetObj;
	
	public Object newProxy(Object targetObj) {
		this.targetObj = targetObj;
		return Proxy.newProxyInstance(targetObj.getClass().getClassLoader(), targetObj.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		log.debug("——————目标方法执行前——————");
		Object ret = method.invoke(targetObj, args);
		log.debug("——————目标方法执行后——————");
		return ret;
	}

}
