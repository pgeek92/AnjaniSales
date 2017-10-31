package com.retail.asales.managedBeans;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.retail.asales.entity.BrokerDetail;
import com.retail.asales.entity.PartyDetail;
import com.retail.asales.service.BrokerService;
import com.retail.asales.service.PartyService;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class ViewPartiesManagedBean implements Serializable {

	private List<PartyDetail> partyDetailsList;

	public ViewPartiesManagedBean() {
		PartyService partyService = new PartyService();
		try {
			partyDetailsList = partyService.getAllPartyDetailsList();
			if (partyDetailsList.isEmpty()) {
				addMessage("No Parties available", FacesMessage.SEVERITY_INFO);
			}
		} catch (Exception exception) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
			exception.printStackTrace();
			System.out.println(exception.getMessage());
		}
	}

	public void postProcessXLS(Object document) {
		HSSFWorkbook wb = (HSSFWorkbook) document;
		HSSFSheet sheet = wb.getSheetAt(0);
		HSSFRow header = sheet.getRow(0);
		HSSFCellStyle cellStyle = wb.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.RED.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		for (int i = 0; i < header.getPhysicalNumberOfCells(); i++) {
			header.getCell(i).setCellStyle(cellStyle);
		}
	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public List<PartyDetail> getPartyDetailsList() {
		return partyDetailsList;
	}

	public void setPartyDetailsList(List<PartyDetail> partyDetailsList) {
		this.partyDetailsList = partyDetailsList;
	}

}
