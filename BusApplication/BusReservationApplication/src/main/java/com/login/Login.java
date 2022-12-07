package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.log.LogFile;
import com.login.user.User;

@SuppressWarnings("serial")
@WebServlet("/jsp/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session=request.getSession();
		User user=(User)session.getAttribute("user");//it will catch the user attribute 
		if(user.validUser) {  //is validUSer flag is set it will redirected to the welcome page otherwise  it will redirected to login page
			response.sendRedirect("/BusReservationApplication/jsp/Welcome.jsp");
			LogFile.writeToLog("user logined successfully");
		}else {
			response.sendRedirect("/BusReservationApplication/jsp/Login.jsp");
		}
	}

}
