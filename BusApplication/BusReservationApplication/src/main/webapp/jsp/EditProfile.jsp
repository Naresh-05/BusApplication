<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Profile</title>
        <link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
    </head>
    <body>
    	<%@ include file="UserHeader.jsp" %>
    	<% 
		 	if(user==null) { //without sign in as client can't access this page
	     	response.sendRedirect("Index.jsp"); 
	     	}
	     %>
        <div class="hero">
            <div class="editProfile-box">
                <label class="title-bar">EDIT PROFILE</label>
                <form id="editProfile" class="editProfile-group" action="Update" method="post"> <!-- the form will be sended as Update servlet as post method -->
                	<label>Customer ID</label>
               		<input type="text" class="input-field" value="${user.getId()}" name="id" readonly>
               		<label>Email</label>
                    <input type="email" class="input-field" value="${user.getEmail()}" name="email" readonly>
                    <label>Name</label>
                    <input type="text" class="input-field" placeholder="Name" value="${user.getName() }" name="name" pattern="[A-Za-z]{3,32}"
                    title="please enter valid name"  name="name" required>
                    <label>Mobile Number</label>
                    <input type="text" class="input-field" placeholder="Mobile" value="${user.getMobile()}" name="mobile" pattern="[789][0-9]{9}"
                    title="please enter the valid mobile number" name="mobile" required>
                    <label>Address</label>
                    <input type="text" class="input-field" placeholder="Adress" name="address" value="${user.getAddress()}">
                    <button type="submit" class="small-btn">SAVE</button>
                </form>
            </div>
        </div>
    </body>
</html>