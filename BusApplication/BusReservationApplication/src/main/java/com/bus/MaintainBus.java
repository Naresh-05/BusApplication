package com.bus;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;

import com.bus.dao.BusDao;
import com.log.LogFile;

@SuppressWarnings("serial")
@WebFilter("/jsp/MaintainBus.jsp")
public class MaintainBus extends HttpFilter implements Filter {
       
 
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		BusDao dao=BusDao.getInstance((HttpServletResponse)response);
		HttpSession session=((HttpServletRequest) request).getSession();
		ArrayList<Bus> buses=null;
		try {
			buses=dao.getAllBus(); //it will collect the all the bus details into the buses 
			LogFile.writeToLog("all the bus detials collected successfully");
		} catch (Exception e) {
			LogFile.writeToLog("faild to get all the bus details",e);
			e.printStackTrace();
		}
		session.setAttribute("main",buses); //and set the main attribute it will used by MaintainBus.jsp
		chain.doFilter(request, response);
	}

	
}
