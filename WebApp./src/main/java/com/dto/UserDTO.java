package com.dto;

import java.awt.*;

public class UserDTO {

	private String name;
	private String surname;
	private String email;
	private String password;
	private Image avatar;

	public UserDTO(String name, String surname, String email, String password/*, Image avatar*/) {
		this.name = name;
		this.surname = surname;
		this.email = email;
		this.password = password;
//		this.avatar = avatar;

	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Image getAvatar() {
		return avatar;
	}

	public void setAvatar(Image avatar) {
		this.avatar = avatar;
	}
}