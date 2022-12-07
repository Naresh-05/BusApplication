<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Maintain Bus</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body text="white" >
	<%@ include file="UserHeader.jsp" %>
	<% 
	 	if(user==null||!user.validAdmin) { //expect admin other user can't access this page
     		response.sendRedirect("Login.jsp"); 
     	}
     %> 
	<div class="hero" align="center">
	        <h1>BUS MAINTANANCE</h1>
	        <div align="center">
	     
	     
	     <c:set var = "main" scope = "session" value = "${main}"/> <!-- main attribute was load the data from the com.bus.MaintainBus Filter -->
	    <c:if test="${main==null}">
	    	<caption>
            	<br><br><br><br><br><h2>BUS MAINTANANCE NOT AVAILABLE :(</h2>
            </caption>
	    </c:if>
	    <c:if test="${main!=null}">
        <table border="1" >
            <caption>
            	<br><br><br>
            	<h2>List of Buses</h2>
            </caption>
            <tr bgcolor="#A52A2A">
                <th>BUS ID</th>
                <th>FROM</th>
                <th>TO</th>
                <th>DATE</th>
                <th>TIME</th>
                <th>TOTAL SEATS</th>
                <th>AVAILABLE SEATS</th>
                <th>COST</th>
                <th>UPDATE</th>
                <th>DELETE</th>
            </tr>
            <c:forEach var="bus" items="${main}">
                <tr bgcolor="#DEB887" align="center" height="40px">
                    <td><c:out value="${bus.id}" /></td>
                    <td><c:out value="${bus.from}" /></td>
                    <td><c:out value="${bus.to}" /></td>
                    <td><c:out value="${bus.date}" /></td>
                    <td><c:out value="${bus.time}" /></td>
                    <td><c:out value="${bus.totalSeats}"/></td>
                    <td><c:out value="${bus.seats}" /></td>
                    <td><c:out value="${bus.cost}" /></td>
                    <td>
                         <a href="UpdateBus?id=<c:out value='${bus.id}' />" class="cute-sml">Update</a>   <!-- it will redirected to the com.bus.UpdateBus servlet -->             
                    </td>
                    <td>
                         <a class="cute-sml"
                         	<c:choose>
                         		<c:when test="${bus.totalSeats==bus.seats}">
                         			href="DeleteBus?id=<c:out value='${bus.id}' />"  <!-- it will redirected to com.bus.DeleteBus servlet -->
								</c:when>
                        	  	<c:otherwise>
									href="javascript:void(0)" <!-- it will disable the link -->
								</c:otherwise>  
                        	 </c:choose> >
                        Delete  </a>                  
                    </td>
                </tr>
            </c:forEach>
        </table>
        </c:if>
    </div> 
	</div>
</body>
</html>