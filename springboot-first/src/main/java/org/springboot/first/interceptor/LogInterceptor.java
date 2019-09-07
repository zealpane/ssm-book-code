package org.springboot.first.interceptor;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

public class LogInterceptor implements HandlerInterceptor {

	/**
	 * 首先定义日期时间格式化
	 */
	private static final ThreadLocal<SimpleDateFormat> sdf = new ThreadLocal<SimpleDateFormat>() {
		protected SimpleDateFormat initialValue() {
			return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		}
	};
	
	/**
	 * 前置处理。处理完后返回true表示可以往后继续执行
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		StringBuilder sb = new StringBuilder("\nUser ").append(" act log").append(" -------- ");
		sb.append(sdf.get().format(new Date())).append(" --------------------------\n");
		if (handler instanceof ResourceHttpRequestHandler) {
			return true;
		}
		HandlerMethod h = (HandlerMethod) handler;
		String clazz = h.getBeanType().getName();
		Method method = h.getMethod();
		Class<?> cc = h.getBeanType();
		sb.append("Controller  : ").append(cc.getName()).append(".(").append(cc.getSimpleName()).append(".java:1) \n");
		sb.append(request.getMethod())
			.append("         : ")
			.append(request.getRequestURI())
			.append(" -------- ")
			.append(method.getName())
			.append("---------------\n");
		
		String urlParas = request.getQueryString();
		if (urlParas != null) {
			sb.append("UrlPara     : ").append(urlParas).append("\n");
		}
		System.out.println(sb);
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
	}
}