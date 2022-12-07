package com.ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

import com.bus.Bus;
import com.log.LogFile;
import com.login.user.User;

@SuppressWarnings("serial")
@WebServlet("/jsp/ConformTicket")
public class ConformTicket extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		TicketDao dao=TicketDao.getInstance(response);
		HttpSession session=request.getSession();
		Bus bus=(Bus)session.getAttribute("pickedBus"); //it will get the bus details
		User user=(User)session.getAttribute("user"); //it will get the use details
		try {
			dao.conformTicket(request.getParameter("seats"),user,bus); //it will conform the ticket
			LogFile.writeToLog("ticket will conformed successfully");
		} catch (SQLException e) {
			LogFile.writeToLog("failed to conform ticket",e);
			e.printStackTrace();
		}
		response.sendRedirect("/BusReservationApplication/jsp/Bus.jsp");

	}

}
