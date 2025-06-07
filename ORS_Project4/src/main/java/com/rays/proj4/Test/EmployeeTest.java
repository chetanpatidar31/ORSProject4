package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.rays.pro4.Bean.EmployeeBean;
import com.rays.pro4.Bean.StudentBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.EmployeeModel;

public class EmployeeTest {
public static void main(String[] args) throws Exception {
	testAdd();
	
}

private static void testAdd() throws Exception {
	try{
		EmployeeBean bean=new EmployeeBean();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		EmployeeModel model = new EmployeeModel();
		//bean.setId(3L);
		bean.setFullName("Kapil");
		bean.setUserName("kmalviya30@gmail.com");
		bean.setPassword("Pass123");
		bean.setBirthDate(sdf.parse("22/09/1997"));
		bean.setNumber("9407411301");
		
		bean.setCreatedBy("admin");
		bean.setModifiedBy("admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));
		long pk = model.add(bean);
		
		EmployeeBean addbean = model.findByPK(pk);
		if(addbean==null){
			System.out.println("Test add fail");
		}
	}catch(ApplicationException e){
		e.printStackTrace();
	}catch(DuplicateRecordException e){
		e.printStackTrace();
	}
	
}
}
