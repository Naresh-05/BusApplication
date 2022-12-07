<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Cancel Ticket</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body>
	<%@ include file="UserHeader.jsp" %>
    	<% 
		 	if(user==null) { // if user not login they can't access this page
	     	response.sendRedirect("Index.jsp"); 
	     	}
	     %>
        <div class="hero">
        	<div class="editProfile-box">
                <label class="title-bar">CANCEL TICKET</label>
                <form id="editProfile" class="editProfile-group" action="Cancel" method="post" > <!-- after conforming the detail it will goes com.ticket.Cancel servlet -->
                	<label>Ticket ID </label>
               		<input type="text"  class="input-field" value="${cancelBus.getTicketId()}" name="id" readonly >
                	<label>Bus ID </label>
               		<input type="text"  class="input-field" value="${cancelBus.getBusId()}" readonly >
               		<label>From --> To</label>
                    <input type="text" class="input-field" value="${cancelBus.getFrom()} --> ${cancelBus.getTo() }" name="from" readonly>
                    <label>Date And Time</label>
                    <input type="text" class="input-field" placeholder="Mobile" value="${cancelBus.getDate()} & ${cancelBus.getTime()}" name="date" readonly>
                    <label>Number of Seats</label>
                    <input type="text" id="seatChoose"class="input-field" placeholder="Number of Seats" value="${cancelBus.getTicket()}"name="seats" pattern="[1-6]"
                    title="your limit is 1 to 6"  required readonly>
                    <label>Amount</label>
                    <input type="text" id="seatChoose"class="input-field" placeholder="Number of Seats" value="${cancelBus.getCost()}" name="seats" pattern="[1-6]"
                    title="your limit is 1 to 6"  required readonly>
                    <input type="button" onclick="history.back()" class="small-btn" value="back"> &nbsp;&nbsp;&nbsp;
                    <button type="submit" class="small-btn" >Cancel</button>
                </form>
            </div>
        </div>
</body>
</html>