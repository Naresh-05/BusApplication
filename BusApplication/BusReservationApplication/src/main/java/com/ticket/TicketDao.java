package com.ticket;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.bus.Bus;
import com.login.user.User;

import jakarta.servlet.http.HttpServletResponse;

public class TicketDao {
		
		private static TicketDao ticketDao=null;
		private Connection conn=null;
		private PreparedStatement preparedStatement=null;
		private ResultSet rs=null;
		private static HttpServletResponse response;
		
		public static TicketDao getInstance(HttpServletResponse res) throws IOException { //singleton method
			response=res;
			if(ticketDao==null) {
				ticketDao=new TicketDao();
			}
			return ticketDao;
		}
		
		private TicketDao() {} //for singleton method it will private constructor
		
		protected void connect() throws SQLException, IOException{
	        if (conn == null || conn.isClosed()) { //if connection is null or connection is closed it will create the connection
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
	        if (conn != null && !conn.isClosed()) { //if connection is not null and connection is not closed it will closed the connection
	            conn.close();
	        }
	    }
	    
	    public void conformTicket(String seats,User user,Bus bus) throws SQLException { //conform the ticket and enter the bus deatisl and customer detaisl in ticket table
			try {
				connect();
				preparedStatement=conn.prepareStatement("INSERT INTO bus_reservation_application.booked_ticket (bus_id,user_id,ticket,cost) VALUES (?,?,?,?)");
				preparedStatement.setString(1,String.valueOf(bus.getId()));
				preparedStatement.setString(2,String.valueOf(user.getId()));
				preparedStatement.setString(3,seats);
				preparedStatement.setString(4,String.valueOf(bus.getCost()*Integer.parseInt(seats)));
				preparedStatement.executeUpdate();
				preparedStatement=conn.prepareStatement("UPDATE bus_reservation_application.bus SET seats=? WHERE bus_id=?");
				preparedStatement.setString(1,String.valueOf(bus.getSeats()-Integer.parseInt(seats)));
				preparedStatement.setString(2,String.valueOf(bus.getId()));
				preparedStatement.executeUpdate();
			}
			catch(Exception e) {
				e.printStackTrace();
			}finally {
				disconnect();
			}
			
		}
	    
	    public ArrayList<BusHistory> getHistory(int userId) throws IOException, SQLException{ //it will gives the all the travel history of particular customer
	    	ArrayList<BusHistory> buses=null;
			try {
				connect();
				preparedStatement=conn.prepareStatement("SELECT booked_ticket.ticket_id, bus.bus_id,bus.p1,bus.p2,bus.date,bus.time,booked_ticket.ticket,booked_ticket.cost FROM booked_ticket INNER JOIN bus ON booked_ticket.bus_id = bus.bus_id where booked_ticket.user_id=?;");
				preparedStatement.setString(1,String.valueOf(userId));
				rs=preparedStatement.executeQuery();
				for(int i=0;rs.next();i++) {
					if(i==0) {
						buses=new ArrayList<>();
					}
					BusHistory b=new BusHistory();
					b.setTicketId(rs.getInt("ticket_id"));
					b.setBusId(rs.getInt("bus_id"));
					b.setFrom(rs.getString("p1"));
					b.setTo(rs.getString("p2"));
					b.setDate(rs.getString("date"));
					b.setTime(rs.getString("time"));
					b.setCost(rs.getInt("cost"));
					b.setTicket(rs.getInt("ticket"));
					buses.add(b);
				}
			}catch(NullPointerException e){
					System.out.println(e);
					PrintWriter out=response.getWriter();
				  out.println("<script type=\"text/javascript\">");
				  out.println("alert('Server not responding please try again! SERVER DOWN? ');");
				  out.println("location='/BusReservationApplication/jsp/Bus.jsp';");
				  out.println("</script>");
			}catch(Exception e){
				System.out.println(e);
			}finally {
				disconnect();
			}
			return buses;
	    }
	    
	    public void cancelTicket(BusHistory bus) throws SQLException, IOException { //it is for cancel of ticket
	    	connect();
	    	 String sql = "SELECT seats from bus where bus_id = ?";
		     preparedStatement = conn.prepareStatement(sql);
		     preparedStatement.setInt(1, bus.getBusId());
		     rs=preparedStatement.executeQuery();
		     if(rs.next()) {
		    	 sql = "UPDATE bus SET seats=? where bus_id = ?";
			     preparedStatement = conn.prepareStatement(sql);
			     preparedStatement.setInt(1,rs.getInt("seats")+bus.getTicket());
			     preparedStatement.setInt(2, bus.getBusId());
			     preparedStatement.executeUpdate();
		     }
		    sql = "DELETE FROM booked_ticket where ticket_id = ?";
	        preparedStatement = conn.prepareStatement(sql);
	        preparedStatement.setInt(1,bus.getTicketId());
	        preparedStatement.executeUpdate();
	        disconnect();
	    }
	    
	    public ArrayList<BusHistory> getAllDeatil() throws IOException, SQLException{ //it will gives the all the details of ticket booked
	    	ArrayList<BusHistory> buses=null;
			try {
				connect();
				preparedStatement=conn.prepareStatement("SELECT c.ticket_id, c.bus_id, b.p1, b.p2, b.date, b.time, c.user_id, a.name, a.mobile, c.ticket, c.cost FROM  bus_reservation_application.booked_ticket AS c INNER JOIN bus_reservation_application.bus AS b ON c.bus_id = b.bus_id INNER JOIN bus_reservation_application.user AS a ON c.user_id = a.user_id");
				rs=preparedStatement.executeQuery();
				for(int i=0;rs.next();i++) {
					if(i==0) {
						buses=new ArrayList<>();
					}
					BusHistory b=new BusHistory();
					b.setTicketId(rs.getInt("ticket_id"));
					b.setBusId(rs.getInt("bus_id"));
					b.setFrom(rs.getString("p1"));
					b.setTo(rs.getString("p2"));
					b.setDate(rs.getString("date"));
					b.setTime(rs.getString("time"));
					b.setCost(rs.getInt("cost"));
					b.setTicket(rs.getInt("ticket"));
					b.setUsertId(rs.getInt("user_id"));
					b.setName(rs.getString("name"));
					b.setMobile(rs.getString("mobile"));
					buses.add(b);
				}
			}catch(NullPointerException e){
					System.out.println(e);
					PrintWriter out=response.getWriter();
				  out.println("<script type=\"text/javascript\">");
				  out.println("alert('Server not responding please try again! SERVER DOWN? ');");
				  out.println("location='/BusReservationApplication/jsp/Bus.jsp';");
				  out.println("</script>");
			}catch(Exception e){
				System.out.println(e);
			}finally {
				disconnect();
			}
			return buses;
	    }
	    public ArrayList<BusHistory> getAllDeatil(String id) throws IOException, SQLException{ //it will get all details of the particular bus 
	    	ArrayList<BusHistory> buses=null;
			try {
				connect();
				preparedStatement=conn.prepareStatement("SELECT c.ticket_id, c.bus_id, b.p1, b.p2, b.date, b.time, c.user_id, a.name, a.mobile, c.ticket, c.cost FROM  bus_reservation_application.booked_ticket AS c INNER JOIN bus_reservation_application.bus AS b ON c.bus_id = b.bus_id INNER JOIN bus_reservation_application.user AS a ON c.user_id = a.user_id  WHERE c.bus_id=?");
				preparedStatement.setString(1,id);
				rs=preparedStatement.executeQuery();
				for(int i=0;rs.next();i++) {
					if(i==0) {
						buses=new ArrayList<>();
					}
					BusHistory b=new BusHistory();
					b.setTicketId(rs.getInt("ticket_id"));
					b.setBusId(rs.getInt("bus_id"));
					b.setFrom(rs.getString("p1"));
					b.setTo(rs.getString("p2"));
					b.setDate(rs.getString("date"));
					b.setTime(rs.getString("time"));
					b.setCost(rs.getInt("cost"));
					b.setTicket(rs.getInt("ticket"));
					b.setUsertId(rs.getInt("user_id"));
					b.setName(rs.getString("name"));
					b.setMobile(rs.getString("mobile"));
					buses.add(b);
				}
			}catch(NullPointerException e){
					System.out.println(e);
					PrintWriter out=response.getWriter();
				  out.println("<script type=\"text/javascript\">");
				  out.println("alert('Server not responding please try again! SERVER DOWN? ');");
				  out.println("location='/BusReservationApplication/jsp/Bus.jsp';");
				  out.println("</script>");
			}catch(Exception e){
				System.out.println(e);
			}finally {
				disconnect();
			}
			return buses;
	    }
}
