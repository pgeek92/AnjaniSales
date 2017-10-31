package com.retail.asales.managedBeans;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.SessionException;

import com.retail.asales.entity.PartyDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.BillBean;
import com.retail.asales.models.SalesBean;
import com.retail.asales.service.BillService;
import com.retail.asales.service.BrokerService;
import com.retail.asales.service.ItemService;
import com.retail.asales.service.PartyService;
import com.retail.asales.service.SalesService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.StringUtils;

@ManagedBean
@ViewScoped
public class SalesManagedBean {

	private List<String> listItemNames;

	private List<String> listPartyNames;

	private List<String> listBrokerNames;

	private List<SalesBean> listSalesBean = new ArrayList<SalesBean>();

	private BillBean billBean;

	private Date selectedDate;

	private Date maxDate;

	public SalesManagedBean() {
		billBean = new BillBean();
		maxDate = new Date();
		ItemService itemService = new ItemService();
		PartyService partyService = new PartyService();
		BillService billService = new BillService();
		BrokerService brokerService = new BrokerService();
		try {
			billBean.setSelectedDate(new Date());
			billBean.setBillId(billService.findMaxBillId() + 1);
			billBean.setMazdoori(0.0);
			billBean.setBardaana(0.0);
			billBean.setDiscount(0.0);
			billBean.setMuddat(0.0);
			billBean.setTotalSaleAmount(0.0);
			billBean.setTotalBillAmount(0.0);
			listItemNames = itemService.getItemNamesList();
			listPartyNames = partyService.getPartyNamesList();
			listBrokerNames = brokerService.getBrokerNamesList();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String buttonAction() {
		if (listSalesBean.size() >= 1) {
			SalesBean salesBeans = listSalesBean.get(listSalesBean.size() - 1);
			boolean isValid = true;
			if (StringUtils.isBlankOrNull(salesBeans.getSelectedItemName())) {
				isValid = false;
			}
			if (salesBeans.getRatePerUnit() == null || salesBeans.getQuantity() == null) {
				isValid = false;
			}
			if (!isValid) {
				addMessage("Please complete existing row", FacesMessage.SEVERITY_ERROR);
			} else {
				SalesBean salesBean = new SalesBean();
				salesBean.setEditable(true);
				salesBean.setBrokerPercentageEnabled(false);
				listSalesBean.add(salesBean);
			}
		} else {
			SalesBean salesBean = new SalesBean();
			salesBean.setBrokerPercentageEnabled(false);
			salesBean.setEditable(true);
			listSalesBean.add(salesBean);
		}
		return "";
	}

	public String save() {
		System.out.println("Saving Data");
		try {
			if (SessionUtility.getUser() == null) {
				addMessage("Your session timed out. Please logout and login again", FacesMessage.SEVERITY_ERROR);
				return "/login.xhtml?faces-redirect=true";
			}

			if (getBillBean().getSelectedDate().after(new Date())) {
				throw new DryFruitBusinessException("Date can't be in future");
			}

			if (listSalesBean.size() == 0) {
				throw new DryFruitBusinessException("Please add atleast one sale item");
			}
			SalesService salesService = new SalesService();
			salesService.save(billBean, listSalesBean);
		} catch (DryFruitBusinessException e) {
			addMessage(e.getMessage(), FacesMessage.SEVERITY_ERROR);
		} catch (SessionException sessionException) {
			addMessage(sessionException.getMessage(), FacesMessage.SEVERITY_ERROR);
			System.out.println(sessionException.getMessage());
		} catch (Exception exception) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
			exception.printStackTrace();
			System.out.println(exception.getMessage());
		}

		return "/addSalesTransaction.xhtml?faces-redirect=true";
	}

	public void rateAndQuantityKeyEvent(SalesBean salesBean) {
		if (salesBean.getQuantity() == null) {
			salesBean.setBasicAmount(null);
		} else if (salesBean.getRatePerUnit() == null) {
			salesBean.setBasicAmount(null);
		} else {
			salesBean.setBasicAmount(salesBean.getRatePerUnit() * salesBean.getQuantity());
			if (salesBean.getBrokerPercentageEnabled()) {
				if (salesBean.getBrokeragePercentage() != null) {
					salesBean.setBrokerageAmount(salesBean.getBasicAmount() * salesBean.getBrokeragePercentage() / 100);
				}
			}
		}
	}

	public void brokeragePerEnabledEvent(SalesBean salesBean) {
		salesBean.setBrokeragePercentage(null);
		salesBean.setBrokerageAmount(null);
	}

	public void brokerageKeyEvent(SalesBean salesBean) {
		if (salesBean.getBrokeragePercentage() == null) {
			salesBean.setBrokerageAmount(null);
		} else if (salesBean.getBasicAmount() == null) {
			salesBean.setBrokerageAmount(null);
		} else {
			salesBean.setBrokerageAmount(salesBean.getBasicAmount() * salesBean.getBrokeragePercentage() / 100);
		}
	}

	public void totalBillKeyEvent() {
		double totalBillAmount = 0.00;
		if (billBean.getTotalSaleAmount() != null) {
			totalBillAmount += billBean.getTotalSaleAmount();
		}
		totalBillAmount = calculatetotalBillAmount(totalBillAmount);
		billBean.setTotalBillAmount(totalBillAmount);
	}

	public void partyKeyEvent() {
		String selectedPartyName = billBean.getSelectedPartyName();
		System.out.println(selectedPartyName);
		if (selectedPartyName == null || selectedPartyName.equals("")) {
			billBean.setOutstandingAmount(null);
		} else {
			PartyService partyService = new PartyService();
			PartyDetail partyDetail;
			try {
				partyDetail = partyService.findByPartyName(selectedPartyName);
				billBean.setSelectedParty(partyDetail);
				if (partyDetail != null) {
					billBean.setOutstandingAmount(partyDetail.getTotalOutstandingAmount());
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				billBean.setOutstandingAmount(null);
				e.printStackTrace();
			}

		}
	}

	public String deleteAction(SalesBean salesBean) {
		System.out.println("Item Deleted");
		listSalesBean.remove(salesBean);
		double total = calculateTotalSaleAmount();
		billBean.setTotalSaleAmount(total);
		double overallTotal = calculatetotalBillAmount(total);
		billBean.setTotalBillAmount(overallTotal);
		return null;
	}

	public String editAction(SalesBean salesBean) {
		System.out.println("yasad");

		if (salesBean.isEditable()) {
			double total = calculateTotalSaleAmount();
			billBean.setTotalSaleAmount(total);
			double overallTotal = calculatetotalBillAmount(total);
			billBean.setTotalBillAmount(overallTotal);
		}
		salesBean.setEditable(!salesBean.isEditable());
		return null;
	}

	private double calculatetotalBillAmount(double total) {
		double overallTotal = 0.00;
		overallTotal += total;
		if (billBean.getBardaana() != null) {
			overallTotal += billBean.getBardaana();
		}
		if (billBean.getMuddat() != null) {
			overallTotal += billBean.getMuddat();
		}
		if (billBean.getDiscount() != null) {
			overallTotal -= billBean.getDiscount();
		}
		if (billBean.getMazdoori() != null) {
			overallTotal += billBean.getMazdoori();
		}
		return overallTotal;
	}

	private double calculateTotalSaleAmount() {
		double total = 0.00;
		for (SalesBean selectedSalesBean : listSalesBean) {
			total += selectedSalesBean.getBasicAmount();
		}
		return total;
	}

	public List<String> completeItem(String query) {
		List<String> filteredItemDetails = new ArrayList<String>();

		for (int i = 0; i < listItemNames.size(); i++) {
			String itemName = listItemNames.get(i);
			if (itemName.toLowerCase().startsWith(query.toLowerCase())) {
				filteredItemDetails.add(itemName);
			}
		}

		return filteredItemDetails;
	}

	public List<String> completeBroker(String query) {
		List<String> filteredBrokerDetails = new ArrayList<String>();

		for (int i = 0; i < listBrokerNames.size(); i++) {
			String brokerName = listBrokerNames.get(i);
			if (brokerName.toLowerCase().startsWith(query.toLowerCase())) {
				filteredBrokerDetails.add(brokerName);
			}
		}

		return filteredBrokerDetails;
	}

	public List<String> completeParty(String query) {
		List<String> filteredPartyDetails = new ArrayList<String>();

		for (int i = 0; i < listPartyNames.size(); i++) {
			String partyName = listPartyNames.get(i);
			if (partyName.toLowerCase().startsWith(query.toLowerCase())) {
				filteredPartyDetails.add(partyName);
			}
		}

		return filteredPartyDetails;
	}

	public void abc(ActionEvent ace) throws IOException {
		System.out.println("yup");
		FacesContext.getCurrentInstance().getExternalContext().redirect("addSalesTransaction.xhtml");
		return;
	}

	public String def() {
		System.out.println("yupss");
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("addSalesTransaction.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/addSalesTransaction.xhtml?faces-redirect=true";
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public List<String> getListItemNames() {
		return listItemNames;
	}

	public void setListItemNames(List<String> listItemNames) {
		this.listItemNames = listItemNames;
	}

	public List<SalesBean> getListSalesBean() {
		return listSalesBean;
	}

	public void setListSalesBean(List<SalesBean> listSalesBean) {
		this.listSalesBean = listSalesBean;
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

	public List<String> getListPartyNames() {
		return listPartyNames;
	}

	public void setListPartyNames(List<String> listPartyNames) {
		this.listPartyNames = listPartyNames;
	}

	public BillBean getBillBean() {
		return billBean;
	}

	public void setBillBean(BillBean billBean) {
		this.billBean = billBean;
	}

	public List<String> getListBrokerNames() {
		return listBrokerNames;
	}

	public void setListBrokerNames(List<String> listBrokerNames) {
		this.listBrokerNames = listBrokerNames;
	}
}