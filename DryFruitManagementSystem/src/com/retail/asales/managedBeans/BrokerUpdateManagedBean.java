package com.retail.asales.managedBeans;

import java.util.Collections;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import org.hibernate.SessionException;

import com.retail.asales.entity.BrokerDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.BrokerBean;
import com.retail.asales.service.BrokerService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.StringUtils;

@ManagedBean
@RequestScoped
public class BrokerUpdateManagedBean {
	private List<String> brokerList;
	private BrokerBean brokerBean;
	private String selectedBrokerName;
	private BrokerDetail selectedEntity;

	public BrokerUpdateManagedBean() {
		try {
			brokerBean = new BrokerBean();
			BrokerService brokerService = new BrokerService();
			brokerList = brokerService.getBrokerNamesList();
			Collections.sort(brokerList);
		} catch (DryFruitBusinessException exception) {
			addMessage(exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}
	}

	public void valueChangeMethod(ValueChangeEvent e) {
		BrokerService brokerService = new BrokerService();
		try {
			selectedEntity = brokerService.findByBrokerName(e.getNewValue().toString());
			getBrokerBean().setBrokerName(selectedEntity.getBrokerName());
			getBrokerBean().setRemarks(selectedEntity.getRemarks());
			getBrokerBean().setBrokerStatus(selectedEntity.getBrokerStatus());
			getBrokerBean().setTotalAmountRemaining(selectedEntity.getTotalAmountRemaining());
		} catch (Exception e1) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}

	}

	public String updateBrokerActionListener() {
		try {
			if (StringUtils.isBlankOrNull(getBrokerBean().getBrokerName())) {
				addMessage("Broker Name is mandatory", FacesMessage.SEVERITY_ERROR);
				return "";
			}

			if (SessionUtility.getUser() == null) {
				addMessage("Your session timed out. Please logout and login again", FacesMessage.SEVERITY_ERROR);
				return "/login.xhtml?faces-redirect=true";
			}

			BrokerService brokerService = new BrokerService();
			if (!selectedEntity.getBrokerName().equalsIgnoreCase(brokerBean.getBrokerName().trim())) {
				if (brokerService.findByBrokerName(brokerBean.getBrokerName()) != null) {
					addMessage("Broker Name is already used. Please provide different name",
							FacesMessage.SEVERITY_ERROR);
					return "";
				}
			}
			brokerService.updateBroker(brokerBean, selectedEntity);
			addMessage("Broker Updated Successfully.", FacesMessage.SEVERITY_INFO);
			brokerBean = new BrokerBean();
			getBrokerBean().setBrokerStatus("ACTIVE");
			return "/updateBroker.xhtml?faces-redirect=true";
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
		return "";
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public BrokerDetail getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(BrokerDetail selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public BrokerBean getBrokerBean() {
		return brokerBean;
	}

	public void setBrokerBean(BrokerBean brokerBean) {
		this.brokerBean = brokerBean;
	}

	public List<String> getBrokerList() {
		return brokerList;
	}

	public void setBrokerList(List<String> brokerList) {
		this.brokerList = brokerList;
	}

	public String getSelectedBrokerName() {
		return selectedBrokerName;
	}

	public void setSelectedBrokerName(String selectedBrokerName) {
		this.selectedBrokerName = selectedBrokerName;
	}

}
