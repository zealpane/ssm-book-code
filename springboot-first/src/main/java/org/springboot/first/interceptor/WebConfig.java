package org.springboot.first.interceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
/**
 * webmvc配置类
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
		
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new LogInterceptor())
				.addPathPatterns("/**");
	}
}