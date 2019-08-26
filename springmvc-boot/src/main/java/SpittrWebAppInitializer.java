import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpittrWebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{
	//加载根配置信息  spring核心
	protected Class<?>[] getRootConfigClasses() {
		return new Class[] {RootConfig.class};
	}
	//springmvc加载配置信息
	protected Class<?>[] getServletConfigClasses() {
		return new Class[] {WebConfig.class};
	}
	//springmvc拦截url映射
	protected String[] getServletMappings() {
		return new String[] {"/"};//拦截所有请求
	}	
}
