package com.bus.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.bus.Bus;

import jakarta.servlet.http.HttpServletResponse;

public class BusDao {
	
	//this class for Data Access Object
	
	private static BusDao loginDao=null;
	private Connection conn=null;
	private PreparedStatement preparedStatement=null;
	private ResultSet rs=null;
	private static HttpServletResponse response=null;
	
	public static BusDao getInstance(HttpServletResponse res) throws IOException { //singleton method
		response=res;
		if(loginDao==null) {
			loginDao=new BusDao(); //object will create only once
		}
		return loginDao;
	}
	
	private BusDao() {} //private constructor for singleton method
	
	protected void connect() throws SQLException, IOException{
        if (conn == null || conn.isClosed()) { //when connection is null or closed it will execute
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
        if (conn != null && !conn.isClosed()) {//if connection is not null or it will not closed it close the connection
            conn.close();
        }
    }
    
public Bus getDetail(String id) throws IOException, SQLException {
	Bus bus=new Bus();
		try {
			connect();
			
			preparedStatement=conn.prepareStatement("SELECT * FROM bus where bus_id=?");  //it will select the particular bus as passed bus id
			preparedStatement.setString(1,id);
			rs=preparedStatement.executeQuery();
			if(rs.next()) {
				bus.setId(rs.getInt("bus_id"));
				bus.setFrom(rs.getString("p1"));
				bus.setTo(rs.getString("p2"));
				bus.setDate(rs.getString("date"));
				bus.setSeats(rs.getInt("seats"));
				bus.setTime(rs.getString("time"));
				bus.setCost(rs.getInt("cost"));
				bus.setTotalSeats(rs.getInt("total_seats"));
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
		return bus; //it will return the particular bus details
	}
    
public ArrayList<Bus> getBusForBook(Bus bus) throws IOException, SQLException {
		
	    ArrayList<Bus> buses=null;
		try {
			connect();
			preparedStatement=conn.prepareStatement("SELECT * FROM bus_reservation_application.bus where p1=? and p2=? and date=?"); //selecting the from point1 to point2 and date selected bus only show 
			preparedStatement.setString(1,bus.getFrom());
			preparedStatement.setString(2,bus.getTo());
			preparedStatement.setString(3,bus.getDate());
			rs=preparedStatement.executeQuery();
			for(int i=0;rs.next();i++) {
				if(i==0) {
					buses=new ArrayList<>();
				}
				Bus b=new Bus(rs.getInt("bus_id"),rs.getString("p1"),rs.getString("p2"),rs.getString("date"),rs.getInt("seats"),rs.getString("time"),rs.getInt("cost"),rs.getInt("total_seats"));
				buses.add(b); //adding the bus into buses array
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
		return buses; // it return the bus list
		
	}

	public ArrayList<String> getFrom() throws SQLException{
		ArrayList<String> from=new ArrayList<>();
		try {
			connect();
			preparedStatement=conn.prepareStatement("SELECT DISTINCT p1 FROM bus_reservation_application.bus"); //it will select the from place without duplicate data
			rs=preparedStatement.executeQuery();
			while(rs.next()) {
				from.add(rs.getString("p1"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return from; //it return the point1 array
	}
	public ArrayList<String> getTo() throws SQLException{
		ArrayList<String> to=new ArrayList<>();
		try {
			connect();
			preparedStatement=conn.prepareStatement("SELECT DISTINCT p2 FROM bus_reservation_application.bus"); //it will select the to place without duplicate data
			rs=preparedStatement.executeQuery();
			while(rs.next()) {
				to.add(rs.getString("p2"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			disconnect();
		}
		return to; //it return the point2 array
	}
	public void addBus(Bus bus) throws SQLException, IOException {
		int row=0;
		try {
			connect();
			preparedStatement=conn.prepareStatement("INSERT INTO bus_reservation_application.bus (p1,p2,date,seats,cost,time,total_seats) VALUES (?,?,?,?,?,?,?)"); //it will add the new bus to db
			preparedStatement.setString(1,bus.getFrom());
			preparedStatement.setString(2,bus.getTo());
			preparedStatement.setString(3,bus.getDate());
			preparedStatement.setInt(4,bus.getSeats());
			preparedStatement.setInt(5,bus.getCost());
			preparedStatement.setString(6,bus.getTime());
			preparedStatement.setInt(7,bus.getSeats());
			row=preparedStatement.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			disconnect();
		}
		if(row!=1) { //if row not set 1 it will not add  a bus and show alert 
			  PrintWriter out=response.getWriter();
			  out.println("<script type=\"text/javascript\">");
			  out.println("alert('Bus not added. :(';");
			  out.println("location='/BusReservationApplication/jsp/AddBus.jsp';");
			  out.println("</script>");
		}else { //else it will add the bus successfully
			PrintWriter out=response.getWriter();
			  out.println("<script type=\"text/javascript\">");
			  out.println("alert('Bus Added successfully!';");
			  out.println("location='/BusReservationApplication/jsp/AddBus.jsp';");
			  out.println("</script>");
		}
	}

	public ArrayList<Bus> getAllBus() throws IOException, SQLException {
		ArrayList<Bus> buses=null;
		try {
			connect();
			preparedStatement=conn.prepareStatement("SELECT * FROM bus_reservation_application.bus"); //it will show the all registered bus details
			rs=preparedStatement.executeQuery();
			for(int i=0;rs.next();i++) {
				if(i==0) {
					buses=new ArrayList<>(); //if any one user is exist memory was allocated
				}
				Bus b=new Bus(rs.getInt("bus_id"),rs.getString("p1"),rs.getString("p2"),rs.getString("date"),rs.getInt("seats"),rs.getString("time"),rs.getInt("cost"),rs.getInt("total_seats"));
				buses.add(b);
			}
		}catch(NullPointerException e){ //db not connect it will execute
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
		return buses; //finally return all the buses details
	}

	public boolean updateBus(Bus b) throws IOException, SQLException {
		try {
			connect();
			preparedStatement=conn.prepareStatement("SELECT * FROM bus_reservation_application.bus where bus_id=?"); //it will for the update the particular bus 
			preparedStatement.setInt(1,b.getId());
			
			rs=preparedStatement.executeQuery();
			
			rs.next();
			
		String sql="UPDATE bus_reservation_application.bus SET ";
		
		try {
			if(!b.getFrom().equals(rs.getString("from"))) {
				sql+="p1=\""+b.getFrom()+"\", ";
			}
		}catch(Exception e) {}
		
		try {
			if(!b.getTo().equals(rs.getString("to"))) {
				sql+="p2=\""+b.getTo()+"\", ";
			}
		}catch(Exception e) {}
		
		try {
			if(!b.getDate().equals(rs.getString("date"))) {
				sql+="date=\""+b.getDate()+"\", ";
			}
		}catch(Exception e) {}
		
		try {
			if(!b.getTime().equals(rs.getString("time"))) {
				sql+="time=\""+b.getTime()+"\", ";
			}
		}catch(Exception e) {}
		
		try {
			if(!(b.getCost()==rs.getInt("cost"))) {
				sql+="cost=\""+b.getCost()+"\", ";
			}
		}catch(Exception e) {}
		
		try {
			if(!(b.getTotalSeats()==rs.getInt("total_seats"))) {
				sql+="total_seats=\""+b.getTotalSeats()+"\", ";
			}
		}catch(Exception e) {}
		
		
		
		
		if(sql.charAt(sql.length()-2)==',') {
			StringBuilder sb=new StringBuilder(sql);
			sb.deleteCharAt(sql.length()-2);
			sql=new String(sb);
		}
		sql+=" WHERE bus_id=\""+b.getId()+"\";";
		
		System.out.println(sql);
		
		Statement statement = conn.createStatement();
		statement.executeUpdate(sql);
		return true;
		
		  
		}catch(Exception e) {
			System.out.println(e);
			PrintWriter out=response.getWriter();
			  out.println("<script type=\"text/javascript\">");
			  out.println("alert('Something Went wrong? Plase update any one data to save.');");
			  out.println("location='UpdateBus.jsp';");
			  out.println("</script>");
		}finally {
			disconnect();
		}
		return false;
	}

	public void deleteBus(String id) throws SQLException, IOException {
		try {
			connect();
			preparedStatement=conn.prepareStatement("DELETE from bus where bus_id=?");  //it will select the particular bus as passed bus id
			preparedStatement.setString(1,id);
			int row=preparedStatement.executeUpdate();
			PrintWriter out=response.getWriter();
			if(row>0) { //it will only successful execution for the no reservation applied for particular bus
			  out.println("<script type=\"text/javascript\">");
			  out.println("alert('Bus was deleted successfully');");
			  out.println("location='MaintainBus.jsp';");
			  out.println("</script>");
			}else {
				out.println("<script type=\"text/javascript\">");
				  out.println("alert('Can't delete this bus because it was reserved by the client.');");
				  out.println("location='MaintainBus.jsp';");
				  out.println("</script>");
			}
		}catch(Exception e){
				System.out.println(e);
				response.sendRedirect("MaintainBus.jsp");
		}finally {
			disconnect();
		}
	}
}