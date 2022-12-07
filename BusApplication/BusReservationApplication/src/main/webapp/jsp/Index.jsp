<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.login.user.User"%>
<!DOCTYPE html>
<html>
<head>
<title>WELCOME</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css"> <!-- adding style sheet for application -->
<link rel="icon" type="image/png" href="/BusReservationApplication/image/favicon.png" sizes="180x180"> <!-- adding web icon -->
</head>
<body>
 	<%@ include file="UserHeader.jsp" %> <%-- It will include a corresponding navigation bar --%>
	<% 
	 if(user!=null) {  //if user is already loged in they can't access index.jsp page redirected to welcome.jsp
     	response.sendRedirect("Welcome.jsp"); 
     	} 
     %>
    
	<div class="hero">
		<br><br><br><br><br><br><br><br>
		<font face="algerian" size="100" color="white">
			<marquee scrollamount="15" loop="infinite">WELCOME TO BOOK MY TICKET</marquee> <!-- It just scroll the text across right to left by default -->
		</font>
	</div>
</body>
</html>