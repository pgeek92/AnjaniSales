package com.retail.asales.models;

public class SalesBean {
	private String selectedItemName;
	private Double ratePerUnit;
	private Double quantity;
	private Double basicAmount;
	private String selectedBroker;
	private Boolean brokerPercentageEnabled;
	private Double brokeragePercentage;
	private Double brokerageAmount;
	private Double totalAmount;
	private boolean editable;

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public String getSelectedItemName() {
		return selectedItemName;
	}

	public void setSelectedItemName(String selectedItemName) {
		this.selectedItemName = selectedItemName;
	}

	public Double getRatePerUnit() {
		return ratePerUnit;
	}

	public void setRatePerUnit(Double ratePerUnit) {
		this.ratePerUnit = ratePerUnit;
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public Double getBasicAmount() {
		return basicAmount;
	}

	public void setBasicAmount(Double basicAmount) {
		this.basicAmount = basicAmount;
	}

	public String getSelectedBroker() {
		return selectedBroker;
	}

	public void setSelectedBroker(String selectedBroker) {
		this.selectedBroker = selectedBroker;
	}

	public Double getBrokeragePercentage() {
		return brokeragePercentage;
	}

	public void setBrokeragePercentage(Double brokeragePercentage) {
		this.brokeragePercentage = brokeragePercentage;
	}

	public Double getBrokerageAmount() {
		return brokerageAmount;
	}

	public void setBrokerageAmount(Double brokerageAmount) {
		this.brokerageAmount = brokerageAmount;
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Boolean getBrokerPercentageEnabled() {
		return brokerPercentageEnabled;
	}

	public void setBrokerPercentageEnabled(Boolean brokerPercentageEnabled) {
		this.brokerPercentageEnabled = brokerPercentageEnabled;
	}

}
