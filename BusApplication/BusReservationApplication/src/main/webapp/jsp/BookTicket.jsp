<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book Ticket</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body>
	<%@ include file="UserHeader.jsp" %>
	<% 
	 	if(user==null||user.validAdmin) { //we cant access this page without login and admin also can't acces this page
     		response.sendRedirect("Login.jsp"); 
     	}
     %>
	<div class="hero">
            <div class="editProfile-box">
                <label class="title-bar">BOOK TICKET</label>
                <form id="editProfile" class="editProfile-group" action="ConformTicket.jsp" method="post" onsubmit="return ValidationEvent()"> <!-- the form will submitted to com.ticket.ConformTicket servlet -->
                	<label>Bus ID</label>
               		<input type="text" class="input-field" value="${pickedBus.getId()}" name="id" readonly>
               		<label>From</label>
                    <input type="email" class="input-field" value="${pickedBus.getFrom()}" name="from" readonly>
                    <label>To</label>
                    <input type="text" class="input-field" placeholder="Name" value="${pickedBus.getTo() }" name="to" readonly>
                    <label>Date</label>
                    <input type="text" class="input-field" placeholder="Mobile" value="${pickedBus.getDate()}" name="date" readonly>
                    <label>Time</label>
                    <input type="text" class="input-field" placeholder="Mobile" value="${pickedBus.getTime()}" name="time" readonly>
                    <label>Number of Seats</label>
                    <input type="text" id="seatChoose"class="input-field" placeholder="Number of Seats" name="seats" pattern="[1-6]"
                    title="your limit is 1 to 6"  required>
                    <input type="button" onclick="window.location.href='Bus.jsp'" class="small-btn" value="back">&nbsp;&nbsp;&nbsp;
                    <button type="submit" class="small-btn" >Book Ticket</button>
                </form>
            </div>
        </div>
        <script>
        	function ValidationEvent(){
        		var s=document.getElementById("seatChoose").value;
        		if(${pickedBus.getSeats()}>=s&&s>=1&&s<=6){  //it will validating the seat selection between 1 to 6
        			return true;
        		}else{
        			alert('Unavalilabel seats enter valid seats');
        		}
        		return false;
        	}
        </script>
</body>
</html>