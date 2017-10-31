package com.retail.asales.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the login_detail database table.
 * 
 */
@Entity
@Table(name="login_detail")
@NamedQuery(name="LoginDetail.findAll", query="SELECT l FROM LoginDetail l")
public class LoginDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	@Column(name="LAST_LOGIN")
	private Timestamp lastLogin;

	public LoginDetail() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getLastLogin() {
		return this.lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	
}