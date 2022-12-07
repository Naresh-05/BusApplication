<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bus Report</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body TEXT="white">
	<%@ include file="UserHeader.jsp" %>
	<% 
	 	if(user==null||!user.validAdmin) { //without login as user we can't access this page it will redired to login.jsp
     		response.sendRedirect("Login.jsp"); 
     	}
     %> 
	<div class="hero" align="center">
	        <h1>Bus Reserved VIEW</h1>
	        <form action="BusReport.jsp" method="post"> <!-- by default it will show all ticket booked list when specifically given the bus id will again redirected to this page and list the bus details -->
	        <h2>
	            <label>Bus ID</label>
				<input type="search" name="search" pattern=[0-9]*>
	            &nbsp;&nbsp;&nbsp;
	            <button type="submit" class="cute-sml">SEARCH</button>
	        </h2>
	        </form>
	    <c:set var = "bookedBuses" scope = "session" value = "${bookedBuses}"/> <!--  it will load the data from com.ticket.BusReport filter -->
	    <c:if test="${bookedBuses==null}"> 
	    	<caption>
            	<br><br><br><br><br><h2>NO RESERVATION AVAILABLE :(</h2>
            </caption>
	    </c:if>
	    <c:if test="${bookedBuses!=null}">
	    <div align="center">
        <table border="1">
            <caption>
            	<br><br><br>
            	<h2>Lit of Reserved Buses</h2>
            </caption>
            <tr bgcolor="#A52A2A">
            	<th>TICKET ID</th>
                <th>BUS ID</th>
                <th>FROM</th>
                <th>TO</th>
                <th>DATE</th>
                <th>TIME</th>
                <th>USER ID</th>
                <th>NAME</th>
                <th>MOBILE</th>
                <th>SEATS</th>
                <th>COST</th>
            </tr>
            <c:forEach var="bus" items="${bookedBuses}">
                <tr bgcolor="#DEB887" align="center" height="40px">
                	<td><c:out value="${bus.ticketId}" /></td>
                    <td><c:out value="${bus.busId}" /></td>
                    <td><c:out value="${bus.from}" /></td>
                    <td><c:out value="${bus.to}" /></td>
                    <td><c:out value="${bus.date}" /></td>
                    <td><c:out value="${bus.time}" /></td>
                    <td><c:out value="${bus.userId}" /></td>
                    <td><c:out value="${bus.name}" /></td>
                    <td><c:out value="${bus.mobile}" /></td>
                    <td><c:out value="${bus.ticket}" /></td>
                    <td><c:out value="${bus.cost}" /></td>
                </tr>
            </c:forEach>
        </table>
    </div> 
    </c:if>
    <c:set var = "bookedBuses" scope = "session" value = "${null}"/> <!-- after printing that it will set as null -->
</div>
</body>
</html>