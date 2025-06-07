package com.rays.pro4.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.PositionBean;
import com.rays.pro4.Exception.ApplicationException;
import com.rays.pro4.Exception.DuplicateRecordException;
import com.rays.pro4.Model.PostionModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "PostionCtl", urlPatterns = { "/ctl/PostionCtl" })
public class PositionCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(SupplierCtl.class);

	@Override
	protected boolean validate(HttpServletRequest request) {
		System.out.println("validate started ... std ctl");
		log.debug("SupplierCtl Method validate Started");
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("desgination"))) {
			request.setAttribute("desgination", PropertyReader.getValue("error.require", "Desgination"));
			pass = false;
		} else if (!DataValidator.isName(request.getParameter("desgination"))) {
			request.setAttribute("desgination", "desgination contains Word only");
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("openingDate"))) {
			request.setAttribute("openingDate", PropertyReader.getValue("error.require", "openingDate"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("openingDate"))) {
			request.setAttribute("openingDate", PropertyReader.getValue("error.require", "openingDate"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("experience"))) {
			request.setAttribute("experience", PropertyReader.getValue("error.require", "experience"));
			pass = false;
		} else if (!DataValidator.isAge(request.getParameter("experience"))) {
			request.setAttribute("experience", " experience required ");
			pass = false;
		}

		System.out.println("validate over ,.... Supplier ctl");
		log.debug("SupplierCtl Method validate Ended");
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("SupplierCtl Method populatebean Started");

		PositionBean bean = new PositionBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setDesgination(DataUtility.getString(request.getParameter("desgination")));
		bean.setCondition(DataUtility.getString(request.getParameter("condition")));
		System.out.println("dob" + bean.getCondition());

		populateDTO(bean, request);
		log.debug("PositionCtl Method populatebean Ended");
		return bean;
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("SupplierCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		long id = DataUtility.getLong(request.getParameter("id"));

		// get model

		PostionModel model = new PostionModel();
		if (id > 0 || op != null) {
			PositionBean bean;
			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		ServletUtility.forward(getView(), request, response);
		log.debug("SupplierCtl Method doGett Ended");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		log.debug("position Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		long id = DataUtility.getLong(request.getParameter("id"));
		// get model

		PostionModel model = new PostionModel();

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
			PositionBean bean = (PositionBean) populateBean(request);
			try {
				if (id > 0) {
					model.Update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" Position is successfully Updated", request);
				} else {
					long pk = model.add(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage(" Position is successfully Added", request);
					// bean.setId(pk);
				}
				ServletUtility.setBean(bean, request);
				// ServletUtility.setSuccessMessage(" Position is successfully Added",request);
			} catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;

			} catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Position Email Id already exists", request);
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.POSITION_CTL, request, response);
			return;
		} else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.POSITION_LIST_CTL, request, response);
			return;
		}
		/*
		 * else if (OP_DELETE.equalsIgnoreCase(op)) {
		 * 
		 * SupplierBean bean = (SupplierBean) populateBean(request); try {
		 * model.delete(bean); ServletUtility.redirect(ORSView.SUPPLIER_CTL, request,
		 * response); return;
		 * 
		 * } catch (ApplicationException e) { log.error(e);
		 * ServletUtility.handleException(e, request, response); return; } }
		 */ ServletUtility.forward(getView(), request, response);

		log.debug("SupplierCtl Method doPost Ended");
	}

	/**
	 *
	 */
	@Override
	protected String getView() {
		return ORSView.POSITION_VIEW;
	}

}
