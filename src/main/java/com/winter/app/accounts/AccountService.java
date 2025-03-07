package com.winter.app.accounts;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.winter.app.users.UserDAO;
import com.winter.app.users.UserDTO;

@Service
public class AccountService {
	
	@Autowired
	private AccountDAO accountDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	public int add(Long [] nums, UserDTO userDTO)throws Exception{
		List<AccountDTO> list = new ArrayList<AccountDTO>();
		
		for(Long l : nums) {
			AccountDTO accountDTO = new AccountDTO();
			accountDTO.setProductNum(l);
			accountDTO.setUserName(userDTO.getUserName());
			Calendar ca = Calendar.getInstance();
			accountDTO.setAccountNum(String.valueOf(ca.getTimeInMillis()));
			list.add(accountDTO);
			Thread.sleep(10);
		}
		int result = accountDAO.add(list);
		
		if(result>0) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("user", userDTO);
			map.put("products", nums);
			userDAO.cartDelete(map);
		}
		
		
		return result;
	}

}
