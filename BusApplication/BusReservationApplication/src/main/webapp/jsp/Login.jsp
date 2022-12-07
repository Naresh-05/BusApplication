<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>
            Login And Registration
        </title>
        <link rel="stylesheet" type="text/css" href="/BusReservationApplication/css/LoginStyle.css">
    </head>
    <body>
    	<%@ include file="UserHeader.jsp" %> <%-- For including navigation bar for corresponding user --%>
    	<% 
			 if(user!=null) { //if user is already loged in they can't access index.jsp page redirected to welcome.jsp
		     	response.sendRedirect("Welcome.jsp"); 
		     	} 
	     %>
        <div class="hero"> <!-- its for bg image -->
            <div class="form-box">
                <div class="button-box"> <!-- it is help for switch between to tab like login and registration -->
                    <div id="btn"></div>
                    <button type="button" class="toggle-btn" onclick="login()">Log In</button>
                    <button type="button" class="toggle-btn" onclick="register()">Register</button>
                </div>
                <form id="login" class="input-group" action="Login" method="post">
                    <input type="email" class="input-field" placeholder="Email" name="email" required>
                    <input type="password" class="input-field" placeholder="Password" name="pass" required>
                    <br><br>
                    <button type="submit" class="submit-btn">LOG IN</button>
                </form>
                <form id="register" class="input-group" action="Signup" method="post" >
                    <input type="email" class="input-field" placeholder="Email" name="email" required>
                    <input type="text" class="input-field" placeholder="Name" name="name" pattern="[a-zA-Z]{3,}"
                                title="Please enter Alphabets only. Name length must be more then three." required>
                    <input type="password" class="input-field" placeholder="Password" name="pass1" id="pass1" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{8,}"
                                title="Must contain at least one number and one uppercase and lowercase letter, and at least 8 or more characters" required >
                    <input type="password" class="input-field" placeholder="Re-Password" name="pass2"  id="pass2" required>
                    <input type="text" class="input-field" placeholder="Mobile Number" name="mobile" pattern="[789][0-9]{9}"
                                title="Enter the Valid mobile number!" required>
                    <br><br>
                    <button type="submit" class="submit-btn" onclick="return Validate()" >REGISTER</button> <!-- When both the passwords are matched then button will enabled -->
                </form>
            </div>
        </div>
        <script>
            var x=document.getElementById("login");
            var y=document.getElementById("register");
            var z=document.getElementById("btn");

            function register(){
                x.style.left="-400px";
                y.style.left="50px";
                z.style.left="110px";
            }

            function login(){
                x.style.left="50px";
                y.style.left="450px";
                z.style.left="0";
            }
            function Validate() {
                var password = document.getElementById("pass1").value;
                var confirmPassword = document.getElementById("pass2").value;
                if (confirmPassword == '' || password == '') {
                    alert('Please Enter the Both the password?');
                    return false;
                }
                else if (password != confirmPassword) {
                    alert('Password does not match?');
                    return false;
                }
                return true;
            }

        </script>
    </body>
</html>