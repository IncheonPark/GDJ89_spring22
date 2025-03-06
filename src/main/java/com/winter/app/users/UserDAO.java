package com.winter.app.users;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.winter.app.products.ProductDTO;

@Repository
public class UserDAO {
	@Autowired
	private SqlSession sqlSession;
	private final String NAMESPACE="com.winter.app.users.UserDAO.";
	
	public int upload(UserFileDTO userFileDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"upload", userFileDTO);
				
	}
	
	public int join(UserDTO userDTO)throws Exception{
		return sqlSession.insert(NAMESPACE+"join", userDTO);
				
	}
	
	//user 한명의 정보를 조회,(username)
	public UserDTO getDetail(UserDTO userDTO)throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getDetail", userDTO);
	}
	
	public int update(UserDTO userDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"update", userDTO);
	}
	
	public int updateFile(UserFileDTO userFileDTO)throws Exception{
		return sqlSession.update(NAMESPACE+"updateFile", userFileDTO);
	}
	
	public int addCart(Map<String, Object> map)throws Exception{
		return sqlSession.insert(NAMESPACE+"addCart", map);
	}
	
	public Long getCartTotalCount(Object userDTO) throws Exception{
		return sqlSession.selectOne(NAMESPACE+"getCartTotalCount", userDTO);
	}
	
	public List<ProductDTO> getCartList(Map<String, Object> map)throws Exception{
		return sqlSession.selectList(NAMESPACE+"getCartList", map);
	}
	
	public int cartDelete(Map<String, Object> map)throws Exception{
		return sqlSession.delete(NAMESPACE+"cartDelete", map);
	}

}







