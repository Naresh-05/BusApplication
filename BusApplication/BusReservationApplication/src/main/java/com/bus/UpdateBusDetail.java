package com.bus;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.bus.dao.BusDao;
import com.log.LogFile;
import com.login.user.User;

@SuppressWarnings("serial")
@WebServlet("/jsp/UpdateBusDetail")
public class UpdateBusDetail extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request. getSession();
        
        User user=(User)session.getAttribute("user");
        
        if(!user.validAdmin) //it will validate the authorized admin only 
   		{
   			response.sendRedirect("Index.jsp");
   		}
        
        Bus b=new Bus();
        String str;
       
        try {
        	str=request.getParameter("id").trim(); //it will get the id from UpdateBus.jsp
        	b.setId(Integer.parseInt(str));
        }catch(Exception e) {e.printStackTrace();}
        
        
        try {
        	str=request.getParameter("from").trim(); //it will get the From from UpdateBus.jsp
        	b.setFrom(str);
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("to").trim();//it will get the to from UpdateBus.jsp
        	b.setTo(str);
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("date").trim(); //it will get the date from UpdateBus.jsp
        	b.setDate(str);
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("time").trim(); //it will get the time from UpdateBus.jsp
        	b.setTime(str);
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("cost").trim(); //it will get the cost from UpdateBus.jsp
        	b.setCost(Integer.parseInt(str));
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("seats").trim(); //it will get the seats from UpdateBus.jsp
        	b.setTotalSeats(Integer.parseInt(str));
        }catch(Exception e) {e.printStackTrace();}
        
        BusDao dao=BusDao.getInstance(response);
        
        try {
			if(dao.updateBus(b)) { //it will updated by passing object
				LogFile.writeToLog(b.getId()+" bus was updated successfuly in DB");
			      PrintWriter out=response.getWriter();
				  out.println("<script type=\"text/javascript\">");
				  out.println("alert('Updated Successfully');");
				  out.println("location='MaintainBus.jsp';");
				  out.println("</script>");
			}
		} catch (Exception e) {
			LogFile.writeToLog(" failed to updae the bus detaisl in DB"+b.getId(),e);
			e.printStackTrace();
		}
	}

}
