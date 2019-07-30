package ref.proxy;

import org.junit.Test;

import ref.proxy.cglib.CglibProxy;
import ref.proxy.cglib.UserServiceImpl;

public class CglibTest {

	@Test
	public void test() {
		CglibProxy cglibProxy = new CglibProxy();
		UserServiceImpl p = (UserServiceImpl) cglibProxy.newProxyObj(UserServiceImpl.class);
		p.sayHi();
		System.out.println();
		p.sayHello();
	}
}
