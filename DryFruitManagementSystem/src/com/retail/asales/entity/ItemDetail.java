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
 * The persistent class for the item_details database table.
 *
 */
@Entity
@Table(name="item_details")
@NamedQueries({@NamedQuery(name="ItemDetail.findAll", query="SELECT i FROM ItemDetail i"),
	@NamedQuery(name="ItemDetail.findByItemName", query="SELECT i FROM ItemDetail i WHERE i.itemName =:itemName") })
public class ItemDetail implements Serializable {
	private static final long serialVersionUID = 1L;

	public static final String FIND_BY_ITEM_NAME="ItemDetail.findByItemName";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="Item_Id")
	private int itemId;

	@Column(name="Created_Date")
	private Timestamp createdDate;

	@Column(name="Item_Name")
	private String itemName;

	@Column(name="ITEM_STATUS")
	private String itemStatus;

	private String remarks;

	@Column(name="Updated_Date")
	private Timestamp updatedDate;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Created_By")
	private User itemCreatedBy;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="Updated_By")
	private User itemUpdatedBy;

	//bi-directional many-to-one association to SalesTransaction
	@OneToMany(mappedBy="itemDetail")
	private List<SalesTransaction> salesTransactions;

	public ItemDetail() {
	}

	public int getItemId() {
		return this.itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public Timestamp getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(Timestamp createdDate) {
		this.createdDate = createdDate;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemStatus() {
		return itemStatus;
	}

	public void setItemStatus(String itemStatus) {
		this.itemStatus = itemStatus;
	}


	public String getRemarks() {
		return this.remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Timestamp getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(Timestamp updatedDate) {
		this.updatedDate = updatedDate;
	}

	public User getItemCreatedBy() {
		return this.itemCreatedBy;
	}

	public void setItemCreatedBy(User itemCreatedBy) {
		this.itemCreatedBy = itemCreatedBy;
	}

	public User getItemUpdatedBy() {
		return this.itemUpdatedBy;
	}

	public void setItemUpdatedBy(User itemUpdatedBy) {
		this.itemUpdatedBy = itemUpdatedBy;
	}

	public List<SalesTransaction> getSalesTransactions() {
		return this.salesTransactions;
	}

	public void setSalesTransactions(List<SalesTransaction> salesTransactions) {
		this.salesTransactions = salesTransactions;
	}

	public SalesTransaction addSalesTransaction(SalesTransaction salesTransaction) {
		getSalesTransactions().add(salesTransaction);
		salesTransaction.setItemDetail(this);

		return salesTransaction;
	}

	public SalesTransaction removeSalesTransaction(SalesTransaction salesTransaction) {
		getSalesTransactions().remove(salesTransaction);
		salesTransaction.setItemDetail(null);

		return salesTransaction;
	}

}