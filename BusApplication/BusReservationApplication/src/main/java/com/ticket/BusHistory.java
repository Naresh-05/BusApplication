package com.ticket;

public class BusHistory {
	int ticketId,busId,ticket,cost,userId;
	String from,to,date,time,mobile,name;
	
	//this class is used for bus and ticket details combine in formate
	
	//it getters and setter for this class
	
	public int getTicketId() {
		return ticketId;
	}

	public int getUserId() {
		return userId;
	}

    public String getMobile() {
    	return mobile;
    }
    
    public String getName() {
    	return name;
    }

	public int getBusId() {
		return busId;
	}





	public int getTicket() {
		return ticket;
	}





	public int getCost() {
		return cost;
	}





	public String getFrom() {
		return from;
	}





	public String getTo() {
		return to;
	}





	public String getDate() {
		return date;
	}





	public String getTime() {
		return time;
	}





	public void setTicketId(int ticketId) {
		this.ticketId = ticketId;
	}

	public void setUsertId(int userId) {
		this.userId = userId;
	}

    public void setMobile(String mobile) {
    	this.mobile=mobile;
    }

    public void setName(String name) {
    	this.name=name;
    }

	public void setBusId(int busId) {
		this.busId = busId;
	}





	public void setTicket(int ticket) {
		this.ticket = ticket;
	}





	public void setCost(int cost) {
		this.cost = cost;
	}





	public void setFrom(String from) {
		this.from = from;
	}





	public void setTo(String to) {
		this.to = to;
	}





	public void setDate(String date) {
		this.date = date;
	}





	public void setTime(String time) {
		this.time = time;
	}





	@Override
	public String toString() {
		return "BusHistory [ticketId=" + ticketId + ", busId=" + busId + ", ticket=" + ticket + ", cost=" + cost
				+ ", from=" + from + ", to=" + to + ", date=" + date + ", time=" + time + "]";
	}
	
}
