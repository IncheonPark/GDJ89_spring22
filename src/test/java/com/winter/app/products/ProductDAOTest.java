package com.winter.app.products;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.winter.app.SampleTestCase;

public class ProductDAOTest extends SampleTestCase {

	@Autowired
	private ProductDAO productDAO;
	
	@BeforeClass
	public static void bf() {
		System.out.println("전체 테스트 시작 전");
	}
	
	@AfterClass
	public static void af() {
		System.out.println("전체 테스트 종료 후");
	}
	
	@Before
	public void fe() {
		System.out.println("개별 테스트 실행 전");
	}
	
	@After
	public void ae() {
		System.out.println("개별 테스트 실행 후");
	}
	
	//@Test()
	public void addTest()throws Exception{
		ProductDTO productDTO = new ProductDTO();
		Calendar ca = Calendar.getInstance();
		
		
		for(int i=0;i<110;i++) {
		
			productDTO.setProductDate(new Date(ca.getTimeInMillis()));
			productDTO.setProductDetail("ProductDetail"+i);
			productDTO.setProductName("ProductName"+i);
			
			double r = Math.random();//0.0 - 1.0
			
			r = r*100;
			
			int ri = (int)r;
			
			r = ri/100.0;
			
			productDTO.setProductRate(r);
			
			productDAO.add(productDTO);
			
			if(i%10==0) {
				Thread.sleep(500);
			}
			
			System.out.println("Finish");
			
		}
		
		
	}
	
	//@Test
	public void getDetailTest()throws Exception{
		System.out.println("GetDetail Test");
		ProductDTO productDTO = new ProductDTO();
		productDTO.setProductNum(5000L);
		
		productDTO = productDAO.getDetail(productDTO);
		
		assertNull(productDTO);
		
	}
	
	@Test
	public void addCommentsTest()throws Exception{
		CommentsDTO commentsDTO = new CommentsDTO();
		commentsDTO.setBoardContents("test");
		commentsDTO.setUserName("id");
		commentsDTO.setProductNum(100L);
		int result = productDAO.addComments(commentsDTO);
		
		assertEquals(1, result);
	}
	


}











