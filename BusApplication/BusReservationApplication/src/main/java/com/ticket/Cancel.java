package com.ticket;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import com.log.LogFile;




@WebServlet("/jsp/Cancel")
public class Cancel extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			TicketDao dao=TicketDao.getInstance(response);
			HttpSession session=request.getSession();
			BusHistory bus=(BusHistory) session.getAttribute("cancelBus"); //it will get the cancelBus attribute from the CancelTicket
			dao.cancelTicket(bus); //it  send the bus object to the cancelTicket method
			LogFile.writeToLog(bus.getTicketId()+" was cancelled successfully");
			response.sendRedirect("History"); //it will redirected to history.jsp
		} catch (Exception e) {
			LogFile.writeToLog("faild to cancel ticket",e);
			e.printStackTrace();
		}
	}

}
