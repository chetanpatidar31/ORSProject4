<%@page import="in.co.rays.controller.ORSView"%>
<%@page import="in.co.rays.controller.LoginCtl"%>
<%@page import="in.co.rays.bean.UserBean"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		UserBean user = (UserBean) session.getAttribute("user");
	%>
	<%
		if (user != null) {
	%>
	<h3>
		Hi,
		<%=user.getFirstName()%>
		(<%=session.getAttribute("role")%>)
	</h3>
	<a href="<%=ORSView.USER_CTL%>"><b>Add User</b></a>
	<b>|</b>
	<a href="<%=ORSView.USER_LIST_CTL%>"><b>User List</b></a>
	<b>|</b>
	<a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>"><b>Logout</b></a>
	<%
		} else {
	%>
	<h3>Hi, Guest</h3>
	<a href="<%=ORSView.WELCOME_CTL %>"><b>Welcome</b></a> |
	<a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>
	<%
		}
	%>
	<hr>
</body>
</html>