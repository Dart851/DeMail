package ru.t_systems.demail.controller;

import org.hibernate.validator.constraints.NotEmpty;

import ru.t_systems.demail.model.user.User;

public class Registration extends User {
//	private String userName;
//	@NotEmpty
//	@Size(min = 4, max = 20)
//	private String password;
	@NotEmpty
	private String confirmPassword;
//	@NotEmpty
//	@Email
//	private String email;
//
//	String country;
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public String getUserName() {
//		return userName;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
//
	public String getConfirmPassword() {
		return confirmPassword;
	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public String getCountry() {
//		return country;
//	}
//
//	public void setCountry(String country) {
//		this.country = country;
//	}

}