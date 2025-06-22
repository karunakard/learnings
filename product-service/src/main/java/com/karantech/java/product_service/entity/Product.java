package com.karantech.java.product_service.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="PROD_ID")
	private int productId;
	
	@Column(name="PROD_NM")
	private String pName;
	
	@Column(name="PROD_DESC")
	private String pDesc;
	
	@Column(name="PROD_EXPIRY")
	private String pExpiry;
	
	public Product() {
		
	}

	public Product(String pName, String pDesc, String pExpiry) {
		super();
		this.pName = pName;
		this.pDesc = pDesc;
		this.pExpiry = pExpiry;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
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
