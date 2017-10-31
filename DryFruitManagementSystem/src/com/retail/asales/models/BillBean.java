package com.retail.asales.models;

import java.util.Date;

import com.retail.asales.entity.PartyDetail;

public class BillBean {

	private long billId;

	private Double bardaana;

	private Double mazdoori;

	private Double discount;

	private Double muddat;

	private Date selectedDate;

	private Date maxDate;

	private Double outstandingAmount;

	private String selectedPartyName;

	private PartyDetail selectedParty;

	private Double totalSaleAmount;

	private Double totalBillAmount;

	public Double getBardaana() {
		return bardaana;
	}

	public void setBardaana(Double bardaana) {
		this.bardaana = bardaana;
	}

	public Double getMazdoori() {
		return mazdoori;
	}

	public void setMazdoori(Double mazdoori) {
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

	public Date getSelectedDate() {
		return selectedDate;
	}

	public void setSelectedDate(Date selectedDate) {
		this.selectedDate = selectedDate;
	}

	public Date getMaxDate() {
		return maxDate;
	}

	public void setMaxDate(Date maxDate) {
		this.maxDate = maxDate;
	}

	public String getSelectedPartyName() {
		return selectedPartyName;
	}

	public void setSelectedPartyName(String selectedPartyName) {
		this.selectedPartyName = selectedPartyName;
	}

	public Double getOutstandingAmount() {
		return outstandingAmount;
	}

	public void setOutstandingAmount(Double outstandingAmount) {
		this.outstandingAmount = outstandingAmount;
	}

	public long getBillId() {
		return billId;
	}

	public void setBillId(long billId) {
		this.billId = billId;
	}

	public PartyDetail getSelectedParty() {
		return selectedParty;
	}

	public void setSelectedParty(PartyDetail selectedParty) {
		this.selectedParty = selectedParty;
	}

	public Double getTotalBillAmount() {
		return totalBillAmount;
	}

	public void setTotalBillAmount(Double totalBillAmount) {
		this.totalBillAmount = totalBillAmount;
	}

	public Double getTotalSaleAmount() {
		return totalSaleAmount;
	}

	public void setTotalSaleAmount(Double totalSaleAmount) {
		this.totalSaleAmount = totalSaleAmount;
	}

}
