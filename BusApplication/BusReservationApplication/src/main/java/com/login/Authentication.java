package com.login;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.io.PrintWriter;

import com.log.LogFile;
import com.login.dao.LoginDao;
import com.login.user.BuildUser;
import com.login.user.User;

@SuppressWarnings("serial")
@WebFilter("/jsp/Login") //it is the authentication filder check the valid user and allocation session time of user
public class Authentication extends HttpFilter implements Filter {
       
    
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		HttpServletResponse res=(HttpServletResponse)response;
		HttpServletRequest  req=(HttpServletRequest) request;	
		
		LoginDao dao=LoginDao.getInstance(res);
			User user=new BuildUser().setEmail(request.getParameter("email")).setPassword(request.getParameter("pass")).getUser();
			try {
				if (dao.check(user)) {   //it is an valid user flag set it will allow the user to use website
					HttpSession session = req.getSession(); 
					session.setAttribute("user",user); //and set the user details in session attribute
					session.setMaxInactiveInterval(2*60); //and set the session timeing as 2 min
					LogFile.writeToLog(user.getEmail()+" was authanticated successfully");
					chain.doFilter(request, response); //all done it will go next chain otherwise it will reflect back
				}
				else {
					LogFile.writeToLog("invalid email or password");
					  PrintWriter out=res.getWriter(); 
					  out.println("<script type=\"text/javascript\">");
					  out.println("alert('Incorrect Email or Password!')");
					  out.println("location='/BusReservationApplication/jsp/Login.jsp';");
					  out.println("</script>");
					
				}
			} catch (Exception e) {
				LogFile.writeToLog("failed to authorize user",e);
				e.printStackTrace();
			}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
