<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Bus</title>
<link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
</head>
<body TEXT="white">
	<%@ include file="UserHeader.jsp" %>
	<div class="hero" align="center">
	        <h1>Search For Bus</h1>
	        <form action="GetBusForBook" method="post">
	        <h2>
	            <label>From</label>
				<select name="from" id="from" required>
				<option value="none" selected disabled hidden>Select a From point</option>
				<c:forEach var="from" items="${fitFrom}"> <!-- getting the attribute fitFrom it will get from CollectFromTo filter -->
	            	<option><c:out value="${from}" /></option>
	            </c:forEach>
				</select>
	            &nbsp;&nbsp;&nbsp;
	            <label>To</label>
	            <select name="to" id="to" required >
	            <option value="none" selected disabled hidden>Select a To point</option>
	            <c:forEach var="to" items="${fitTo}"> <!-- getting the attribute fitTo it will get from CollectFromTo filter -->
	            	<option><c:out value="${to}" /></option>
	            </c:forEach>
	            </select>
	            &nbsp;&nbsp;&nbsp;
	            <label >Date</label>
				<input type="date" name="date" required>
				&nbsp;&nbsp;&nbsp;
	            <button type="submit" class="cute-sml" onclick="return check()">SEARCH</button> <!-- it will enable the button based on js script -->
	        </h2>
	        </form>
	    <c:set var = "listBus" scope = "session" value = "${listBus}"/> <!-- getting the attribute listBus it will get from GetBusForBook servlet -->
	    <c:if test="${listBus==null}"> <!-- if listBus is null it will execute -->
	    	<caption>
            	<br><br><br><br><br><h2>BUS NOT AVAILABLE :(</h2>
            </caption>
	    </c:if>
	    <c:if test="${listBus!=null}"> <!--  if list is not null the below code will execute -->
	    <div align="center">
        <table border="1">
            <caption>
            	<br><br><br>
            	<h2>Lit of Buses</h2>
            </caption>
            <tr bgcolor="#A52A2A">
                <th>BUS ID</th>
                <th>FROM</th>
                <th>TO</th>
                <th>DATE</th>
                <th>TIME</th>
                <th>SEATS</th>
                <th>COST PER PERSON</th>
                <th>ACTION</th>
            </tr>
            <c:forEach var="bus" items="${listBus}">
            	<c:if test="${bus.seats!=0}">
                <tr bgcolor="#DEB887" align="center" height="40px">
                    <td><c:out value="${bus.id}" /></td>
                    <td><c:out value="${bus.from}" /></td>
                    <td><c:out value="${bus.to}" /></td>
                    <td><c:out value="${bus.date}" /></td>
                    <td><c:out value="${bus.time }"/></td>
                    <td><c:out value="${bus.seats}" /></td>
                    <td><c:out value="${bus.cost}" /></td>
                    <td>
                    	  &nbsp;
                         <a href="BookTicket?id=<c:out value='${bus.id}' />" class="cute-sml">Book</a>   <!-- On click book button it will send the dynamic url as busId it call BookTicket servlet -->                 
                    </td>
                </tr>
                </c:if>
            </c:forEach>
        </table>
    </div> 
    </c:if>
    <c:set var = "listBus" scope = "session" value = "${null}"/> <!-- after executing all the bus list the it will become null for old data storing -->
</div>
<script>
function check(){
	 if(document.getElementById("from").value==document.getElementById("to").value){ //it will check the from and to value not same if both are same it will popup the alert box
		alert('INVALID POINTS:(');
		return false;
	} 
	return true;
}
</script>
</body>
</html>