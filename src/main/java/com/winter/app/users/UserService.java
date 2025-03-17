package com.winter.app.users;

import java.io.File;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.winter.app.files.FileManger;
import com.winter.app.pages.Pager;
import com.winter.app.products.ProductDTO;

@Service
public class UserService {
	
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private FileManger fileManger;
	
	
	public int join(UserDTO userDTO, MultipartFile profile, ServletContext context)throws Exception{
		int result = userDAO.join(userDTO);
		
		if(profile.isEmpty()) {
			return result;
		}
		
		//1. 어디에 저장 할 것인가??
		UserFileDTO userFileDTO = this.save(context, profile, userDTO);
		result = userDAO.upload(userFileDTO);
		
		return result;
	}
	
	public UserDTO check(UserDTO userDTO) throws Exception{
		return userDAO.getDetail(userDTO);
	}
	
	public UserDTO login(UserDTO userDTO)throws Exception{
		//result는 username(id) 만 비교 함
		UserDTO result = userDAO.getDetail(userDTO);
		
		if(result != null) {
			if(result.getPassword().equals(userDTO.getPassword())) {
				return result;
			}
			
			throw new UsernameException("비번 틀렸다");
			
		}
		
		if(result ==null) {
			throw new UsernameException("아이디가 틀렸다");
		}
		
		return null;
		
	}
	
	public int update(UserDTO userDTO, MultipartFile profile, HttpSession session)throws Exception{
		//dao user정보를 update
		int result = userDAO.update(userDTO);
		
		if(!profile.isEmpty()) {
			UserFileDTO userFileDTO = this.save(session.getServletContext(), profile, userDTO);
			//update 후에 결과값이 0이면 insert 시도
			int r = userDAO.updateFile(userFileDTO);
			if(r<1) {
				result = userDAO.upload(userFileDTO);
			}
		}
		
		
		
		
		userDTO= userDAO.getDetail(userDTO);
		session.setAttribute("user", userDTO);
		
		return result;
		
	}
	
	public int addCart(Map<String, Object> map)throws Exception{
		return userDAO.addCart(map);
	}
	
	public List<ProductDTO> getCartList(Pager pager, Object userDTO)throws Exception{
		
		pager.make(userDAO.getCartTotalCount(userDTO));
		
		pager.makeNum();
		Map<String, Object> map =new HashMap<String, Object>();
		map.put("pager", pager);
		map.put("user", userDTO);
		return userDAO.getCartList(map);
	}
	
	
	
	
	
	
	
	private UserFileDTO save(ServletContext context, MultipartFile profile, UserDTO userDTO)throws Exception{
		String path = context.getRealPath("/resources/images/profiles/");
		System.out.println(path);
		
		String f = fileManger.fileSave(path, profile);

		
		
		UserFileDTO userFileDTO = new UserFileDTO();
		userFileDTO.setUserName(userDTO.getUserName());
		userFileDTO.setFileName(f);
		userFileDTO.setOldName(profile.getOriginalFilename());
		
		return userFileDTO;
	}
	
	public int cartDelete(Map<String, Object> map)throws Exception{
		return userDAO.cartDelete(map);
	}
	
	
	
	
	
	
	
	
	
	

}
