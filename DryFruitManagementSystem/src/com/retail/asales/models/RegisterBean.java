package com.retail.asales.models;

public class RegisterBean {
	private String userId;
	private String password;
	private String confirmPassword;
	private String userName;
	private String secAnswer;
	private String secureQuest;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSecAnswer() {
		return secAnswer;
	}

	public void setSecAnswer(String secAnswer) {
		this.secAnswer = secAnswer;
	}

	public String getSecureQuest() {
		return secureQuest;
	}

	public void setSecureQuest(String secureQuest) {
		this.secureQuest = secureQuest;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

}
