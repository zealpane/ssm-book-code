package com.gdatacloud.zz.ssm;

import java.io.File;

import javax.servlet.ServletException;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.AprLifecycleListener;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;

/**
 * Hello world!
 */
public class App {

    public static int TOMCAT_PORT = 8080;
    public static String TOMCAT_HOSTNAME = "127.0.0.1";
    public static String WEBAPP_PATH = "src/main";
    public static String WEBINF_CLASSES = "/WEB-INF/classes";
    public static String CLASS_PATH = "target/classes";
    public static String INTERNAL_PATH = "/";

    public static void main(String[] args) throws LifecycleException, ServletException {
        System.out.println("Hello World!");
        //使用java内置tomcat运行MVC框架
        start();
    }

    public static void start() throws ServletException, LifecycleException {
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(TOMCAT_PORT);
        tomcat.setHostname(TOMCAT_HOSTNAME);
        tomcat.setBaseDir("."); // tomcat 信息保存在项目下

        /*
         * https://www.cnblogs.com/ChenD/p/10061008.html
         */
        StandardContext myCtx = (StandardContext) tomcat
                .addWebapp("/access", System.getProperty("user.dir") + File.separator + WEBAPP_PATH);
        /*
         * true时：相关classes | jar 修改时，会重新加载资源，不过资源消耗很大
         * autoDeploy 与这个很相似，tomcat自带的热部署不是特别可靠，效率也不高。生产环境不建议开启。
         * 相关文档：
         * http://www.blogjava.net/wangxinsh55/archive/2011/05/31/351449.html
         */
        myCtx.setReloadable(false);
        // 上下文监听器
        myCtx.addLifecycleListener(new AprLifecycleListener());

        // 注册servlet
        tomcat.addServlet("/access", "demoServlet", new DispatcherServlet());
        // servlet mapping
        myCtx.addServletMappingDecoded("/*", "demoServlet");
        tomcat.start();
        tomcat.getServer().await();
    }

}
