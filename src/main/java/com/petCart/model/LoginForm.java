package com.petCart.model;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginForm {

	@NotEmpty(message="username is required")
	private String username;
	
	@NotEmpty(message="password is required")
	private String password;

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getpassword() {
		return password;
	}

	public void password(String password) {
		this.password = password;
	}
	
	
}
