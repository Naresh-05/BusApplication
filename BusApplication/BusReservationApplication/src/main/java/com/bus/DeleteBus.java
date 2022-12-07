package com.bus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.bus.dao.BusDao;
import com.log.LogFile;

@SuppressWarnings("serial")
@WebServlet("/jsp/DeleteBus")
public class DeleteBus extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id=request.getParameter("id"); //it will get the ticket id from the MaintainBus.jsp
		BusDao dao=BusDao.getInstance(response);
		try {
			dao.deleteBus(id); //it will execute the delete Bus 
			LogFile.writeToLog(id+ "bus id was deleted successfully.");
		} catch (Exception e) {
			LogFile.writeToLog(" faild to delete bus "+ id,e);
			e.printStackTrace();
		}
	}

	

}
