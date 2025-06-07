package com.rays.pro4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.pro4.Bean.BaseBean;
import com.rays.pro4.Bean.TaskBean;
import com.rays.pro4.Model.TaskModel;
import com.rays.pro4.Util.DataUtility;
import com.rays.pro4.Util.DataValidator;
import com.rays.pro4.Util.PropertyReader;
import com.rays.pro4.Util.ServletUtility;

@WebServlet(name = "TaskCtl", urlPatterns = { "/TaskCtl" })
public class TaskCtl extends BaseCtl {

	@Override
	protected boolean validate(HttpServletRequest request) {
		boolean pass = true;

		if (DataValidator.isNull(request.getParameter("creationDate"))) {
			request.setAttribute("creationDate", PropertyReader.getValue("error.require", "CreationDate"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("taskTitle"))) {
			request.setAttribute("taskTitle", PropertyReader.getValue("error.require", "Task Title"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("details"))) {
			request.setAttribute("details", PropertyReader.getValue("error.require", "Details"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("assignedTo"))) {
			request.setAttribute("assignedTo", PropertyReader.getValue("error.require", "Assigned To"));
			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("taskStatus"))) {
			request.setAttribute("taskStatus", PropertyReader.getValue("error.require", "Task Status"));
			pass = false;
		}
		return pass;
	}

	@Override
	protected BaseBean populateBean(HttpServletRequest request) {

		TaskBean bean = new TaskBean();
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		bean.setCreationDate(DataUtility.getDate(request.getParameter("creationDate")));
		System.out.println("date => " + request.getParameter("creationDate"));
		bean.setTaskTitle(DataUtility.getString(request.getParameter("taskTitle")));
		bean.setDetails(DataUtility.getString(request.getParameter("details")));
		bean.setAssignedTo(DataUtility.getString(request.getParameter("assignedTo")));
		bean.setTaskStatus(DataUtility.getString(request.getParameter("taskStatus")));
		populateDTO(bean, request);
		return bean;

	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));
		Long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0) {

			TaskModel model = new TaskModel();

			try {
				TaskBean bean = model.findByPk(id);
				ServletUtility.setBean(bean, request);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		ServletUtility.forward(getView(), request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String op = DataUtility.getString(request.getParameter("operation"));

		TaskModel model = new TaskModel();

		TaskBean bean = (TaskBean) populateBean(request);

		if (OP_SAVE.equalsIgnoreCase(op)) {
			try {

				model.add(bean);
				ServletUtility.setSuccessMessage("Data Added Successfully..!!", request);
				ServletUtility.forward(getView(), request, response);
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Data already exist", request);
				ServletUtility.forward(getView(), request, response);
			}

			catch (Exception e) {
				e.printStackTrace();
			}
		} else if (OP_RESET.equalsIgnoreCase(op)) {
			ServletUtility.redirect(ORSView.TASK_CTL, request, response);
			return;
		}
	}

	@Override
	protected String getView() {
		return ORSView.TASK_VIEW;
	}

}
