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

import com.retail.asales.entity.PartyDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.PartyBean;
import com.retail.asales.service.PartyService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.StringUtils;

@ManagedBean
@RequestScoped
public class PartyUpdateManagedBean {
	private List<String> partyList;
	private String selectedPartyName;
	private PartyBean partyBean;
	private PartyDetail selectedEntity;

	public PartyUpdateManagedBean() {
		
		try {
			partyBean = new PartyBean();
			PartyService partyService = new PartyService();
			partyList = partyService.getPartyNamesList();
			Collections.sort(partyList);
		} catch (DryFruitBusinessException exception) {
			addMessage(exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}
	}

	public void valueChangeMethod(ValueChangeEvent e) {
		PartyService partyService = new PartyService();
		try {
			selectedEntity = partyService.findByPartyName(e.getNewValue().toString());
			getPartyBean().setPartyName(selectedEntity.getPartyName());
			getPartyBean().setRemarks(selectedEntity.getRemarks());
			getPartyBean().setPartyStatus(selectedEntity.getPartyStatus());
			getPartyBean().setAddress(selectedEntity.getAddress());
			getPartyBean().setLandmark(selectedEntity.getLandmark());
			getPartyBean().setCity(selectedEntity.getCity());
			getPartyBean().setCategory(selectedEntity.getPartyCategory());
			getPartyBean().setRemarks(selectedEntity.getRemarks());
			getPartyBean().setTotalOutstandingAmount(selectedEntity.getTotalOutstandingAmount());
		} catch (Exception e1) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}

	}

	public String updatePartyActionListener() {
		try {
			if (StringUtils.isBlankOrNull(getPartyBean().getPartyName())) {
				addMessage("Party Name is mandatory", FacesMessage.SEVERITY_ERROR);
				return "";
			}

			if (SessionUtility.getUser() == null) {
				addMessage("Your session timed out. Please logout and login again", FacesMessage.SEVERITY_ERROR);
				return "/login.xhtml?faces-redirect=true";
			}

			PartyService partyService = new PartyService();
			if(!selectedEntity.getPartyName().equalsIgnoreCase(partyBean.getPartyName().trim())){
				if(partyService.findByPartyName(partyBean.getPartyName())!=null){
					addMessage("PartyName is already used. Please provide different name", FacesMessage.SEVERITY_ERROR);
					return "";
				}
			}
			partyService.updateParty(partyBean, selectedEntity);
			addMessage("Party Updated Successfully.", FacesMessage.SEVERITY_INFO);
			partyBean = new PartyBean();
			getPartyBean().setPartyStatus("ACTIVE");
			return "/updateParty.xhtml?faces-redirect=true";
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

	public PartyDetail getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(PartyDetail selectedEntity) {
		this.selectedEntity = selectedEntity;
	}


	public PartyBean getPartyBean() {
		return partyBean;
	}

	public void setPartyBean(PartyBean partyBean) {
		this.partyBean = partyBean;
	}

	public List<String> getPartyList() {
		return partyList;
	}

	public void setPartyList(List<String> itemList) {
		this.partyList = itemList;
	}

	public String getSelectedPartyName() {
		return selectedPartyName;
	}

	public void setSelectedPartyName(String selectedPartyName) {
		this.selectedPartyName = selectedPartyName;
	}

}
