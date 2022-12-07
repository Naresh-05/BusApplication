package com.login.user;

public class User {
	
	   //this class for the User data store in the and retrive of data make use of this class object    
	
		String name,email,password,address,mobile;
		int id;
		public boolean validUser=false,validAdmin=false;
		
		public User(){}
		
		public User(int id,String email, String password, String name, String mobile, String address) {
			
			this.id=id;
			this.email = email;
			this.password = password;
			this.name = name;
			this.mobile = mobile;
			this.address=address;
		}
		
		//getter and setters of this class
		
		public boolean getValidUser() {
			return validUser;
		}
		
		public int getId() {
			return id;
		}
		
		public String getEmail() {
			return email;
		}



		public String getPassword() {
			return password;
		}



		public String getName() {
			return name;
		}


		public String getMobile() {
			return mobile;
		}

		public String getAddress() {
			return address;
		}
		
		public void setId(int id) {
			this.id=id;
		}

		public void setEmail(String email) {
			this.email = email;
		}



		public void setPassword(String password) {
			this.password = password;
		}



		public void setName(String name) {
			this.name = name;
		}


		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		
		public void setAddress(String address) {
			this.address=address;
		}


		@Override
		public String toString() {
			return "User ["+"id ="+id+", email=" + email + ", password=" + password + ", name=" + name + ", mobile="
					+ mobile + ", address="+ address+"]";
		}
}
