package com.winter.app.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.winter.app.boards.BoardDTO;
import com.winter.app.boards.notice.NoticeDAO;
import com.winter.app.products.ProductDTO;
import com.winter.app.users.UserDTO;

public class UserNameCheckInteceptor extends HandlerInterceptorAdapter{
	
	
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		UserDTO userDTO = (UserDTO)request.getSession().getAttribute("user");
		
		
		String kind = (String)modelAndView.getModel().get("kind");
		
		Object objDTO = modelAndView.getModel().get("dto");
		
		String name="";
		
		if(objDTO instanceof BoardDTO) {
			name =  ((BoardDTO) objDTO).getUserName();
		}
		
		if(!userDTO.getUserName().equals(name)) {
			modelAndView.setViewName("commons/result");
			modelAndView.getModel().put("result", "작성가 아니다");
			modelAndView.getModel().put("path", "./list");
		}
	}

}
