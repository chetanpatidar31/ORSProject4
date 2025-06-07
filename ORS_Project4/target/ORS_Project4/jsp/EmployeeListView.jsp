<%@page import="com.rays.pro4.Bean.EmployeeBean"%>
<%@page import="com.rays.pro4.Model.EmployeeModel"%>
<%@page import="com.rays.pro4.controller.EmployeeListCtl"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@page import="com.rays.pro4.controller.StudentListCtl"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<html>
<head>
<link rel="icon" type="image/png"
	href="<%=ORSView.APP_CONTEXT%>/img/logo.png" sizes="16*16" />
<title>Employee List</title>

<script src="<%=ORSView.APP_CONTEXT%>/js/jquery.min.js""></script>
<script src="<%=ORSView.APP_CONTEXT%>/js/Checkbox11.js"></script>


</head>
<body>

	<jsp:useBean id="cbean" class="com.rays.pro4.Bean.EmployeeBean"
		scope="request"></jsp:useBean>

	<form action="<%=ORSView.EMPLOYEE_lIST_CTL%>" method="post">
		<%@include file="Header.jsp"%>

		<center>

			<div align="center">
				<h1>Employee List</h1>
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>


			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				List list = ServletUtility.getList(request);
				Iterator<EmployeeBean> it = list.iterator();

				if (list.size() != 0) {
			%>

			<table width="100%">
				<tr align="center">
					<td align="center"><label align="center">Name:</label> <input
						type="text" name="name" placeholder="Enter Name" Size="25"
						value="<%=ServletUtility.getParameter("name", request)%>">
						&nbsp; <label align="center">Payment: </label> <input type="text"
						name="payment" placeholder="Enter Payment" Size="25"
						value="<%=ServletUtility.getParameter("payment", request)%>">
						&nbsp; <input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_RESET%>"></td>
				</tr>
			</table>

			<br>

			<table border="1" width="100%" align="center" cellpadding=6px
				cellspacing=".2">
				<tr style="background: skyblue">
					<th><input type="checkbox" id="select_all" name="select">Select
						All.</th>

					<th>S No.</th>
					<th>FullName.</th>
					<th>UserName.</th>
					<th>Password</th>
					<th>Birth Date.</th>
					<th>Number</th>


					<th>Edit</th>
				</tr>

				<%
					while (it.hasNext()) {
							cbean = it.next();
				%>


				<tr align="center">
					<td><input type="checkbox" class="checkbox" name="ids"
						value="<%=cbean.getId()%>">
					<td><%=index++%></td>
					<td><%=cbean.getFullName()%></td>
					<td><%=cbean.getUserName()%></td>
					<td><%=cbean.getPassword()%></td>
					<td><%=cbean.getBirthDate()%></td>
					<td><%=cbean.getNumber()%></td>

					<td><a href="Employee?id=<%=cbean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<%
						if (pageNo == 1) {
					%>
					<td><input type="submit" name="operation" disabled="disabled"
						value="<%=EmployeeListCtl.OP_PREVIOUS%>"> <%
 	} else {
 %>
					<td><input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_PREVIOUS%>"></td>
					<%
						}
					%>

					<td><input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_DELETE%>"></td>
					<td><input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_NEW%>"></td>

					<%
						EmployeeModel model = new EmployeeModel();
					%>
					<%
						if (list.size() < pageSize || model.nextPK() - 1 == cbean.getId()) {
					%>
					<td align="right"><input type="submit" name="operation"
						disabled="disabled" value="<%=EmployeeListCtl.OP_NEXT%>"></td>
					<%
						} else {
					%>
					<td align="right"><input type="submit" name="operation"
						value="<%=EmployeeListCtl.OP_NEXT%>"></td>
					<%
						}
					%>

				</tr>
			</table>
			<%
				}
				if (list.size() == 0) {
			%>
			<td align="center"><input type="submit" name="operation"
				value="<%=EmployeeListCtl.OP_BACK%>"></td>
			<%
				}
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">
	</form>
	</br>
	</br>
	</br>
	</br>
	</br>
	</br>
	</center>

	<%@include file="Footer.jsp"%>
</body>
</html>