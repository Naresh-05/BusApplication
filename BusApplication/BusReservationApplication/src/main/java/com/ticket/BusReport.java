package com.ticket;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

import com.log.LogFile;

@SuppressWarnings("serial")
@WebFilter("/jsp/BusReport.jsp")
public class BusReport extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		jakarta.servlet.http.HttpSession session=((HttpServletRequest) request).getSession();
		
		TicketDao dao=TicketDao.getInstance((HttpServletResponse) response);
		ArrayList<BusHistory> bookedBuses=new ArrayList<>();
		String id=null;
		try {
			id=(String) request.getParameter("search"); 
			if(id==null||id=="") {  //if id is null or empty it will fetch all the bus booked details
				bookedBuses=dao.getAllDeatil();
				LogFile.writeToLog("all the bus reserved detaisl was get successfully");
			}else {
				bookedBuses=dao.getAllDeatil(id); //else it will display particular bus detail as output
				LogFile.writeToLog(id+" bus reserved detaisl was get successfully");
			}
		}catch(Exception e) {
			LogFile.writeToLog("faild to get the reserved details",e);
			e.printStackTrace();
		}
		session.setAttribute("bookedBuses",bookedBuses); // it will set the attribute		
		chain.doFilter(request, response);
	}


}
