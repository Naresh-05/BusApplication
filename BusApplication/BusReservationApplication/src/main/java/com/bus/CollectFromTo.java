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

import com.bus.dao.BusDao;
import com.log.LogFile;

@SuppressWarnings("serial")
@WebFilter("/jsp/Bus.jsp")
public class CollectFromTo extends HttpFilter implements Filter {
       
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		BusDao dao=BusDao.getInstance((HttpServletResponse) response);
		try {
			HttpServletRequest req=(HttpServletRequest) request;
			HttpSession session=req.getSession(); 
			session.setAttribute("fitFrom",dao.getFrom()); //it will get all the point1 place details and fetch it into the fitFrom attribute
			session.setAttribute("fitTo", dao.getTo()); //it will get all the point2 place details and fetch it into the firTi attribute
			LogFile.writeToLog("it will collected the from and to ");
		} catch (Exception e) {
			LogFile.writeToLog(" fail to fetch from and to ",e);
			e.printStackTrace();
		}
		chain.doFilter(request, response); //it will check the next chain
	}


}
