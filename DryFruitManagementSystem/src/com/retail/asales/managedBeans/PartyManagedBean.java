package com.retail.asales.managedBeans;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.SessionException;

import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.ItemBean;
import com.retail.asales.models.PartyBean;
import com.retail.asales.service.ItemService;
import com.retail.asales.service.PartyService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.StringUtils;

@ManagedBean
@RequestScoped
public class PartyManagedBean {
	private PartyBean partyBean;
	
	public PartyManagedBean(){
		partyBean = new PartyBean();
	}
	
	public String addPartyActionListener() {
		try {
			if (StringUtils.isBlankOrNull(partyBean.getPartyName())) {
				addMessage("Party Name is mandatory", FacesMessage.SEVERITY_ERROR);
				return "";
			}

			if (SessionUtility.getUser() == null) {
				addMessage("Your session timed out. Please logout and login again", FacesMessage.SEVERITY_ERROR);
				return "/login.xhtml?faces-redirect=true";
			}

			PartyService partyService = new PartyService();
			partyService.addParty(partyBean);
			addMessage("Party Added Successfully.", FacesMessage.SEVERITY_INFO);
			setPartyBean(new PartyBean());
			return "/addParty.xhtml?faces-redirect=true";
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

	public PartyBean getPartyBean() {
		return partyBean;
	}

	public void setPartyBean(PartyBean partyBean) {
		this.partyBean = partyBean;
	}

}
