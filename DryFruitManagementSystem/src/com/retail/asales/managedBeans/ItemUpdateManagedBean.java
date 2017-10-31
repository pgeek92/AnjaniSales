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

import com.retail.asales.entity.ItemDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.service.ItemService;
import com.retail.asales.util.SessionUtility;
import com.retail.asales.util.StringUtils;

@ManagedBean
@RequestScoped
public class ItemUpdateManagedBean {
	private List<String> itemList;
	private String remarks;
	private String status;
	private String selectedItemName;
	private String itemName;
	private ItemDetail selectedEntity;

	public ItemUpdateManagedBean() {
		try {
			ItemService itemService = new ItemService();
			itemList = itemService.getItemNamesList();
			Collections.sort(itemList);
		} catch (DryFruitBusinessException exception) {
			addMessage(exception.getMessage(), FacesMessage.SEVERITY_ERROR);
		} catch (Exception e) {
			e.printStackTrace();
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}
	}

	public void valueChangeMethod(ValueChangeEvent e) {
		ItemService itemService = new ItemService();
		try {
			selectedEntity = itemService.findByItemName(e.getNewValue().toString());
			setItemName(selectedEntity.getItemName());
			setRemarks(selectedEntity.getRemarks());
			setStatus(selectedEntity.getItemStatus());
		} catch (Exception e1) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
		}

	}

	public String updateItemActionListener() {
		try {
			if (StringUtils.isBlankOrNull(getItemName())) {
				addMessage("Item Name is mandatory", FacesMessage.SEVERITY_ERROR);
				return "";
			}

			if (SessionUtility.getUser() == null) {
				addMessage("Your session timed out. Please logout and login again", FacesMessage.SEVERITY_ERROR);
				return "/login.xhtml?faces-redirect=true";
			}

			ItemService itemService = new ItemService();
			if(!selectedEntity.getItemName().equalsIgnoreCase(itemName.trim())){
				if(itemService.findByItemName(itemName)!=null){
					addMessage("ItemName is already used. Please provide different name", FacesMessage.SEVERITY_ERROR);
					return "";
				}
			}
			itemService.updateItem(itemName, remarks, status, selectedEntity);
			addMessage("Item Updated Successfully.", FacesMessage.SEVERITY_INFO);
			setItemName(null);
			setRemarks(null);
			setStatus("ACTIVE");
			return "/updateItem.xhtml?faces-redirect=true";
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

	public ItemDetail getSelectedEntity() {
		return selectedEntity;
	}

	public void setSelectedEntity(ItemDetail selectedEntity) {
		this.selectedEntity = selectedEntity;
	}

	public String getItemName() {
		return itemName;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public List<String> getItemList() {
		return itemList;
	}

	public void setItemList(List<String> itemList) {
		this.itemList = itemList;
	}

	public String getSelectedItemName() {
		return selectedItemName;
	}

	public void setSelectedItemName(String selectedItemName) {
		this.selectedItemName = selectedItemName;
	}

}
