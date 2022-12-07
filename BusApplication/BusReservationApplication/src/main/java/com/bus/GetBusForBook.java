package com.bus;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import com.bus.dao.BusDao;
import com.log.LogFile;


@SuppressWarnings("serial")
@WebServlet("/jsp/GetBusForBook")
public class GetBusForBook extends HttpServlet {
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BusDao dao=BusDao.getInstance(response);
		ArrayList<Bus> listBus = null;
		try {
			listBus = dao.getBusForBook(new Bus(0,request.getParameter("from"),request.getParameter("to"),request.getParameter("date"),0,null,0,0)); //it will get the from,to and date form the Bus.jsp and it will collect the all the bus details of particular constrains
			LogFile.writeToLog("successfull collection of from,to and date");
		} catch (Exception e) {
			LogFile.writeToLog("faild to fatch the GetBus for book",e);
			e.printStackTrace();
		}
	    request.setAttribute("listBus", listBus); //and set the all the details as litBus attribute
	    RequestDispatcher dispatcher = request.getRequestDispatcher("Bus.jsp"); 
	    dispatcher.forward(request, response);//and redirected to the bus.jsp
		}

}
