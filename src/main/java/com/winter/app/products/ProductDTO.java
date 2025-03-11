package com.winter.app.products;

import java.sql.Date;

public class ProductDTO {
	
	//모든 멤버변수의 접근지정자는 private
	//변수명, 타입은 DB의 컬럼과 동일하게
	//Setter, Getter
	//생성자는 최소 기본생성자가 있어야 함
	private Long productNum;
	private String productName;
	private Double productRate;
	private Date productDate;
	private String productDetail;
	
	
	public Long getProductNum() {
		return productNum;
	}
	public void setProductNum(Long productNum) {
		this.productNum = productNum;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public Double getProductRate() {
		return productRate;
	}
	public void setProductRate(Double productRate) {
		this.productRate = productRate;
	}
	public Date getProductDate() {
		return productDate;
	}
	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String productDetail) {
		this.productDetail = productDetail;
	}
	
	

}
