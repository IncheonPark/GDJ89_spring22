package com.winter.app.products;

import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.pages.Pager;

@Service
public class ProductService {
	
	@Autowired
	private ProductDAO productDAO;

	private static Long count=0L;
	
	//detail
	public ProductDTO getDetail(ProductDTO productDTO)throws Exception{

		return productDAO.getDetail(productDTO);
	}
	
	//list
	public List<ProductDTO> getList(Pager pager)throws Exception{
//		Pager pager = new Pager();
//		pager.setPage(page);
		
		
		pager.setKind("k1");
		
		
		Long totalCount = productDAO.getTotalCount(pager);
		
		
		pager.make(totalCount);
		
		pager.makeNum();
		List<ProductDTO> ar = productDAO.getList(pager);
		System.out.println("Service List");
		return ar;
	}
	
	//add
	public int add(ProductDTO productDTO)throws Exception{
		//dao 호출 코드
		
		return productDAO.add(productDTO);
		
	}
	
	//------------------ Comments ------------------
	public int addComments(CommentsDTO commentsDTO)throws Exception{
		return productDAO.addComments(commentsDTO);
	}
	
	public List<CommentsDTO> getCommentsList(CommentsDTO commentsDTO, Pager pager)throws Exception{
	
		pager.make(productDAO.getCommentsTotalCount(commentsDTO));
		
		pager.makeNum();
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("comments", commentsDTO);
		map.put("pager", pager);
		return productDAO.getCommentsList(map);
	}
	
	public int deleteComments(CommentsDTO commentsDTO)throws Exception{
		return productDAO.deleteComments(commentsDTO);
	}
	
	public int updateComments(CommentsDTO commentsDTO)throws Exception{
		return productDAO.updateComments(commentsDTO);
	}

}







