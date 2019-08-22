package demo.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="hello", urlPatterns="/*", loadOnStartup=1)
public class HelloServlet extends HttpServlet {

	@Override
	public void init() throws ServletException {
		// 初始化操作
		super.init();
	}

	@Override
	public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
		// ServletRequest封装请求信息，ServletResponse
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		response.getOutputStream().print("请求到这儿了");
	}

	@Override
	public void destroy() {
		// 容器销毁时执行
		super.destroy();
	}

}
