package com.ticket;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.log.LogFile;
import com.login.user.User;

@SuppressWarnings("serial")
@WebServlet("/jsp/History")
public class History extends HttpServlet {
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		TicketDao dao=TicketDao.getInstance(response);
		ArrayList<BusHistory> history = null;
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		try {
			history = dao.getHistory(user.getId()); //it will select the particular persons booked details
			LogFile.writeToLog(user.getId()+" history was collected successfully");
		} catch (Exception e) {
			LogFile.writeToLog(user.getId()+" history was not collected :(");
			e.printStackTrace();
		}
	    session.setAttribute("history", history); //and stored it into the session
	    RequestDispatcher dispatcher = request.getRequestDispatcher("History.jsp"); //redirected to the history.jsp
	    dispatcher.forward(request, response);
	}


}
