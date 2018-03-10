package com.ewei.chat.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoginInterceptor implements HandlerInterceptor {
	// 不拦截login、logout
	private static final String[] IGNORE_URL = { "/login", "/logout" };

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handle) throws Exception {
		boolean flag = false;
		String servletPath = request.getServletPath();
		// 判断是否进行拦截
		for (String s : IGNORE_URL) {
			if (servletPath.contains(s)) {
				flag = true;
				break;
			}
		}

		// 拦截请求
		if (!flag) {
			HttpSession httpSession = request.getSession();
			if(httpSession.getAttribute("userid") == null) {
				//如果用户没登录
				request.setAttribute("message", "请先登陆再访问网站");
				request.getRequestDispatcher("login").forward(request, response);
			}
			else {
				flag = true;
			}
		}
		return flag;
	}

}
