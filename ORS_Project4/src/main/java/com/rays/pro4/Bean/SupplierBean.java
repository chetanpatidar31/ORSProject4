package com.rays.pro4.Bean;

import java.util.Date;

public class SupplierBean extends BaseBean {

	private String name;
	private String category;
	private Date supplierDate;
	private String payment;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getSupplierDate() {
		return supplierDate;
	}

	public void setSupplierDate(Date supplierDate) {
		this.supplierDate = supplierDate;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	@Override
	public String getkey() {
		// TODO Auto-generated method stub
		return id + " ";
	}

	@Override
	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

}
