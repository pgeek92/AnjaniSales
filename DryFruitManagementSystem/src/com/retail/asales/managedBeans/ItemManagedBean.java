package com.retail.asales.managedBeans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import org.hibernate.SessionException;

import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.ItemBean;
import com.retail.asales.service.ItemService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.StringUtils;

@ManagedBean
@RequestScoped
public class ItemManagedBean {
	private ItemBean itemBean;
	private List<String> dummyNames;
    private String selectedName;
    private List<String> inputTexts;

	public ItemManagedBean() {
		itemBean = new ItemBean();
		dummyNames = new ArrayList<String>();
        dummyNames.add("Dandidaar");
        inputTexts = new ArrayList<String>();
        inputTexts.add("");
	}
	
	 public void addInput(ActionEvent e) 
	 {
		 if (inputTexts.size() == 5) 
		 {
			 System.out.println("excedded limit");
	         return;
	     }
	     inputTexts.add("");
	 }

	public String addItemActionListener() {
		try {
			if (StringUtils.isBlankOrNull(itemBean.getItemName())) {
				addMessage("Item Name is mandatory", FacesMessage.SEVERITY_ERROR);
				return "";
			}

			if (SessionUtility.getUser() == null) {
				addMessage("Your session timed out. Please logout and login again", FacesMessage.SEVERITY_ERROR);
				return "/login.xhtml?faces-redirect=true";
			}

			ItemService itemService = new ItemService();
			itemService.addItem(getItemBean());
			addMessage("Item Added Successfully.", FacesMessage.SEVERITY_INFO);
			setItemBean(new ItemBean());
			return "/addItem.xhtml?faces-redirect=true";
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

	public ItemBean getItemBean() {
		return itemBean;
	}

	public void setItemBean(ItemBean itemBean) {
		this.itemBean = itemBean;
	}
	
	public List<String> getDummyNames() {
		return dummyNames;
	}

	public void setDummyNames(List<String> dummyNames) {
		this.dummyNames = dummyNames;
	}

	public String getSelectedName() {
		return selectedName;
	}

	public void setSelectedName(String selectedName) {
		this.selectedName = selectedName;
	}

	public List<String> getInputTexts() {
		return inputTexts;
	}

	public void setInputTexts(List<String> inputTexts) {
		this.inputTexts = inputTexts;
	}
}
