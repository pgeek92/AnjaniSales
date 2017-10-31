package com.retail.asales.models;

public class ItemBean {

	private Integer itemId;
	private String itemName;
	private String remarks;
	private String itemStatus;
	
	public Integer getId() {
		return itemId;
	}
	public void setId(Integer id) {
		this.itemId = id;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public String getStatus() {
		return itemStatus;
	}
	public void setStatus(String status) {
		this.itemStatus = status;
	}
}
