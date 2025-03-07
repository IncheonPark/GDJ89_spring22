package com.winter.app.accounts;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountDAO {
	
	@Autowired
	private SqlSession sqlSession;
	
	private final String NAMESPACE="com.winter.app.accounts.AccountDAO.";
	
	public int add(List<AccountDTO> list)throws Exception{
		//productNum, List<Long>
		//productNum, Long []
		return sqlSession.insert(NAMESPACE+"add", list);
	}

}
