package com.winter.app.interceptors;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

@Component
public class LoginCheckInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//DS -> Controller
		
		Object user = request.getSession().getAttribute("user");
		
		
		
		if(user != null) {
			return true;
		}
		
		//1.redirect
		//response.sendRedirect("/users/login");
		//2. foward
		request.setAttribute("result", "로그인 해");
		request.setAttribute("path", "/users/login");
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/views/commons/result.jsp");
		view.forward(request, response);
		
		//return 이 true라면 Controller 로 진행
		//return 이 false라면 Controller 로 진행 X
		return false;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		
		//Controller -> DS
		System.out.println("postHandle");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		//html로 렌더링 후
		System.out.println("afterCompletion");
	}
	
	

}
