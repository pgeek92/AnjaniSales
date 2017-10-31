package com.retail.asales.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the secure_quest database table.
 * 
 */
@Entity
@Table(name="secure_quest")
@NamedQueries({
@NamedQuery(name="SecureQuest.findAll", query="SELECT s FROM SecureQuest s"),
@NamedQuery(name="SecureQuest.findByQuestion", query="SELECT s FROM SecureQuest s WHERE s.securityQuestion =:question")
})
public class SecureQuest implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_QUESTION="SecureQuest.findByQuestion";

	@Id
	@Column(name="SECURITY_QUESTION_ID")
	private int securityQuestionId;

	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="CREATED_DATE")
	private Timestamp createdDate;

	@Column(name="SECURITY_QUESTION")
	private String securityQuestion;

	//bi-directional many-to-one association to User
	@OneToMany(mappedBy="secureQuest")
	private List<User> users;

	public SecureQuest() {
	}

	public int getSecurityQuestionId() {
		return this.securityQuestionId;
	}

	public void setSecurityQuestionId(int securityQuestionId) {
		this.securityQuestionId = securityQuestionId;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getSecurityQuestion() {
		return this.securityQuestion;
	}

	public void setSecurityQuestion(String securityQuestion) {
		this.securityQuestion = securityQuestion;
	}

	public List<User> getUsers() {
		return this.users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User addUser(User user) {
		getUsers().add(user);
		user.setSecureQuest(this);

		return user;
	}

	public User removeUser(User user) {
		getUsers().remove(user);
		user.setSecureQuest(null);

		return user;
	}

}