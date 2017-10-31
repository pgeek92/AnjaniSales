package com.retail.asales.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * The persistent class for the bill_details database table.
 *
 */
@Entity
@Table(name = "bill_details")
@NamedQueries({ @NamedQuery(name = "BillDetail.findAll", query = "SELECT b FROM BillDetail b"),
		@NamedQuery(name = "BillDetail.findMaxBillId", query = "SELECT b FROM BillDetail b where b.billingId=(select max(b1.billingId) from BillDetail b1)") })
public class BillDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_MAX_BILL_ID = "BillDetail.findMaxBillId";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "billing_id")
	private int billingId;

	private double bardaana;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "billing_date")
	private Date billingDate;

	@Column(name = "CREATED_DATE")
	private Timestamp createdDate;

	private double mazdoori;

	private Double discount;

	private Double muddat;

	@Column(name = "total_bill_amount")
	private double totalBillAmount;

	@Column(name = "UPDATED_DATE")
	private Timestamp updatedDate;

	// bi-directional many-to-one association to PartyDetail
	@ManyToOne
	@JoinColumn(name = "party_id")
	private PartyDetail partyDetail;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "CREATED_BY")
	private User billCreatedBy;

	// bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name = "UPDATED_BY")
	private User billUpdatedBy;

	// bi-directional many-to-one association to SalesTransaction
	@OneToMany(mappedBy = "billDetail", cascade = CascadeType.ALL)
	private List<SalesTransaction> salesTransactions;

	public BillDetail() {
	}

	public int getBillingId() {
		return this.billingId;
	}

	public void setBillingId(int billingId) {
		this.billingId = billingId;
	}

	public double getBardaana() {
		return this.bardaana;
	}

	public void setBardaana(double bardaana) {
		this.bardaana = bardaana;
	}

	public Date getBillingDate() {
		return this.billingDate;
	}

	public void setBillingDate(Date billingDate) {
		this.billingDate = billingDate;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public double getMazdoori() {
		return this.mazdoori;
	}

	public void setMazdoori(double mazdoori) {
		this.mazdoori = mazdoori;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getMuddat() {
		return muddat;
	}

	public void setMuddat(Double muddat) {
		this.muddat = muddat;
	}

	public double getTotalBillAmount() {
		return this.totalBillAmount;
	}

	public void setTotalBillAmount(double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public PartyDetail getPartyDetail() {
		return this.partyDetail;
	}

	public void setPartyDetail(PartyDetail partyDetail) {
		this.partyDetail = partyDetail;
	}

	public User getBillCreatedBy() {
		return billCreatedBy;
	}

	public void setBillCreatedBy(User billCreatedBy) {
		this.billCreatedBy = billCreatedBy;
	}

	public User getBillUpdatedBy() {
		return billUpdatedBy;
	}

	public void setBillUpdatedBy(User billUpdatedBy) {
		this.billUpdatedBy = billUpdatedBy;
	}

	public List<SalesTransaction> getSalesTransactions() {
		return this.salesTransactions;
	}

	public void setSalesTransactions(List<SalesTransaction> salesTransactions) {
		this.salesTransactions = salesTransactions;
	}

	public SalesTransaction addSalesTransaction(SalesTransaction salesTransaction) {
		getSalesTransactions().add(salesTransaction);
		salesTransaction.setBillDetail(this);

		return salesTransaction;
	}

	public SalesTransaction removeSalesTransaction(SalesTransaction salesTransaction) {
		getSalesTransactions().remove(salesTransaction);
		salesTransaction.setBillDetail(null);

		return salesTransaction;
	}

}