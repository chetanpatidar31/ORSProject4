<%@page import="com.rays.pro4.Util.DataUtility"%>
<%@page import="com.rays.pro4.Util.HTMLUtility"%>
<%@page import="com.rays.pro4.controller.TaskCtl"%>
<%@page import="java.util.HashMap"%>
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
	<form action="<%=ORSView.TASK_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

		<jsp:useBean id="bean" class="com.rays.pro4.Bean.TaskBean"
			scope="request"></jsp:useBean>

		<div align="center">

			<h1>
				<font color="navy">Add Task</font>
			</h1>

			<h3>
				<font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
				</font>
			</h3>
			<h3>
				<font color="red"> <%=ServletUtility.getErrorMessage(request)%>
				</font>
			</h3>

			<input type="hidden" name="id" value="<%=bean.getId()%>"> <input
				type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
			<input type="hidden" name="modifiedBy"
				value="<%=bean.getModifiedBy()%>"> <input type="hidden"
				name="createdDatetime"
				value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"
				value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			<table>
				<tr>
					<th>Creation Date:</th>
					<td><input type="date" name="creationDate" id="udate"
						placeholder="Enter Creation Date"
						value="<%=DataUtility.getStringData(bean.getCreationDate())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("creationDate", request)%></font></td>
				</tr>
				<tr>
					<th>Task Title:</th>
					<td><input type="text" name="taskTitle"
						placeholder="Enter Task Title"
						value="<%=DataUtility.getStringData(bean.getTaskTitle())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("taskTitle", request)%></font></td>
				</tr>
				<tr>
					<th>Details:</th>
					<td><input type="text" name="details"
						placeholder="Enter Details"
						value="<%=DataUtility.getStringData(bean.getDetails())%>"></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("details", request)%></font></td>
				</tr>
				<tr>
					<th>Assigned To:</th>
					<td>
						<%
							HashMap map = new HashMap();
							map.put("prince", "prince");
							map.put("avnish", "avnish");
							map.put("abhishek", "abhishek");
							map.put("aryan", "aryan");

							String hlist = HTMLUtility.getList("assignedTo", bean.getAssignedTo(), map);
						%> <%=hlist%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("assignedTo", request)%></font></td>
				</tr>
				<tr>
					<th>Task Status:</th>
					<td>
						<%
							HashMap map1 = new HashMap();
							map1.put("New", "New");
							map1.put("Started", "Started");
							map1.put("On Hold", "On Hold");
							map1.put("Completed", "Completed");
							map1.put("Closed", "Closed");
							String hlist1 = HTMLUtility.getList("taskStatus", bean.getTaskStatus(), map1);
						%> <%=hlist1%>
					</td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("taskStatus", request)%></font></td>
				</tr>
				<th></th>
				<td><input type="submit" name="operation"
					value="<%=TaskCtl.OP_SAVE%>">&nbsp; <input type="submit"
					name="operation" value="<%=TaskCtl.OP_RESET%>"></td>
				</tr>
			</table>
		</div>
	</form>

</body>
</html>
