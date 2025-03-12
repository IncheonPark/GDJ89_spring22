package com.winter.app.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winter.app.boards.CommentDTO;
import com.winter.app.pages.Pager;

@Repository
public class ProductDAO {
	
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.winter.app.products.ProductDAO.";
	
	//insert
	public int add(ProductDTO productDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"add", productDTO);
		
	}
	
	//detail
	public ProductDTO getDetail(ProductDTO productDTO)throws Exception{
		//statement => mapper의 id값
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("pn", pn);
//		map.put("name", name);
		
		return sqlSession.selectOne(NAMESPACE+"getDetail", productDTO);
	}
	
	public Long getTotalCount(Pager pager)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getTotalCount", pager);
	}
	
	//list
	public List<ProductDTO> getList(Pager pager)throws Exception{
			
		return sqlSession.selectList(NAMESPACE+"getList", pager);
	}
	
	
	public Long test()throws Exception{
		return sqlSession.selectOne(NAMESPACE+ "test");
	}
	
	public Map<String, Object> test2()throws Exception{
		return sqlSession.selectOne(NAMESPACE+"test2");
	}
	
	//----------------------- Comments ----------------
	public int addComments(CommentsDTO commentsDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"addComments", commentsDTO);
	}
	
	public List<CommentsDTO> getCommentsList(Map<String, Object> map)throws Exception{
		return sqlSession.selectList(NAMESPACE+"getCommentsList", map);
	}
	
	public Long getCommentsTotalCount(CommentsDTO commentsDTO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getCommentsTotalCount", commentsDTO);
	}
	
	public int deleteComments(CommentsDTO commentsDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+"deleteComments", commentsDTO);
	}
	
	public int updateComments(CommentsDTO commentsDTO)throws Exception{
		return sqlSession.delete(NAMESPACE+"updateComments", commentsDTO);
	}
	
	
	
	
	
	
	
	
	
	
	
	

}
