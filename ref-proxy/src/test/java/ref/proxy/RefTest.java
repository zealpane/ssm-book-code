package ref.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.junit.Test;

import ref.proxy.ref.User;

public class RefTest {

	@Test
	public void reflectTest() throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 正常调用
		User user = new User();
		user.setName("盘古");
		user.setAge(Integer.MAX_VALUE);
		StringBuilder sBuilder = new StringBuilder();
		sBuilder.append(user.getName()).append("的年龄").append(user.getAge());
		System.out.println(sBuilder);
		
		// 反射
		Class clazz = Class.forName("ref.proxy.ref.User");
		Method setAgeMethod = clazz.getMethod("setAge", Integer.class);
		
		Constructor userConstructor = clazz.getConstructor();
		Object userObj = userConstructor.newInstance();
		setAgeMethod.invoke(userObj, 99999);
		Method getAgeMethod = clazz.getMethod("getAge");
		System.out.println("年龄" + getAgeMethod.invoke(userObj));
	}
}
