<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@page import="in.co.rays.project_4.ctl.ORSView"%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome</title>
</head>
<body>

	<form action="<%=ORSView.WELCOME_CTL %>">
	<br>
	<%@include file="Header.jsp"%>
		<h1 align="Center">
		<br><br>
		
			<font size="10px" color="red" align="right">Welcome to ORS</font></h1>
			
			  <%-- <%
			UserBean beanUserBean=(UserBean)session.getAttribute("user");
			if(beanUserBean!=null){
				if(beanUserBean.getRoleId()==RoleBean.STUDENT){
				
					
							
				%>
				
				
				 <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Click here to see your Marksheet </a>
                    </h2>
			<% 	
				}
			}
			%> --%> 
	</form>
	<%@include file="Footer.jsp"%>
</body>
</html>