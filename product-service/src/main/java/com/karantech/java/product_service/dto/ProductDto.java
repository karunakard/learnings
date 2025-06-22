package com.karantech.java.product_service.dto;

public class ProductDto {

	private String pName;

	private String pDesc;

	private String pExpiry;
	
	public ProductDto() {
		
	}

	public ProductDto(String pName, String pDesc, String pExpiry) {
		super();

		this.pName = pName;
		this.pDesc = pDesc;
		this.pExpiry = pExpiry;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}

	public String getpDesc() {
		return pDesc;
	}

	public void setpDesc(String pDesc) {
		this.pDesc = pDesc;
	}

	public String getpExpiry() {
		return pExpiry;
	}

	public void setpExpiry(String pExpiry) {
		this.pExpiry = pExpiry;
	}

}
