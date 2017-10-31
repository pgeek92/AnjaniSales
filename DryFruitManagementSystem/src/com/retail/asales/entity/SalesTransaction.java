package com.retail.asales.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the sales_transaction database table.
 *
 */
@Entity
@Table(name = "sales_transaction")
@NamedQuery(name = "SalesTransaction.findAll", query = "SELECT s FROM SalesTransaction s")
public class SalesTransaction implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SALES_TRANSACTION_ID")
	private int salesTransactionId;

	@Column(name = "BASIC_AMOUNT")
	private Double basicAmount;

	@Column(name = "BROKERAGE_AMOUNT")
	private Double brokerageAmount;

	@Column(name = "BROKERAGE_PERCENTAGE")
	private Double brokeragePercentage;

	@Column(name = "Created_Date")
	private Timestamp createdDate;

	private Double quantity;

	@Column(name = "RATE_PER_UNIT")
	private Double ratePerUnit;

	@Column(name = "Updated_Date")
	private Timestamp updatedDate;

	// bi-directional many-to-one association to BillDetail
	@ManyToOne
	@JoinColumn(name = "BILL_ID")
	private BillDetail billDetail;

	// bi-directional many-to-one association to BrokerDetail
	@ManyToOne
	@JoinColumn(name = "BROKER_ID")
	private BrokerDetail brokerDetail;

	// bi-directional many-to-one association to ItemDetail
	@ManyToOne
	@JoinColumn(name = "ITEM_ID")
	private ItemDetail itemDetail;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "Created_By")
	private User salesTransCreatedBy;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "Updated_By")
	private User salesTransUpdatedBy;

	public SalesTransaction() {
	}

	public int getSalesTransactionId() {
		return this.salesTransactionId;
	}

	public void setSalesTransactionId(int salesTransactionId) {
		this.salesTransactionId = salesTransactionId;
	}

	public Double getBasicAmount() {
		return this.basicAmount;
	}

	public void setBasicAmount(Double basicAmount) {
		this.basicAmount = basicAmount;
	}

	public Double getBrokerageAmount() {
		return this.brokerageAmount;
	}

	public void setBrokerageAmount(Double brokerageAmount) {
		this.brokerageAmount = brokerageAmount;
	}

	public Double getBrokeragePercentage() {
		return this.brokeragePercentage;
	}

	public void setBrokeragePercentage(Double brokeragePercentage) {
		this.brokeragePercentage = brokeragePercentage;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public Double getQuantity() {
		return this.quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getRatePerUnit() {
		return this.ratePerUnit;
	}

	public void setRatePerUnit(Double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public BrokerDetail getBrokerDetail() {
		return this.brokerDetail;
	}

	public void setBrokerDetail(BrokerDetail brokerDetail) {
		this.brokerDetail = brokerDetail;
	}

	public ItemDetail getItemDetail() {
		return this.itemDetail;
	}

	public void setItemDetail(ItemDetail itemDetail) {
		this.itemDetail = itemDetail;
	}

	public BillDetail getBillDetail() {
		return billDetail;
	}

	public void setBillDetail(BillDetail billDetail) {
		this.billDetail = billDetail;
	}

	public User getSalesTransCreatedBy() {
		return this.salesTransCreatedBy;
	}

	public void setSalesTransCreatedBy(User salesTransCreatedBy) {
		this.salesTransCreatedBy = salesTransCreatedBy;
	}

	public User getSalesTransUpdatedBy() {
		return this.salesTransUpdatedBy;
	}

	public void setSalesTransUpdatedBy(User salesTransUpdatedBy) {
		this.salesTransUpdatedBy = salesTransUpdatedBy;
	}
}