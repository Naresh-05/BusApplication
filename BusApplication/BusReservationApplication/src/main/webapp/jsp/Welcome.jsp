<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.login.user.User"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>WELCOME</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body>
	<%@ include file="UserHeader.jsp" %>
	<% 
	 	if(user==null) { //if not login can't access welcome page
     	response.sendRedirect("Index.jsp"); 
     	}
     %>
     
	<div class="hero">
		
	</div>
</body>
</html>