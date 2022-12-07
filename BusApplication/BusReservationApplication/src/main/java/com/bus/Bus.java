package com.bus;

public class Bus {
	
	    //this class is used to store the bus data and reuse it by make use of this class
	
		String from,to,date,time;
		int id,seats,cost,totalSeats;
		
		public Bus(int id,String from,String to,String date,int seats,String time,int cost,int totalSeats){
			this.id=id;
			this.from=from;
			this.to=to;
			this.date=date;
			this.seats=seats;
			this.time=time;
			this.cost=cost;
			this.totalSeats=totalSeats;
		}
		
		public Bus() {}

		//all getters and setter are below
		
		public String getFrom() {
			return from;
		}

		public void setTotalSeats(int seats) {
			this.totalSeats=seats;
		}
		public int getTotalSeats() {
			return totalSeats;
		}
		public void setFrom(String from) {
			this.from = from;
		}

		public String getTo() {
			return to;
		}

		public void setTo(String to) {
			this.to = to;
		}

		public String getDate() {
			return date;
		}

		public void setDate(String date) {
			this.date = date;
		}

		public String getTime() {
			return time;
		}

		public void setTime(String time) {
			this.time = time;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getSeats() {
			return seats;
		}

		public void setSeats(int seats) {
			this.seats = seats;
		}

		public int getCost() {
			return cost;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		@Override
		public String toString() {
			return "Bus [from=" + from + ", to=" + to + ", date=" + date + ", time=" + time + ", id=" + id + ", seats="
					+ seats + ", cost=" + cost + "]";
		}
		
		
}
