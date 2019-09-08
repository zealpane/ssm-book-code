package com.gdatacloud.zz.ssm;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gdatacloud.zz.ssm.annotation.Autowired;
import com.gdatacloud.zz.ssm.annotation.Controller;
import com.gdatacloud.zz.ssm.annotation.RequestMapping;
import com.gdatacloud.zz.ssm.annotation.Service;
import com.gdatacloud.zz.ssm.utils.StrKit;

@WebServlet(urlPatterns = "/*", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {

	// 类名列表
	private ArrayList<String> classNameList = new ArrayList<>();

	private Map<String, Object> ioc = new HashMap<>();
	private Map<String, Method> handlerMapping = new HashMap<>();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 实际是在service方法中调用get、post等方法
		if (handlerMapping.isEmpty()) {
			return;
		}
		String uri = req.getRequestURI();
		String contextPath = req.getContextPath();

		// 拼接url并把多个/替换为1个
		uri = uri.replace(contextPath, "").replaceAll("/+", "/");

		if (!this.handlerMapping.containsKey(uri)) {
			resp.getWriter().write("404");
			return;
		}

		Method method = this.handlerMapping.get(uri);

		// 获取方法的参数列表
		Class<?>[] parameterTypes = method.getParameterTypes();
		// 获取请求的参数
		Map<String, String[]> parameterMap = req.getParameterMap();
		// 保存参数值
		Object[] paramValues = new Object[parameterTypes.length];

		for (int i = 0; i < parameterTypes.length; i++) {
			// 根据参数类型字符串
			String requestParam = parameterTypes[i].getSimpleName();
			if ("HttpServletRequest".equals(requestParam)) {
				paramValues[i] = req;
				continue;
			}
			if ("HttpServletResponse".equals(requestParam)) {
				paramValues[i] = resp;
				continue;
			}
			if ("String".equals(requestParam)) {
				for (Entry<String, String[]> param : parameterMap.entrySet()) {
					String value = Arrays.toString(param.getValue()).replaceAll("\\[|\\]", "");
					paramValues[i] = value;
					continue;
				}
			}
		}
		try {
			method.invoke(ioc.get(uri), paramValues);
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 扫描包，反射初始化类，初始化HandlerMapping、实现注入
	 */
	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		// 根据当前类所在包为基础包名
		String packageName = this.getClass().getPackage().getName();
		// 1、扫描包
		doScanPackage(packageName);
		try {
			// 2、初始化类
			doInstance();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		}
		// 3、初始化handlerMapping
		doInitHandlerMapping();
		// 4、依赖注入
		doDI();
	}

	private void doDI() {
		if (ioc.isEmpty()) {
			return;
		}
		for (Entry<String, Object> entry : ioc.entrySet()) {
			Field[] fields = entry.getValue().getClass().getDeclaredFields();
			for (Field field : fields) {
				field.setAccessible(true);
				if (field.isAnnotationPresent(Autowired.class)) {
					Autowired rAnno = field.getAnnotation(Autowired.class);
					String value = rAnno.value();
					field.setAccessible(true);

					String key;
					if (value != null && !"".equals(value)) {
						key = value;
					} else {
						key = field.getName();
					}

					try {
						field.set(entry.getValue(), ioc.get(key));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}

	}

	private void doInitHandlerMapping() {
		if (ioc.isEmpty()) {
			return;
		}
		try {
			Map<String, Object> url_method = new HashMap<>();
			for (Map.Entry<String, Object> entry : ioc.entrySet()) {
				Class<? extends Object> clazz = entry.getValue().getClass();
				if (!clazz.isAnnotationPresent(Controller.class)) {
					continue;
				}

				// 拼url时，是controller上配置的和method上的合并
				String baseUrl = "";
				if (clazz.isAnnotationPresent(RequestMapping.class)) {
					RequestMapping cAnnotation = clazz.getAnnotation(RequestMapping.class);
					baseUrl = cAnnotation.value();
				}

				Method[] methods = clazz.getMethods();
				for (Method method : methods) {
					if (!method.isAnnotationPresent(RequestMapping.class)) {
						continue;
					}
					RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
					String url = methodAnnotation.value();

					url = (baseUrl + "/" + url).replaceAll("/+", "/");

					handlerMapping.put(url, method);
					url_method.put(url, clazz.newInstance());
				}
			}
			ioc.putAll(url_method);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void doInstance() throws IllegalAccessException, InstantiationException {
		if (classNameList == null || classNameList.size() <= 0) {
			return;
		}
		for (String className : classNameList) {
			try {
				Class<?> clazz = Class.forName(className);
				if (clazz.isAnnotationPresent(Controller.class)) {
					Controller controller = clazz.getAnnotation(Controller.class);
					String key = controller.value();
					// 如果key不是空，那么就以key为键存储
					if (key != null && !"".equals(key)) {
						ioc.put(key, clazz.newInstance());
					} else {
						// 否则就把首字母小写，再存储
						ioc.put(StrKit.lowerFirst(clazz.getSimpleName()), clazz.newInstance());
					}

				} else if (clazz.isAnnotationPresent(Service.class)) {
					Service service = clazz.getAnnotation(Service.class);
					String key = service.value();
					// 如果key不是空，那么就以key为键存储
					if (key != null && !"".equals(key)) {
						ioc.put(key, clazz.newInstance());
					} else {
						// 否则就把首字母小写，再存储
						ioc.put(StrKit.lowerFirst(clazz.getSimpleName()), clazz.newInstance());
					}
				} else {
					continue;
				}

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}

	// 递归扫描包及子包下面的类
	private void doScanPackage(String packageName) {
		String path = "" + packageName.replaceAll("\\.", "/");
		URL url = this.getClass().getClassLoader().getResource(path);
		File dir = new File(url.getFile());
		for (File f : dir.listFiles()) {
			if (f.isDirectory()) {
				doScanPackage(packageName + "." + f.getName());
			} else {
				String className = packageName + "." + f.getName().replace(".class", "");
				classNameList.add(className);
				System.out.println("实例化" + className);
			}
		}
	}

}
