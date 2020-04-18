package models;

import java.util.Random;

import utils.Utils;

public class User {
	private Random r = new Random();
	private String username;
	private String password;
	private String name;
	private String surname;
	private int userCode;
	
	public User(String name, String surname, String username, int userCode) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.userCode = userCode;
	}
	
	public User(String name, String surname, String username, String password) {
		this.setName(name);
		this.setSurname(surname);
		this.setUsername(username);
		this.setPassword(password);
		this.setUserCode();
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		if (username != null && !username.isEmpty()) {
			this.username = username;
		} else {
			throw new IllegalArgumentException("Username Is Not Valid");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if (password != null && !password.isEmpty() && password.length() >= 7) {
			this.password = Utils.MD5(password);
		} else {
			throw new IllegalArgumentException("Password Is Not Valid");
		}
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name != null && !name.isEmpty()) {
			this.name = name;
		} else {
			throw new IllegalArgumentException("Name Is Not Valid");
		}
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		if (surname != null && !surname.isEmpty()) {
			this.surname = surname;
		} else {
			throw new IllegalArgumentException("Surname Is Not Valid");
		}
	}

	public int getUserCode() {
		return userCode;
	}

	public void setUserCode() {
		this.userCode = r.nextInt((1000 - 1) + 1) + 1;
	}
}
