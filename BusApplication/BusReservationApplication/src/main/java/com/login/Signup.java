package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.log.LogFile;
import com.login.dao.LoginDao;
import com.login.user.BuildUser;
import com.login.user.User;

@SuppressWarnings("serial")
@WebServlet("/jsp/Signup")
public class Signup extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user=new BuildUser().setEmail(request.getParameter("email")).setPassword(request.getParameter("pass1")).setName(request.getParameter("name")).setMobile(request.getParameter("mobile")).getUser(); //it will get the all details about the user form the signup,jsp
		LoginDao dao=LoginDao.getInstance(response);
		try {
			if(dao.setUser(user)) { //and create the new user and set the valid user flag as true
				LogFile.writeToLog(user.getEmail()+" was added to DB and user is verified successfully");
				HttpSession session = request.getSession();
				session.setAttribute("user",user);
				user.validUser=true;
				response.sendRedirect("/BusReservationApplication/jsp/Welcome.jsp"); //redirected to the welcome page
			}
			else {
				LogFile.writeToLog("faild to add user in DB or user already exist");
				PrintWriter out=response.getWriter();
				out.println("<script type=\"text/javascript\">");
				out.println("alert('This user is already exist please sign in ');");
				out.println("location='/BusReservationApplication/jsp/Login.jsp';");
				out.println("</script>");
			}
		} catch (Exception e) {
			LogFile.writeToLog("faild to add user to DB",e);
			e.printStackTrace();
		}
		
	}

}
