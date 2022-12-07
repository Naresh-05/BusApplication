package com.login;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
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
@WebServlet("/jsp/Update")
public class Update extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request. getSession();
        
        User user=(User)session.getAttribute("user");
        
        if(user==null) //if user is not signin it will redirected to the index page
   		{
   			response.sendRedirect("Index.jsp");
   		}
        
        BuildUser bu=new BuildUser().setId(user.getId()).setEmail(user.getEmail());
        String str;
       
        try {
        	str=request.getParameter("name").trim(); //get the data form the Editprofile.jsp
        	bu.setName(str);
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("mobile").trim();
        	bu.setMobile(str);
        }catch(Exception e) {e.printStackTrace();}
        
        try {
        	str=request.getParameter("address").trim();
        	bu.setAddress(str);
        }catch(Exception e) {}
        
        
        
        User updateUser=bu.getUser();
        
        
        LoginDao dao=LoginDao.getInstance(response);
        
        try {
			if(dao.update(updateUser)) { //it will update the user details
				LogFile.writeToLog(user.getEmail()+" detail was updated successfully");
				  user.setAddress(updateUser.getAddress());
				  user.setMobile(updateUser.getMobile());
				  user.setName(updateUser.getName());
			      PrintWriter out=response.getWriter();
				  out.println("<script type=\"text/javascript\">");
				  out.println("alert('Updated Successfully');");
				  out.println("location='EditProfile.jsp';");
				  out.println("</script>");
			}
		} catch (Exception e) {
			LogFile.writeToLog("faild to update use detaisl someting went wrong!",e);
			e.printStackTrace();
		}
	}

}
