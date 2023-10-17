<%@page import="in.co.rays.project_4.bean.RoleBean"%>
<%@page import="in.co.rays.project_4.ctl.LoginCtl"%>
<%@page import="in.co.rays.project_4.bean.UserBean"%>
<%@page import="in.co.rays.project_4.ctl.ORSView"%>

<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
  <title>jQuery UI Datepicker - Display month &amp; year menus</title>
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <link rel="stylesheet" href="/resources/demos/style.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker({
      changeMonth: true,
      changeYear: true,
      yearRange : '1970:2030'
    });
  } );
  </script>



<!-- <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  <script>
  $( function() {
    $( "#datepicker" ).datepicker();
  } );
  </script> -->
  

</head>
<%
    UserBean userBean = (UserBean) session.getAttribute("user");

    boolean userLoggedIn = userBean != null;

    String welcomeMsg = "Hi, ";

    if (userLoggedIn) {
        String role = (String) session.getAttribute("role");
        welcomeMsg += userBean.getFirstName() + " (" + role + ")";
    } else {
        welcomeMsg += "Guest";
    }
%>
		

<table width="100%" border="0">
    <tr>
        <td width="90%" ><a href="<%=ORSView.WELCOME_CTL%>">Welcome</b></a> |
            <%
            if (userLoggedIn) {
        %> <a href="<%=ORSView.LOGIN_CTL%>?operation=<%=LoginCtl.OP_LOG_OUT%>">Logout</b></a>

            <%
                } else {
            %> <a href="<%=ORSView.LOGIN_CTL%>">Login</b></a> <%
     }
 %></td>
        <td rowspan="2">
            
   		<center>         
            <!-- <img alt="Anand" src="rays.png"> -->
             <h1 align="right">   <img src="<%=ORSView.APP_CONTEXT%>/image/rays.png" width="318"
                    height="60"></h1>
            </center>
        </td> 

    </tr>
    
    <tr>
        <td >
            <h3>
                <%=welcomeMsg%></h3>
        </td>
    </tr>
    

    <%
        if (userLoggedIn) {
    %>

    <tr>
        <td colspan="2" >
        
        <a href="<%=ORSView.GET_MARKSHEET_CTL%>">Get Marksheet</b></a> |
            <a href="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>">Marksheet Merit
                List</b>
        </a> | <a href="<%=ORSView.MY_PROFILE_CTL%>">MyProfile</b></a> | <a
            href="<%=ORSView.CHANGE_PASSWORD_CTL%>">Change Password</b></a>    <%
            if (userBean.getRoleId() == RoleBean.ADMIN) {
        %>| <a href="<%=ORSView.MARKSHEET_CTL%>">Add Marksheet</b></a> | <a
            href="<%=ORSView.MARKSHEET_LIST_CTL%>">Marksheet List</b></a> |
            <a href="<%=ORSView.USER_CTL%>">Add User</b></a> | <a
            href="<%=ORSView.USER_LIST_CTL%>">User List</b></a> | <a
            href="<%=ORSView.COLLEGE_CTL%>">Add College</b></a> | <a
            href="<%=ORSView.COLLEGE_LIST_CTL%>">College List</b></a> | <a
            href="<%=ORSView.STUDENT_CTL%>">Add Student</b></a> | <a
            href="<%=ORSView.STUDENT_LIST_CTL%>">Student List</b></a> | <a
            href="<%=ORSView.ROLE_CTL%>">Add Role</b></a> | <a
            href="<%=ORSView.ROLE_LIST_CTL%>">Role List</b></a> |<a
				href="<%=ORSView.COURSE_CTL%>">Add Course</b></a> | <a
            href="<%=ORSView.COURSE_LIST_CTL%>">Course List</b></a> | <a
            href="<%=ORSView.SUBJECT_CTL%>">Add Subject</b></a> | <a
            href="<%=ORSView.SUBJECT_LIST_CTL%>">Subject List</b></a> | <a
            href="<%=ORSView.TIMETABLE_CTL%>">Add TimeTable</b></a> | <a
            href="<%=ORSView.TIMETABLE_LIST_CTL%>">TimeTable List</b></a>| <a
				href="<%=ORSView.FACULTY_CTL%>">Add Faculty</b></a>| <a
				href="<%=ORSView.FACULTY_LIST_CTL%>">Faculty List</b></a> | <a
				href="<%=ORSView.JAVA_DOC_VIEW%>" target="blank">Java Doc</b></a> |  <%
     }
 %></td>
    
    </tr>
    <%
        }
    %>
</table>
<hr>
</html>