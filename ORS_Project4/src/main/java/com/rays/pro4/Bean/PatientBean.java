package com.rays.pro4.Bean;

import java.util.Date;

public class PatientBean extends BaseBean {

	private String Name;
	private Date Dob;
	private String Mobile;
	private String Decease;

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public Date getDob() {
		return Dob;
	}

	public void setDob(Date dob) {
		Dob = dob;
	}

	public String getMobile() {
		return Mobile;
	}

	public void setMobile(String mobile) {
		Mobile = mobile;
	}

	public String getDecease() {
		return Decease;
	}

	public void setDecease(String decease) {
		Decease = decease;
	}

	@Override
	public String getkey() {
		return Decease;
	}

	@Override
	public String getValue() {
		return Decease;
	}

}
