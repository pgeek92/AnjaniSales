package com.retail.asales.models;

public class BrokerBean {
	private Integer brokerId;
	private String brokerName;
	private String remarks;
	private Double totalAmountRemaining;
	private String brokerStatus;
	public Integer getBrokerId() {
		return brokerId;
	}
	public void setBrokerId(Integer brokerId) {
		this.brokerId = brokerId;
	}
	public String getBrokerName() {
		return brokerName;
	}
	public void setBrokerName(String brokerName) {
		this.brokerName = brokerName;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	public Double getTotalAmountRemaining() {
		return totalAmountRemaining;
	}
	public void setTotalAmountRemaining(Double totalAmountRemaining) {
		this.totalAmountRemaining = totalAmountRemaining;
	}
	public String getBrokerStatus() {
		return brokerStatus;
	}
	public void setBrokerStatus(String brokerStatus) {
		this.brokerStatus = brokerStatus;
	}
}
