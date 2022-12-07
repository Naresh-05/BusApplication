package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.log.LogFile;


@SuppressWarnings("serial")
@WebServlet("/jsp/Logout")
public class Logout extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		LogFile.writeToLog("user logout successfully :)");
		session.removeAttribute("user"); //it will remove the user details from the session 
		session.invalidate(); //make the session and invalid
		response.sendRedirect("Index.jsp"); //redirected to the index page
	}

	

}
