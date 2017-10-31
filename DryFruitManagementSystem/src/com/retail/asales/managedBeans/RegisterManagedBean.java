package com.retail.asales.managedBeans;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.hibernate.SessionException;

import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.RegisterBean;
import com.retail.asales.service.RegisterService;
import com.retail.asales.service.SecureQuestService;
import com.retail.asales.util.StringUtils;

@ManagedBean
@RequestScoped
public class RegisterManagedBean {
	private RegisterBean registerBean;
	private List<String> secureQuestList;

	public List<String> getSecureQuestList() {
		return secureQuestList;
	}

	public void setSecureQuestList(List<String> secureQuestList) {
		this.secureQuestList = secureQuestList;
	}

	public RegisterBean getRegisterBean() {
		return registerBean;
	}

	public void setRegisterBean(RegisterBean registerBean) {
		this.registerBean = registerBean;
	}

	public RegisterManagedBean() {
		registerBean = new RegisterBean();
		try {
			SecureQuestService sqs = new SecureQuestService();
			secureQuestList = sqs.getAllSecureQuests();
		} catch (Exception e) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}

	}

	public String registerActionListener() {
		try {
			if (StringUtils.isBlankOrNull(registerBean.getUserId().trim())) {
				addMessage("User Id is mandatory", FacesMessage.SEVERITY_ERROR);
				return "";
			}
			if (!registerBean.getPassword().equals(registerBean.getConfirmPassword())) {
				throw new DryFruitBusinessException("Password and confirm password should be same");
			}
			RegisterService registerService = new RegisterService();
			registerService.addNewUser(registerBean);
			registerBean = new RegisterBean();
			addMessage("New user request has been submitted to Admin Successfully", FacesMessage.SEVERITY_INFO);
			return "/login.xhtml?faces-redirect=true";

		} catch (SessionException sessionException) {
			addMessage(sessionException.getMessage(), FacesMessage.SEVERITY_ERROR);
			System.out.println(sessionException.getMessage());
			return "";
		} catch (DryFruitBusinessException exception) {
			addMessage(exception.getMessage(), FacesMessage.SEVERITY_ERROR);
			return "";
		} catch (Exception exception) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
			exception.printStackTrace();
			System.out.println(exception.getMessage());
			return "";
		}
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}
}
