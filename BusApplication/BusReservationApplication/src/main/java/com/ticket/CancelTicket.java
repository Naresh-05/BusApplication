package com.ticket;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.log.LogFile;

@SuppressWarnings("serial")
@WebServlet("/jsp/CancelTicket")
public class CancelTicket extends HttpServlet {
       
    
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			int id=Integer.parseInt(request.getParameter("id"));//it will get the id form History.jsp
			HttpSession session=request.getSession();
			ArrayList<BusHistory> history = (ArrayList<BusHistory>) session.getAttribute("history");
			for(BusHistory b: history) {
				if(b.ticketId==id) { //it will select the particular bus form the all the bus details
					LogFile.writeToLog("particular ticket was found successfully");
					session.setAttribute("cancelBus", b);
					response.sendRedirect("ConformCancel.jsp");
				}
			}
		} catch (Exception e) {
			LogFile.writeToLog("failed to foudn ticket id",e);
			e.printStackTrace();
		}
	}


}
