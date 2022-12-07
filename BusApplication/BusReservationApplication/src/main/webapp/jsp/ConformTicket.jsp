<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Conform Ticket</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body>
	<%@ include file="UserHeader.jsp" %>
    	<% 
		 	if(user==null) { //if not a valid user client can't access this page
	     	response.sendRedirect("Index.jsp"); 
	     	}
	     %>
        <div class="hero">
        	<div class="editProfile-box">
                <label class="title-bar">CONFORM TICKET</label>
                <form id="editProfile" class="editProfile-group" action="ConformTicket" method="post" > <!-- after conforming the ticket it will goes to com.ticket.ConformTicket servlet -->
                	<label>Bus ID</label>
               		<input type="text" class="input-field" value="${pickedBus.getId()}" name="id" readonly>
               		<label>From</label>
                    <input type="text" class="input-field" value="${pickedBus.getFrom()}" name="from" readonly>
                    <label>To</label>
                    <input type="text" class="input-field" placeholder="Name" value="${pickedBus.getTo() }" name="to" readonly>
                    <label>Date And Time</label>
                    <input type="text" class="input-field" placeholder="Mobile" value="${pickedBus.getDate()} & ${pickedBus.getTime()}" name="date" readonly>
                    <% session.setAttribute("pickedSeats",request.getParameter("seats")); %>
                    <label>Number of Seats</label>
                    <input type="text" id="seatChoose"class="input-field" placeholder="Number of Seats" value="${pickedSeats}"name="seats" pattern="[1-6]"
                    title="your limit is 1 to 6"  required readonly>
                    <label>Amount</label>
                    <input type="text" id="seatChoose"class="input-field" placeholder="Number of Seats" value="${pickedSeats*pickedBus.getCost()}" name="seats" pattern="[1-6]"
                    title="your limit is 1 to 6"  required readonly>
                    <input type="button" onclick="history.back()" class="small-btn" value="back"> &nbsp;&nbsp;&nbsp;
                    <button type="submit" class="small-btn" >Conform</button>
                </form>
            </div>
        </div>
</body>
</html>