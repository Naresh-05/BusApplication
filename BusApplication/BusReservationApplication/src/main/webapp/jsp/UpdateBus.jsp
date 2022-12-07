<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>UPDATE BUS</title>
	<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
    </head>
    <body>
    	<%@ include file="UserHeader.jsp" %>
    	<% 
		 	if(user==null||!user.validAdmin) { //without sign in as client can't access this page
	     	response.sendRedirect("Login.jsp"); 
	     	}
	     %>
        <div class="hero">
            <div class="editProfile-box">
                <label class="title-bar">UPDATE BUS</label>
                <form id="editProfile" class="editProfile-group" action="UpdateBusDetail" method="post"> <!-- the form will be sended as Update servlet as post method -->
                	<label>Bus ID</label>
               		<input type="text" class="input-field" value="${updateBus.getId()}" name="id" readonly>
               		<label>From</label>
                    <input type="text" class="input-field" placeholder="from" value="${updateBus.getFrom()}" name="from" required>
                    <label>To</label>
                    <input type="text" class="input-field" placeholder="to" value="${updateBus.getTo()}" name="to" required>
                    <label>Date</label><br>
                    <input type="Date" placeholder="date" name="date" value="${updateBus.getDate()}" required><br>
                    <label>Time</label><br>
                    <input type="time" name="time"  placeholder="time" value="${updateBus.getTime()}" required><br>
                    <label>Cost</label>
                    <input type="text" class="input-field" placeholder="cost"  name="cost" pattern="[1-9][0-9]*" value="${updateBus.getCost()}" required>
                    <label>Number of Seats</label>
                    <input type="text" id="seatChoose" class="input-field" placeholder="Number of Seats"  value="${updateBus.getTotalSeats()}" name="seats" pattern="[1-7][0-9]" required>
                    <button type="submit" class="small-btn">SAVE</button>
                </form>
            </div>
        </div>
    </body>
</html>