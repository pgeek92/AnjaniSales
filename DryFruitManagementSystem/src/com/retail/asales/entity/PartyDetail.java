package com.retail.asales.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


/**
 * The persistent class for the party_details database table.
 *
 */
@Entity
@Table(name="party_details")
@NamedQueries({
@NamedQuery(name="PartyDetail.findAll", query="SELECT p FROM PartyDetail p"),
@NamedQuery(name="PartyDetail.findByPartyName", query="SELECT p FROM PartyDetail p WHERE p.partyName =:partyName")
})
public class PartyDetail implements Serializable {
	private static final long serialVersionUID = 1L;
	
	public static final String FIND_BY_PARTY_NAME="PartyDetail.findByPartyName";

	@Id
	@Column(name="Party_Id")
	private int partyId;

	private String address;

	private String city;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	private String landmark;

	@Column(name="Party_Category")
	private String partyCategory;

	@Column(name="Party_Name")
	private String partyName;

	@Column(name="PARTY_STATUS")
	private String partyStatus;

	private String remarks;

	@Column(name="Total_Outstanding_Amount")
	private Double totalOutstandingAmount;

	@Column(name="Updated_Date")
	private Timestamp updatedDate;
	
	//bi-directional many-to-one association to BillDetail
	@OneToMany(mappedBy="partyDetail")
	private List<BillDetail> billDetails;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Created_By")
	private User partyCreatedBy;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Updated_By")
	private User partyUpdatedBy;

	public PartyDetail() {
	}

	public int getPartyId() {
		return this.partyId;
	}

	public void setPartyId(int partyId) {
		this.partyId = partyId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getLandmark() {
		return this.landmark;
	}

	public void setLandmark(String landmark) {
		this.landmark = landmark;
	}

	public String getPartyCategory() {
		return this.partyCategory;
	}

	public void setPartyCategory(String partyCategory) {
		this.partyCategory = partyCategory;
	}

	public String getPartyName() {
		return this.partyName;
	}

	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}

	public String getPartyStatus() {
		return partyStatus;
	}

	public void setPartyStatus(String partyStatus) {
		this.partyStatus = partyStatus;
	}

	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Double getTotalOutstandingAmount() {
		return this.totalOutstandingAmount;
	}

	public void setTotalOutstandingAmount(Double totalOutstandingAmount) {
		this.totalOutstandingAmount = totalOutstandingAmount;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getPartyCreatedBy() {
		return this.partyCreatedBy;
	}

	public void setPartyCreatedBy(User partyCreatedBy) {
		this.partyCreatedBy = partyCreatedBy;
	}

	public User getPartyUpdatedBy() {
		return this.partyUpdatedBy;
	}

	public void setPartyUpdatedBy(User partyUpdatedBy) {
		this.partyUpdatedBy = partyUpdatedBy;
	}

	public List<BillDetail> getBillDetails() {
		return this.billDetails;
	}

	public void setBillDetails(List<BillDetail> billDetails) {
		this.billDetails = billDetails;
	}

	public BillDetail addBillDetail(BillDetail billDetail) {
		getBillDetails().add(billDetail);
		billDetail.setPartyDetail(this);

		return billDetail;
	}

	public BillDetail removeBillDetail(BillDetail billDetail) {
		getBillDetails().remove(billDetail);
		billDetail.setPartyDetail(null);

		return billDetail;
	}


}