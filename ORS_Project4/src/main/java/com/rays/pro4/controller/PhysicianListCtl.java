package com.rays.pro4.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.PhysicianBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Model.PhysicianModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PhysicianListCtl", urlPatterns = { "/ctl/PhysicianListCtl" })
public class PhysicianListCtl extends BaseCtl {
	@Override
	protected void preload(HttpServletRequest request) {
		PhysicianModel model = new PhysicianModel();
		Map<String, String> map = new HashMap<String, String>();

		map.put("Dermatologists", "Dermatologists");
		map.put("Cardiology", "Cardiology");
		map.put("Nephrology", "Nephrology");

		request.setAttribute("splztion", map);
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {
		PhysicianBean bean = new PhysicianBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setFullName(DataUtility.getString(request.getParameter("fullName")));
		bean.setBirthDate(DataUtility.getDate(request.getParameter("birthDate")));
		System.out.println("DataUtility.getLong(request.getParameter(\"phone\")"+DataUtility.getLong(request.getParameter("phone")));
		bean.setPhone(DataUtility.getLong(request.getParameter("phone")));
		bean.setSpecialization(DataUtility.getString(request.getParameter("specialization")));

		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List list = null;
		List nextList = null;

		int pageNo = 1;
		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		PhysicianBean bean = (PhysicianBean) populateBean(request);
		String op = DataUtility.getString(request.getParameter("operation"));
		System.out.println(">>>>>>>>>>>>>>>helooo" + bean.getBirthDate());

		PhysicianModel model = new PhysicianModel();

		try {
			list = model.search(bean, pageNo, pageSize);
			System.out.println("list" + list);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

			ServletUtility.setList(list, request);
			ServletUtility.setPageNo(pageNo, request);
			ServletUtility.setPageSize(pageSize, request);
			// ServletUtility.setBean(bean, request);

		} catch (ApplicationException e) {

			ServletUtility.handleException(e, request, response);
			return;
		}

		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("PaymentListCtl doPost Start");

		List list;
		List nextList = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
		pageNo = (pageNo == 0) ? 1 : pageNo;
		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		String op = DataUtility.getString(request.getParameter("operation"));
		PhysicianBean bean = (PhysicianBean) populateBean(request);

		String[] ids = request.getParameterValues("ids");
		PhysicianModel model = new PhysicianModel();

		 if (OP_SEARCH.equalsIgnoreCase(op)) {
			pageNo = 1;
		}

		else if (OP_NEXT.equalsIgnoreCase(op)) {
			pageNo++;
		} else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
			pageNo--;

		} else if (OP_NEW.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.PHYSICIAN_CTL, request, response);
			return;
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.PHYSICIAN_LIST_CTL, request, response);
			return;

		} else if (OP_DELETE.equalsIgnoreCase(op)) {
			pageNo = 1;
			if (ids != null && ids.length > 0) {
				PhysicianBean deletebean = new PhysicianBean();
				for (String id : ids) {
					deletebean.setId(DataUtility.getInt(id));
					try {
						model.delete(deletebean);
					} catch (ApplicationException e) {

						ServletUtility.handleException(e, request, response);
						return;
					}

					ServletUtility.setSuccessMessage("Physician is Deleted Successfully", request);
				}
			} else {
				ServletUtility.setErrorMessage("Select at least one record", request);
			}
		}
		try {

			list = model.search(bean, pageNo, pageSize);

			nextList = model.search(bean, pageNo + 1, pageSize);

			request.setAttribute("nextlist", nextList.size());

		} catch (ApplicationException e) {

			ServletUtility.handleException(e, request, response);
			return;
		}
		if (list == null || list.size() == 0 && !OP_DELETE.equalsIgnoreCase(op)) {
			ServletUtility.setErrorMessage("No record found ", request);
		}
		ServletUtility.setList(list, request);
		ServletUtility.setBean(bean, request);
		ServletUtility.setPageNo(pageNo, request);
		ServletUtility.setPageSize(pageSize, request);

		ServletUtility.forward(getView(), request, response);

	}

	@Override
	protected String getView() {
		return ORSView.PHYSICIAN_LIST_VIEW;
	}

}
