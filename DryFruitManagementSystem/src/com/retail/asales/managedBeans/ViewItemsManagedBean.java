package com.retail.asales.managedBeans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.lowagie.text.BadElementException;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Image;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.retail.asales.entity.ItemDetail;
import com.retail.asales.service.ItemService;

@SuppressWarnings("serial")
@ManagedBean
@RequestScoped
public class ViewItemsManagedBean implements Serializable {

	private List<ItemDetail> itemDetailsList;

	public ViewItemsManagedBean() {
		ItemService itemService = new ItemService();
		try {
			itemDetailsList = itemService.getAllItemDetailsList();
			if (itemDetailsList.isEmpty()) {
				addMessage("No Items available", FacesMessage.SEVERITY_INFO);
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

	public void preProcessPDF(Object document) throws IOException, BadElementException, DocumentException {
		try {
			Document pdf = (Document) document;
			pdf.open();
			pdf.setPageSize(PageSize.A4);
			Phrase phraseBefore = new Paragraph("Anjani Sales");
			HeaderFooter header = new HeaderFooter(phraseBefore, true);
			ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext()
					.getContext();
			String logo = servletContext.getRealPath("") + "\\" + "images" + "\\" + "pdf.jpg";
			System.out.println(servletContext.getRealPath(""));
			pdf.add(Image.getInstance(logo));
			pdf.setHeader(header);
		} catch (Exception exception) {
			addMessage("Some error has occured. Contact Adminstrator", FacesMessage.SEVERITY_WARN);
			exception.printStackTrace();
			System.out.println(exception.getMessage());
		}

	}

	public void addMessage(String summary, Severity severity) {
		FacesMessage message = new FacesMessage(severity, summary, null);
		FacesContext context = FacesContext.getCurrentInstance();
		context.addMessage(null, message);
		context.getExternalContext().getFlash().setKeepMessages(true);
	}

	public List<ItemDetail> getItemDetailsList() {
		return itemDetailsList;
	}

	public void setItemDetailsList(List<ItemDetail> itemDetailsList) {
		this.itemDetailsList = itemDetailsList;
	}

}
