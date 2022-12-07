<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Travel History</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body text="white" >
	<%@ include file="UserHeader.jsp" %>
	<% 
	 	if(user==null||user.validAdmin) { //without login as clint can't access this page it will redirected to login.jsp
     		response.sendRedirect("Login.jsp"); 
     	}
     %>
	<div class="hero" align="center">
	        <h1>Travel History</h1>
	        <div align="center">
	     
	     
	     <c:set var = "history" scope = "session" value = "${history}"/> <!-- attribute history was loaded by the com.ticket.History servlet will load the data -->
	    <c:if test="${history==null}">
	    	<caption>
            	<br><br><br><br><br><h2>BUS TRAVEL HISTORY NOT AVAILABLE :(</h2>
            </caption>
	    </c:if>
	    <c:if test="${history!=null}">
        <table border="1" >
            <caption>
            	<br><br><br>
            	<h2>List of Buses</h2>
            </caption>
            <tr bgcolor="#A52A2A">
            	<th>TICKET ID</th>
                <th>BUS ID</th>
                <th>FROM</th>
                <th>TO</th>
                <th>DATE</th>
                <th>TIME</th>
                <th>SEATS</th>
                <th>COST</th>
                <th>ACTION</th>
            </tr>
            <c:forEach var="bus" items="${history}">
                <tr bgcolor="#DEB887" align="center" height="40px">
                	<td><c:out value="${bus.ticketId}" /></td>
                    <td><c:out value="${bus.busId}" /></td>
                    <td><c:out value="${bus.from}" /></td>
                    <td><c:out value="${bus.to}" /></td>
                    <td><c:out value="${bus.date}" /></td>
                    <td><c:out value="${bus.time}" /></td>
                    <td><c:out value="${bus.ticket}" /></td>
                    <td><c:out value="${bus.cost}" /></td>
                    <td>
                    	  &nbsp;
                         <a href="CancelTicket?id=<c:out value='${bus.ticketId}' />" class="cute-sml">Cancel</a>  <!-- after clicking the button dynamic url as ticketID will send com.ticket.CancelTicket -->             
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </div> 
	</div>
</body>
</html>