package com.rays.proj4.Test;

import java.sql.Timestamp;
import java.util.Date;

import com.rays.pro4.Bean.SupplierBean;
import com.rays.pro4.Model.SupplierModel;

public class SupplierModelTest {

	public static void main(String[] args) throws Exception {

		testAdd();
		// testDelete();
		// testFindByPk();
		// testUpdate();
		// testSearch();

	}

	private static void testSearch() {
		// TODO Auto-generated method stub

	}

	private static void testUpdate() {
		// TODO Auto-generated method stub

	}

	private static void testFindByPk() {
		// TODO Auto-generated method stub

	}

	private static void testDelete() {
		// TODO Auto-generated method stub

	}

	private static void testAdd() throws Exception {
		SupplierModel model = new SupplierModel();
		SupplierBean bean = new SupplierBean();
		bean.setName("aryan");
		bean.setCategory("xyz");
		bean.setSupplierDate(new Date(2025 - 01 - 12));
		bean.setPayment("50000/-");
		bean.setCreatedBy("Admin");
		bean.setModifiedBy("Admin");
		bean.setCreatedDatetime(new Timestamp(new Date().getTime()));
		bean.setModifiedDatetime(new Timestamp(new Date().getTime()));

		model.add(bean);

	}

}
