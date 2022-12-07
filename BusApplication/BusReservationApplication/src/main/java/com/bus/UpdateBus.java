package com.bus;

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
@WebServlet("/jsp/UpdateBus")
public class UpdateBus extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id"); //it will get the bus id from the MaintainBus.jsp
		BusDao dao=BusDao.getInstance(response);
		try {
			HttpSession session=(HttpSession) request.getSession();
			session.setAttribute("updateBus",dao.getDetail(id));  //it will collect the all the details about the particular bus
			LogFile.writeToLog(id+" bus detail was updated successfully");
			response.sendRedirect("UpdateBus.jsp"); //and it will redirected to the updateBus.jsp
		} catch (Exception e) {
			LogFile.writeToLog(id+" bus detail was faild to update",e);
			e.printStackTrace();
		}
	}

}
