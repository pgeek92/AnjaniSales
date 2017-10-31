package com.retail.asales.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * The persistent class for the broker_details database table.
 *
 */
@Entity
@Table(name = "broker_details")
@NamedQueries({ @NamedQuery(name = "BrokerDetail.findAll", query = "SELECT b FROM BrokerDetail b"),
		@NamedQuery(name = "BrokerDetail.findByBrokerName", query = "SELECT b FROM BrokerDetail b WHERE b.brokerName =:brokerName") })
public class BrokerDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_BROKER_NAME = "BrokerDetail.findByBrokerName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "BROKER_ID")
	private Integer brokerId;

	@Column(name = "BROKER_NAME")
	private String brokerName;

	@Column(name = "BROKER_STATUS")
	private String brokerStatus;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	private String remarks;

	@Column(name = "TOTAL_AMOUNT_REMAINING")
	private Double totalAmountRemaining;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private User brokerCreatedBy;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private User brokerUpdatedBy;

	// bi-directional many-to-one association to SalesTransaction
	@OneToMany(mappedBy = "brokerDetail")
	private List<SalesTransaction> salesTransactions;

	public BrokerDetail() {
	}

	public Integer getBrokerId() {
		return this.brokerId;
	}

	public void setBrokerId(Integer brokerId) {
		this.brokerId = brokerId;
	}

	public String getBrokerName() {
		return this.brokerName;
	}

	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}

	public String getBrokerStatus() {
		return this.brokerStatus;
	}

	public void setBrokerStatus(String brokerStatus) {
		this.brokerStatus = brokerStatus;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getTotalAmountRemaining() {
		return this.totalAmountRemaining;
	}

	public void setTotalAmountRemaining(Double totalAmountRemaining) {
		this.totalAmountRemaining = totalAmountRemaining;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getBrokerCreatedBy() {
		return brokerCreatedBy;
	}

	public void setBrokerCreatedBy(User brokerCreatedBy) {
		this.brokerCreatedBy = brokerCreatedBy;
	}

	public User getBrokerUpdatedBy() {
		return brokerUpdatedBy;
	}

	public void setBrokerUpdatedBy(User brokerUpdatedBy) {
		this.brokerUpdatedBy = brokerUpdatedBy;
	}

	public List<SalesTransaction> getSalesTransactions() {
		return this.salesTransactions;
	}

	public void setSalesTransactions(List<SalesTransaction> salesTransactions) {
		this.salesTransactions = salesTransactions;
	}

	public SalesTransaction addSalesTransaction(SalesTransaction salesTransaction) {
		getSalesTransactions().add(salesTransaction);
		salesTransaction.setBrokerDetail(this);

		return salesTransaction;
	}

	public SalesTransaction removeSalesTransaction(SalesTransaction salesTransaction) {
		getSalesTransactions().remove(salesTransaction);
		salesTransaction.setBrokerDetail(null);

		return salesTransaction;
	}

}