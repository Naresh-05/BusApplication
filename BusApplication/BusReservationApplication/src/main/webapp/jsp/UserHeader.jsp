<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="com.login.user.User"%>
<!DOCTYPE html>
<html>

<head>
    <link rel="stylesheet" href="/BusReservationApplication/css/Navbar.css">
</head>

<body>
    <%
    response.setHeader("Cache-Control","no-cache, no-store, must-revalidate"); //HTTP 1.1
    response.setHeader("Pragma","no-cache"); //HTTP 1.0 
    response.setHeader("Expires","0"); //proxies 
    User user=(User)session.getAttribute("user");  //getting the user attribute
    if(user==null){ %> <!-- if user is not sign in this navbar will show -->
    <header class="header">
        <div class="container">
            <div class="row align-items-center justify-content-between">
               <div class="logo">
               	 <img src="/BusReservationApplication/image/favicon.png" align="left" alt="ZOHO BUS" width="100"  height="50" style="margin:-5px -100px">
                 <a href="#">BUS TICKET BOOKING</a>
               </div>
               <input type="checkbox" id="nav-check">
               <label for="nav-check" class="nav-toggler">
                  <span></span>
               </label>
               <nav class="nav">
                  <ul>
                     <li><a href="Index.jsp">Home</a></li>
                     <li><a href="Bus.jsp">Bus</a></li>
                     <li><a href="Login.jsp">Login</a></li>
                     <li><a href="About.jsp">About</a></li>
                     <li><a href="Contact.jsp">Contact</a></li>
                  </ul>
               </nav>
            </div>
        </div>
     </header> 
    <%} else if(user.validAdmin) { %> <!-- if admin was loged in this navbar wiill show -->
        <header class="header">
        <div class="container">
            <div class="row align-items-center justify-content-between">
               <div class="logo">
                 <img src="/BusReservationApplication/image/favicon.png" align="left" alt="ZOHO BUS" width="100"  height="50" style="margin:-5px -100px">
                 <a href="#">WELCOME ${user.getName()}</a>
               </div>
               <input type="checkbox" id="nav-check">
               <label for="nav-check" class="nav-toggler">
                  <span></span>
               </label>
               <nav class="nav">
                  <ul>
                     <li><a href="Index.jsp" class="active">Home</a></li>
                     <li><a href="AddBus.jsp">Add Bus</a></li>
                     <li><a href="BusReport.jsp">Bus Report</a></li>
                     <li><a href="MaintainBus.jsp">Maintain Bus</a>
                     <li><a href="Logout">Logout</a></li>
                  </ul>
               </nav>
            </div>
        </div>
     </header>
    <% } else if(user.validUser){ %> <!-- if client user is loged in it will show -->
    	<header class="header">
        <div class="container">
            <div class="row align-items-center justify-content-between">
               <div class="logo">
               <img src="/BusReservationApplication/image/favicon.png" align="left" alt="ZOHO BUS" width="100"  height="50" style="margin:-5px -100px">
                 <a href="#">WELCOME ${user.getName()}</a>
               </div>
               <input type="checkbox" id="nav-check">
               <label for="nav-check" class="nav-toggler">
                  <span></span>
               </label>
               <nav class="nav">
                  <ul>
                     <li><a href="Index.jsp" class="active">Home</a></li>
                     <li><a href="Bus.jsp">Bus</a></li>
                     <li><a href="EditProfile.jsp">Edit Profile</a></li>
                     <li><a href="History">History</a></li>
                     <li><a href="Logout">Logout</a></li>
                  </ul>
               </nav>
            </div>
        </div>
     </header>
    <% } %>
</body>

</html>