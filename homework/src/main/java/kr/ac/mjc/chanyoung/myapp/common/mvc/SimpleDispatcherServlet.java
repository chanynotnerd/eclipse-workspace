package kr.ac.mjc.chanyoung.myapp.common.mvc;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * URL에 따라 매핑되어 있는 메서드를 실행
 */
public class SimpleDispatcherServlet extends HttpServlet {

	Map<String, MethodInfo> invokeMap = null;

	/**
	 * 메서드 실행에 대한 정보. invokeMap의 value에 들어간다.
	 */

	@Override
	@SuppressWarnings("unchecked")
	public void init(ServletConfig config) throws ServletException {
		this.invokeMap = (Map<String, MethodInfo>) config.getServletContext()
				.getAttribute("invokeMap");
	}

	@Override
	public void service(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		MethodInfo methodInfo = invokeMap.get(pathInfo);

		// 매핑된 메서드가 없으면 화면으로
		if (methodInfo == null) {
			request.getRequestDispatcher("/WEB-INF/jsp" + pathInfo + ".jsp")
					.forward(request, response);
			return;
		}

		// request method가 다르면 405
		if (!request.getMethod().equals(methodInfo.getRequestMethod().name())) {
			response.sendError(405);
			return;
		}

		try {
			// 매핑된 메서드 실행
			methodInfo.getMethod().invoke(methodInfo.getController(), request,
					response);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e.getCause());
		}
	}
}