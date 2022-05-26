package com.gyp.chatapp.dto;

/*
 * Data Transfer Object
 */

public class UserDTO {
	private String userid;
	private char[] password;
	private String email;
	private String phno;
	private String city;
	
	public UserDTO(String userid, char[] password) {
		this.userid=userid;
		this.password=password;
	}

	public String getUserid() {
		return userid;
	}

	public char[] getPassword() {
		return password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
}
