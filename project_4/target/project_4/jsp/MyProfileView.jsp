<%@page import="in.co.rays.project_4.ctl.MyProfileCtl" %>
<%@page import="in.co.rays.project_4.util.HTMLUtility" %>
<%@page import="in.co.rays.project_4.util.DataUtility" %>
<%@page import="in.co.rays.project_4.util.ServletUtility" %>
<%@page import="java.util.HashMap" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>MY Profile</title>
<style>
.p1 {
	font-size: 18px;
}

.p2 {
	padding: 5px;
	margin: 3px;
}
</style>
<script>
	function phoneno() {
		$('#phone').keypress(function(e) {
			var a = [];
			var k = e.which;

			for (i = 48; i < 58; i++)
				a.push(i);

			if (!(a.indexOf(k) >= 0))
				e.preventDefault();
		});
	}
</script>
</head>
<body>
<form action="<%=ORSView.MY_PROFILE_CTL%>"method="post">
<%@include file="Header.jsp" %>
<script type="text/javascript" src="../jss/calendar.js"></script>
<jsp:useBean id="bean" class="in.co.rays.project_4.bean.UserBean" scope="request"></jsp:useBean>

<center>
  <h1>My Profile</h1>
  <h2>
     <font color="red"><%=ServletUtility.getErrorMessage(request) %></font>
     
  </h2>
  <input type="hidden" name="id" value="<%=bean.getId()%>">
  <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
  <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
  <input type="hidden" name="createdDateTime" value="<%=bean.getCreatedDateTime()%>">
  <input type="hidden" name="modifiedDateTime" value="<%=bean.getModifiedDateTime()%>">
 
 <table>
<tr>
					<th align="left" class="p1">LoginId<span style="color: red;">*</span></th>
					<td><input type="text" name="login" size="40" class="p2"
						placeholder="Enter Login ID"
						value="<%=DataUtility.getStringData(bean.getLogin())%>"
						<%=(bean.getId() > 0) ? "readonly" : ""%>></td>
					<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
				</tr>
					<%
					if (bean != null && bean.getId() > 0) {
				%>
				<%
					}
				%>
  <tr>
  <th align="left" class="p1">FirstName<span style="color:red">*</span></th>
    <td><input type="text" name="firstName" placeholder="Enter First Name" size="40" class="p2" value="<%=DataUtility.getStringData(bean.getFirstName()) %>"></td>
   <td> <font color="red"><%=ServletUtility.getErrorMessage("firstName", request) %></font></td>
    </tr>  
    <tr>
    <th align="left" class="p1">LastName<span style="color:red">*</span></th>
    <td><input type="text" name="lastName" placeholder="Enter Last Name" size="40" class="p2" value="<%=DataUtility.getStringData(bean.getLastName()) %>"></td>
  <td>  <font color="red"><%=ServletUtility.getErrorMessage("lastName", request) %></font></td>
    </tr>
    <%-- <tr>
      <th align="left" class="p1">Gender<span style="color:red">*</span></th>
      <td>
        <%
          HashMap map=new HashMap();
        map.put("Male","Male");
        map.put("Female","Female");
        
        String htmlList=HTMLUtility.getList("gender", bean.getGender(), map);
        
        %> <%=htmlList %>
        </td>
        </tr> --%>
        <tr>
		<th align="left" class="p1">Gender<span style="color: red;">*</span></th>
		<td><input type="text" name="gender" class="p2" size="40"
		 placeholder="Enter Gender" value="<%=DataUtility.getStringData(bean.getGender())%>"></td>
		<td style="position: fixed;"><font color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font></td>
				</tr>
        <tr>
          <th align="left" class="p1">MobileNo<span style="color:red">*</span></th>
    <td><input type="text" name="mobileNo" placeholder="Enter MobileNo" size="40" class="p2" maxlength="10" value="<%=DataUtility.getStringData(bean.getMobileNo()) %>"></td>
   <td> <font color="red"><%=ServletUtility.getErrorMessage("mobileNo", request) %></font></td>
    </tr>
    <tr>
      <th align="left" class="p1">Date Of Birth<span style="color:red">*</span></th>
    <td><input type="text" name="dob" readonly="readonly" size="40" class="p2" value="<%=DataUtility.getDateString(bean.getDob()) %>"></td>
  <!-- <a href="javascript:getCalendar(document.forms[0].dob);">
  <img alt="Calender" src="../img/cal.jpg" width="16" height="15" border="0">
  </a> -->
 <td>   <font color="red"><%=ServletUtility.getErrorMessage("dob", request) %></font></td>
    </tr>
    <h2>
    <font color="green"><%=ServletUtility.getSuccessMessage(request) %></font>
    </h2>
    
    <tr>
      <th></th>
      <td colspan="2"><input type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD%>">&nbsp;
      <input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE%>">&nbsp;</td>
	</tr>
	
 </table>
 
 </center>
 </form>
<%@include file="Footer.jsp" %>
</body>
</html>