<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Bus</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body>
	<%@ include file="UserHeader.jsp" %>
	<% 
	 	if(user==null||!user.validAdmin) { //successfull login client only access this page
     		response.sendRedirect("Login.jsp"); 
     	}
     %> 
	<div class="hero">
            <div class="editProfile-box">
                <label class="title-bar">ADD BUS</label>
                <form id="editProfile" class="editProfile-group" action="AddBus" method="post"  > <!-- The form will submit to com.bus.AddBus servlet -->
               		<label>From</label>
                    <input type="text" class="input-field" placeholder="from" name="from" required>
                    <label>To</label>
                    <input type="text" class="input-field" placeholder="to"  name="to" required>
                    <label>Date</label><br>
                    <input type="Date" placeholder="date" name="date" required><br>
                    <label>Time</label><br>
                    <input type="time" name="time"  placeholder="time" required><br>
                    <label>Cost</label>
                    <input type="text" class="input-field" placeholder="cost"  name="cost" pattern="[1-9][0-9]*" required>
                    <label>Number of Seats</label>
                    <input type="text" id="seatChoose" class="input-field" placeholder="Number of Seats" name="seats" pattern="[1-7][0-9]" required>
                    <input type="button" onclick="window.location.href='AddBus.jsp'" class="small-btn" value="back">&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="small-btn" >Add Bus</button>
                </form>
            </div>
        </div>
</body>
</html>