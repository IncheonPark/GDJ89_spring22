package com.winter.app.products;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.winter.app.boards.CommentDTO;
import com.winter.app.pages.Pager;
import com.winter.app.users.UserDTO;

@Controller
@RequestMapping(value = "/products/*")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	/**
	 * Model -> 
	 * requestScope 와 라이프사이클이 비슷
	 * 응답이 발생하면 소멸
	 * request와 비슷한 일을 함
	 * java -> jsp로 데이터를 전달 할 때 사용
	 * */
	
	@RequestMapping(value = "list", method = RequestMethod.GET)
	public void getList(Model model, Pager pager)throws Exception{
		System.out.println("Product List");
		List<ProductDTO> ar = productService.getList(pager);
		
		model.addAttribute("pager", pager);
		model.addAttribute("list", ar);
		
	}

	@RequestMapping(value = "detail", method = RequestMethod.GET)
	public ModelAndView getDetail(ProductDTO productDTO)throws Exception{
		System.out.println("Product Detail");
		
		productDTO = productService.getDetail(productDTO);
		
		ModelAndView mv = new ModelAndView();
		//model
		mv.addObject("dto", productDTO);
		//view
		mv.setViewName("products/detail");
		return mv;
	}
	
	@RequestMapping(value = "add", method = RequestMethod.GET)
	public ModelAndView add(ModelAndView mv)throws Exception{
		return mv;
	}

	@RequestMapping(value = "add", method = RequestMethod.POST)
	public ModelAndView add(ProductDTO productDTO)throws Exception{
		/**
		 * 파라미터 처리 방법
		 * 1.모든 요청 정보는 Request에 있다.(URL, METHOD, PARAMETER, COOKIE...)
		 *  메서드의 매개변수로 HttpServletRequest request 선언 
		 *  request.getParameter("")
		 *  
		 * 2.매개변수로 파라미터이름과 동일한 변수명, 동일한 타입명으로 선언
		 * 
		 * 3.매개변수로 Bean(DTO)를 선언
		 *   파라미터의 이름과 타입이 DTO의 Setter의 이름과 동일   
		 * */
		
		System.out.println(productDTO.getProductDate().toString());
		
		int result = productService.add(productDTO);
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:./list");
		return mv;
	}
	
	@RequestMapping(value = "update", method = RequestMethod.GET)
	public void update(ProductDTO productDTO, Model model)throws Exception{
		productDTO = productService.getDetail(productDTO);
		model.addAttribute("dto", productDTO);
	}
	
	@RequestMapping(value = "delete", method = RequestMethod.POST)
	public void delete(ProductDTO productDTO, Model model)throws Exception{
		productDTO = productService.getDetail(productDTO);
		model.addAttribute("dto", productDTO);
	}
	
	//------------------ Comments ------------------------
	//addComments
	@RequestMapping(value = "addComments", method = RequestMethod.POST)
	public void addComments(CommentsDTO commentsDTO, HttpSession session)throws Exception{
		
		
		UserDTO userDTO = (UserDTO)session.getAttribute("user");
		commentsDTO.setUserName(userDTO.getUserName());
		
		int result = productService.addComments(commentsDTO);
	}
	
	


}
