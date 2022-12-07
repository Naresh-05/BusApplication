package com.bus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;


import com.bus.dao.BusDao;
import com.log.LogFile;

@SuppressWarnings("serial")
@WebServlet("/jsp/BookTicket")
public class BookTicket extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusDao dao=BusDao.getInstance(response);
		Bus bus=new Bus();
		try {
			bus = dao.getDetail(request.getParameter("id"));  //it will get id from Bus.jsp as dynamic url and it collect the all detail about the picked bus
			LogFile.writeToLog(bus.getId()+" bus detail will fetched successfully.");
		} catch (Exception e) {
			LogFile.writeToLog("picked bus was not compledted ",e);
			e.printStackTrace();
		}
		HttpSession session=request.getSession();
	    session.setAttribute("pickedBus", bus); //it will set the attribute to picked bus
	    RequestDispatcher dispatcher = request.getRequestDispatcher("BookTicket.jsp");  //redirected to the BookTicket page
	    dispatcher.forward(request, response);
		}
	}

