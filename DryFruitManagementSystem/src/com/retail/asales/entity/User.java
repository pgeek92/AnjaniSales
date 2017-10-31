package com.retail.asales.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user database table.
 * 
 */
@Entity
@NamedQuery(name="User.findAll", query="SELECT u FROM User u")
public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="USER_ID")
	private String userId;

	private String password;

	private String remarks;

	@Column(name="SECURITY_ANSWER")
	private String securityAnswer;

	private String status;

	@Column(name="USER_NAME")
	private String userName;

	@Column(name="USER_ROLE")
	private String userRole;
	
	//bi-directional many-to-one association to BillDetail
	@OneToMany(mappedBy="billCreatedBy")
	private List<BillDetail> billDetails1;

	//bi-directional many-to-one association to BillDetail
	@OneToMany(mappedBy="billUpdatedBy")
	private List<BillDetail> billDetails2;

	//bi-directional many-to-one association to BrokerDetail
	@OneToMany(mappedBy="brokerCreatedBy")
	private List<BrokerDetail> brokerDetails1;

	//bi-directional many-to-one association to BrokerDetail
	@OneToMany(mappedBy="brokerUpdatedBy")
	private List<BrokerDetail> brokerDetails2;

	//bi-directional many-to-one association to ItemDetail
	@OneToMany(mappedBy="itemCreatedBy")
	private List<ItemDetail> itemDetails1;

	//bi-directional many-to-one association to ItemDetail
	@OneToMany(mappedBy="itemUpdatedBy")
	private List<ItemDetail> itemDetails2;

	//bi-directional many-to-one association to PartyDetail
	@OneToMany(mappedBy="partyCreatedBy")
	private List<PartyDetail> partyDetails1;

	//bi-directional many-to-one association to PartyDetail
	@OneToMany(mappedBy="partyUpdatedBy")
	private List<PartyDetail> partyDetails2;

	//bi-directional many-to-one association to SalesTransaction
	@OneToMany(mappedBy="salesTransCreatedBy")
	private List<SalesTransaction> salesTransactions1;

	//bi-directional many-to-one association to SalesTransaction
	@OneToMany(mappedBy="salesTransUpdatedBy")
	private List<SalesTransaction> salesTransactions2;

	//bi-directional many-to-one association to SecureQuest
	@ManyToOne
	@JoinColumn(name="SECURITY_QUESTION_ID")
	private SecureQuest secureQuest;

	public User() {
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getSecurityAnswer() {
		return this.securityAnswer;
	}

	public void setSecurityAnswer(String securityAnswer) {
		this.securityAnswer = securityAnswer;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserRole() {
		return this.userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public List<BrokerDetail> getBrokerDetails1() {
		return this.brokerDetails1;
	}

	public void setBrokerDetails1(List<BrokerDetail> brokerDetails1) {
		this.brokerDetails1 = brokerDetails1;
	}

	public BrokerDetail addBrokerDetails1(BrokerDetail brokerDetails1) {
		getBrokerDetails1().add(brokerDetails1);
		brokerDetails1.setBrokerCreatedBy(this);

		return brokerDetails1;
	}

	public BrokerDetail removeBrokerDetails1(BrokerDetail brokerDetails1) {
		getBrokerDetails1().remove(brokerDetails1);
		brokerDetails1.setBrokerCreatedBy(null);

		return brokerDetails1;
	}

	public List<BrokerDetail> getBrokerDetails2() {
		return this.brokerDetails2;
	}

	public void setBrokerDetails2(List<BrokerDetail> brokerDetails2) {
		this.brokerDetails2 = brokerDetails2;
	}

	public BrokerDetail addBrokerDetails2(BrokerDetail brokerDetails2) {
		getBrokerDetails2().add(brokerDetails2);
		brokerDetails2.setBrokerUpdatedBy(this);

		return brokerDetails2;
	}

	public BrokerDetail removeBrokerDetails2(BrokerDetail brokerDetails2) {
		getBrokerDetails2().remove(brokerDetails2);
		brokerDetails2.setBrokerUpdatedBy(null);

		return brokerDetails2;
	}

	public List<ItemDetail> getItemDetails1() {
		return this.itemDetails1;
	}

	public void setItemDetails1(List<ItemDetail> itemDetails1) {
		this.itemDetails1 = itemDetails1;
	}

	public ItemDetail addItemDetails1(ItemDetail itemDetails1) {
		getItemDetails1().add(itemDetails1);
		itemDetails1.setItemCreatedBy(this);

		return itemDetails1;
	}

	public ItemDetail removeItemDetails1(ItemDetail itemDetails1) {
		getItemDetails1().remove(itemDetails1);
		itemDetails1.setItemCreatedBy(null);

		return itemDetails1;
	}

	public List<ItemDetail> getItemDetails2() {
		return this.itemDetails2;
	}

	public void setItemDetails2(List<ItemDetail> itemDetails2) {
		this.itemDetails2 = itemDetails2;
	}

	public ItemDetail addItemDetails2(ItemDetail itemDetails2) {
		getItemDetails2().add(itemDetails2);
		itemDetails2.setItemUpdatedBy(this);

		return itemDetails2;
	}

	public ItemDetail removeItemDetails2(ItemDetail itemDetails2) {
		getItemDetails2().remove(itemDetails2);
		itemDetails2.setItemUpdatedBy(null);

		return itemDetails2;
	}

	public List<PartyDetail> getPartyDetails1() {
		return this.partyDetails1;
	}

	public void setPartyDetails1(List<PartyDetail> partyDetails1) {
		this.partyDetails1 = partyDetails1;
	}

	public PartyDetail addPartyDetails1(PartyDetail partyDetails1) {
		getPartyDetails1().add(partyDetails1);
		partyDetails1.setPartyCreatedBy(this);

		return partyDetails1;
	}

	public PartyDetail removePartyDetails1(PartyDetail partyDetails1) {
		getPartyDetails1().remove(partyDetails1);
		partyDetails1.setPartyCreatedBy(null);

		return partyDetails1;
	}

	public List<PartyDetail> getPartyDetails2() {
		return this.partyDetails2;
	}

	public void setPartyDetails2(List<PartyDetail> partyDetails2) {
		this.partyDetails2 = partyDetails2;
	}

	public PartyDetail addPartyDetails2(PartyDetail partyDetails2) {
		getPartyDetails2().add(partyDetails2);
		partyDetails2.setPartyUpdatedBy(this);

		return partyDetails2;
	}

	public PartyDetail removePartyDetails2(PartyDetail partyDetails2) {
		getPartyDetails2().remove(partyDetails2);
		partyDetails2.setPartyUpdatedBy(null);

		return partyDetails2;
	}

	public List<SalesTransaction> getSalesTransactions1() {
		return this.salesTransactions1;
	}

	public void setSalesTransactions1(List<SalesTransaction> salesTransactions1) {
		this.salesTransactions1 = salesTransactions1;
	}

	public SalesTransaction addSalesTransactions1(SalesTransaction salesTransactions1) {
		getSalesTransactions1().add(salesTransactions1);
		salesTransactions1.setSalesTransCreatedBy(this);

		return salesTransactions1;
	}

	public SalesTransaction removeSalesTransactions1(SalesTransaction salesTransactions1) {
		getSalesTransactions1().remove(salesTransactions1);
		salesTransactions1.setSalesTransCreatedBy(null);

		return salesTransactions1;
	}

	public List<SalesTransaction> getSalesTransactions2() {
		return this.salesTransactions2;
	}

	public void setSalesTransactions2(List<SalesTransaction> salesTransactions2) {
		this.salesTransactions2 = salesTransactions2;
	}

	public SalesTransaction addSalesTransactions2(SalesTransaction salesTransactions2) {
		getSalesTransactions2().add(salesTransactions2);
		salesTransactions2.setSalesTransUpdatedBy(this);

		return salesTransactions2;
	}

	public SalesTransaction removeSalesTransactions2(SalesTransaction salesTransactions2) {
		getSalesTransactions2().remove(salesTransactions2);
		salesTransactions2.setSalesTransUpdatedBy(null);

		return salesTransactions2;
	}

	public SecureQuest getSecureQuest() {
		return this.secureQuest;
	}

	public void setSecureQuest(SecureQuest secureQuest) {
		this.secureQuest = secureQuest;
	}

	public List<BillDetail> getBillDetails1() {
		return billDetails1;
	}

	public void setBillDetails1(List<BillDetail> billDetails1) {
		this.billDetails1 = billDetails1;
	}

	public List<BillDetail> getBillDetails2() {
		return billDetails2;
	}

	public void setBillDetails2(List<BillDetail> billDetails2) {
		this.billDetails2 = billDetails2;
	}

}