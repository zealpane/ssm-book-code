package com.demo;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

public class AppTomcat {
	public static void main(String[] args) throws ServletException, LifecycleException {
		//使用java内置tomcat运行springMVC框架
		//原理：tomcat加载到springmvc注解启动方式，就会创建springmvc容器
		start();
	}
	public static void start() throws ServletException, LifecycleException {
		//创建Tomcat服务器
		//创建Tomcat服务器
		Tomcat tomcatServer=new Tomcat();
		//指定端口号
		tomcatServer.setPort(9090);
		//读取项目路径
		StandardContext ctx=(StandardContext) tomcatServer.addWebapp("/", new File("src/main").getAbsolutePath());
		//禁止重新载入
		ctx.setReloadable(false);
		//class文件读取地址
		File additionWebInfClasses=new File("target/classes");
		//创建webroot
		WebResourceRoot resources=new StandardRoot(ctx);
		//tomcat内部读取class执行
		resources.addPreResources(new DirResourceSet(resources,"/WEB-INF/classes",additionWebInfClasses.getAbsolutePath(),"/"));
		tomcatServer.start();
		//异步等待请求执行
		tomcatServer.getServer().await();
	}
}