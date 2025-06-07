<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.controller.TaskListCtl"%>
<%@page import="com.rays.pro4.Bean.TaskBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="com.rays.pro4.Util.ServletUtility"%>
<%@page import="com.rays.pro4.controller.ORSView"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<div align="center">
		<form action="<%=ORSView.TASK_LIST_CTL%>" method="post">

			<jsp:useBean id="bean" class="com.rays.pro4.Bean.TaskBean"
				scope="request"></jsp:useBean>

			<div align="center">
				<h1>
					<font color="navy">Task List</font>
				</h1>
			</div>

			<div align="center" style="height: 15px; margin-bottom: 12px">
				<h3>
					<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>
				</h3>
				<h3>
					<font color="green"><%=ServletUtility.getSuccessMessage(request)%></font>
				</h3>
			</div>

			<%
				int pageNo = ServletUtility.getPageNo(request);
				int pageSize = ServletUtility.getPageSize(request);
				int index = ((pageNo - 1) * pageSize) + 1;

				int nextPageSize = request.getAttribute("nextListSize") != null
						? DataUtility.getInt(request.getAttribute("nextListSize").toString())
						: 0;

				List list = ServletUtility.getList(request);
				if (list != null && !list.isEmpty()) {
					Iterator it = list.iterator();
			%>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">

			<table style="width: 100%">
				<tr>
					<td align="center"><label><b>Task Title:</b></label> <input
						type="text" name="taskTitle"
						value="<%=ServletUtility.getParameter("taskTitle", request)%>">&nbsp;
						<input type="submit" name="operation"
						value="<%=TaskListCtl.OP_SEARCH%>"> &nbsp; <input
						type="submit" name="operation" value="<%=TaskListCtl.OP_RESET%>">
					</td>
				</tr>
			</table>

			<br>

			<table border="1" style="width: 100%">
				<tr style="background-color: lavender; color: black;">
					<th><input type="checkbox" id="selectall"></th>
					<th>S.No.</th>
					<th>Creation Date</th>
					<th>Task Title</th>
					<th>Details</th>
					<th>Assigned To</th>
					<th>Task Status</th>
					<th>Edit</th>
				</tr>
				<%
					while (it.hasNext()) {
							TaskBean taskBean = (TaskBean) it.next();
				%>
				<tr align="center">
					<td><input type="checkbox" class="case" name="ids"
						value="<%=taskBean.getId()%>"></td>
					<td><%=index++%></td>
					<td><%=taskBean.getCreationDate()%></td>
					<td><%=taskBean.getTaskTitle()%></td>
					<td><%=taskBean.getDetails()%></td>
					<td><%=taskBean.getAssignedTo()%></td>
					<td><%=taskBean.getTaskStatus()%></td>
					<td><a href="<%=ORSView.TASK_CTL%>?id=<%=taskBean.getId()%>">edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<br>

			<table style="width: 100%">
				<tr>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=TaskListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>
					<td style="width: 30%"><input type="submit" name="operation"
						value="<%=TaskListCtl.OP_NEW%>"></td>
					<td style="width: 25%"><input type="submit" name="operation"
						value="<%=TaskListCtl.OP_DELETE%>"></td>
					<td style="text-align: right;"><input type="submit"
						name="operation" value="<%=TaskListCtl.OP_NEXT%>"
						<%=(nextPageSize != 0) ? "" : "disabled"%>></td>
				</tr>
			</table>
			<%
				} else {
			%>
			<br>
			<table>
				<tr>
					<td align="center"><b>No records found</b></td>
				</tr>
				<tr>
					<td align="right"><input type="submit" name="operation"
						value="<%=TaskListCtl.OP_BACK%>"></td>
				</tr>
			</table>
			<%
				}
			%>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>

</body>
</html>
