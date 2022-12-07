package com.login.user;

public class BuildUser {
	
	//it is buildUser class it use for loading to User constructor without follow the order of constructor parameter
	
	String email;
	String password;
	String name;
	String mobile;
	String address;
	int id;
	
	//getters and setter of this class
	
	public BuildUser setEmail(String email) {
		this.email = email;
		return this;
	}
	public BuildUser setId(int id) {
		this.id=id;
		return this;
	}
	public BuildUser setPassword(String password) {
		this.password = password;
		return this;
	}
	public BuildUser setName(String name) {
		this.name = name;
		return this;
	}
	public BuildUser setMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}
	public BuildUser setAddress(String address) {
		this.address = address;
		return this;
	}
	
	//it will create the object of user class
	public User getUser() {
		return new User(this.id,this.email, this.password, this.name, this.mobile,this.address);
	}

}
