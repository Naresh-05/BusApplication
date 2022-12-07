package com.login.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.login.user.User;

import jakarta.servlet.http.HttpServletResponse;

public class LoginDao {
	
	//it will for user login detail
	private static LoginDao loginDao=null;
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	private Statement statement=null;
	private ResultSet rs=null;
	private static HttpServletResponse response;
	
	public static LoginDao getInstance(HttpServletResponse res) throws IOException { //singleton method
		response=res;
		if(loginDao==null) {
			loginDao=new LoginDao();
		}
		return loginDao;
	}
	
	private LoginDao() {} //for singleton method it will as private constructor
	
	protected void connect() throws SQLException, IOException{
        if (conn == null || conn.isClosed()) { //when connection is null or connection is closed it will established the connection
        	try {
    			Class.forName("com.mysql.jdbc.Driver");
    			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bus_reservation_application", "root", "12345");
    		}catch(Exception e) {
    			  e.printStackTrace();
    			  PrintWriter out=response.getWriter();
    			  out.println("<script type=\"text/javascript\">");
    			  out.println("alert('Server not responding please try again! SERVER DOWN? ');");
    			  out.println("location='/BusReservationApplication/jsp/Login.jsp';");
    			  out.println("</script>");
    		}
        }
    }
     
    protected void disconnect() throws SQLException {
        if (conn != null && !conn.isClosed()) { //if connection is not closed and connection is not null it will close the connection
            conn.close();
        }
    }
	public boolean check(User user) throws IOException, SQLException { //this  method will check the given username and password mached or not
		
		try {
			connect();
			preparedStatement=conn.prepareStatement("SELECT * FROM user where email=? and password=?"); //it will select the statement based on email and passowrd
			preparedStatement.setString(1,user.getEmail());
			preparedStatement.setString(2,user.getPassword());
			rs=preparedStatement.executeQuery();
			if(rs.next()) { //it statement exist it will get all detail about user and it will return true
				user.setName(rs.getString("name"));
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
				user.setId(rs.getInt("user_id"));
				user.validUser=true; //it will set the validUser flag
				if(user.getEmail().equalsIgnoreCase("admin@zoho.com")) { //again it will check the validAdmin 
					user.validAdmin=true; //it will set the validAdmin flag
				}
				return true;
			}
		}catch(NullPointerException e){ 
				System.out.println(e);
				PrintWriter out=response.getWriter();
			  out.println("<script type=\"text/javascript\">");
			  out.println("alert('Server not responding please try again! SERVER DOWN? ');");
			  out.println("location='/BusReservationApplication/jsp/Login.jsp';");
			  out.println("</script>");
		}catch(Exception e){
			System.out.println(e);
		}finally {
			disconnect();
		}
		
		return false; //if not a valid user it will return false
	}
	
	public boolean setUser(User user) throws SQLException { //it will set the user user
		try {
			connect(); //getting all details about the user and user object
			preparedStatement=conn.prepareStatement("INSERT INTO bus_reservation_application.user (email,password,name,mobile) VALUES (?,?,?,?)");
			preparedStatement.setString(1,user.getEmail());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3,user.getName());
			preparedStatement.setString(4,user.getMobile());
			int row=preparedStatement.executeUpdate(); //it will insert the user in db
			return row==1;//if successful execution of insert it will gives 1 it return true otherwise give false
		}catch(Exception e) {
			e.printStackTrace();
			
		}finally {
			disconnect();
		}
		return false; //by default it will return false
		
	}
	
	 public boolean update(User updateUser) throws IOException, SQLException { //it will used for update the user pofile 
			
			try {
				connect();
				preparedStatement=conn.prepareStatement("SELECT * FROM bus_reservation_application.user where user_id=?");
				preparedStatement.setInt(1,updateUser.getId());
				
				rs=preparedStatement.executeQuery();
				
				rs.next();
				
			String sql="UPDATE bus_reservation_application.user SET ";
			
			try {
				if(!updateUser.getName().equals(rs.getString("name"))) { //it will check the given data and existing data are same means it will not do otherwise it will change the update
					sql+="name=\""+updateUser.getName()+"\", ";
				}
			}catch(Exception e) {}
			
			try {
				if(!updateUser.getMobile().equals(rs.getString("mobile"))) {
					sql+="mobile=\""+updateUser.getMobile()+"\", ";
				}
			}catch(Exception e) {}
			
			try {
				if(!updateUser.getAddress().equals(rs.getString("address"))) {
					sql+="address=\""+updateUser.getAddress()+"\", ";
				}
			}catch(Exception e) {}
			
			
			
			if(sql.charAt(sql.length()-2)==',') {
				StringBuilder sb=new StringBuilder(sql);
				sb.deleteCharAt(sql.length()-2);
				sql=new String(sb);
			}
			sql+=" WHERE user_id=\""+updateUser.getId()+"\";";
			
			System.out.println(sql);
			
			statement=conn.createStatement();
			statement.executeUpdate(sql);
			return true;
			
			  
			}catch(Exception e) { //no changes made it will execute
				System.out.println(e);
				PrintWriter out=response.getWriter();
				  out.println("<script type=\"text/javascript\">");
				  out.println("alert('Something Went wrong? Plase update any one data to save.');");
				  out.println("location='EditProfile.jsp';");
				  out.println("</script>");
			}finally {
				disconnect();
			}
			return false;
	        
		}
}
