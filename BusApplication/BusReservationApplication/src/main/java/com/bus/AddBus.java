package com.bus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import com.bus.dao.BusDao;
import com.log.LogFile;

@SuppressWarnings("serial")
@WebServlet("/jsp/AddBus")
public class AddBus extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Bus bus=new Bus();   //getting the all the info about bus it will passed from AddBus.jsp
		bus.setFrom(request.getParameter("from"));
		bus.setTo(request.getParameter("to"));
		bus.setDate(request.getParameter("date"));
		bus.setSeats(Integer.parseInt(request.getParameter("seats")));
		bus.setCost(Integer.parseInt(request.getParameter("cost")));
		bus.setTime(request.getParameter("time"));
		BusDao dao=BusDao.getInstance(response);
		try {
			dao.addBus(bus); //it will create the a new bus
			LogFile.writeToLog(bus.getId()+" will added successfully");
			PrintWriter out=response.getWriter();
			  out.println("<script type=\"text/javascript\">");
			  out.println("alert('Bus Added successfully!';");
			  out.println("location='AddBus.jsp';");
			  out.println("</script>");
		} catch (Exception e) {
			LogFile.writeToLog("faild to add bus ",e);
			e.printStackTrace();
		}
		response.sendRedirect("AddBus.jsp"); //again redirected to the AddBus page
	}

}
