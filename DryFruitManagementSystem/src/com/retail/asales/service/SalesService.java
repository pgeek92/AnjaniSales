package com.retail.asales.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.text.WordUtils;

import com.retail.asales.DAO.BillDetailJPADAO;
import com.retail.asales.DAO.BrokerJPADAO;
import com.retail.asales.DAO.ItemJPADAO;
import com.retail.asales.DAOImpl.BillDetailJPADAOImpl;
import com.retail.asales.DAOImpl.BrokerJPADAOImpl;
import com.retail.asales.DAOImpl.ItemJPADAOImpl;
import com.retail.asales.entity.BillDetail;
import com.retail.asales.entity.ItemDetail;
import com.retail.asales.entity.SalesTransaction;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.BillBean;
import com.retail.asales.models.ItemBean;
import com.retail.asales.models.SalesBean;
import com.retail.asales.util.SessionUtility;

public class SalesService {

	public void save(BillBean billBean, List<SalesBean> listSalesBean) throws DryFruitBusinessException, Exception {
		BillDetail billDetail = new BillDetail();
		billDetail.setBillingDate(billBean.getSelectedDate());
		billDetail.setPartyDetail(billBean.getSelectedParty());
		billDetail.setBardaana(billBean.getBardaana());
		billDetail.setMazdoori(billBean.getMazdoori());
		billDetail.setDiscount(billBean.getDiscount());
		billDetail.setMuddat(billBean.getMuddat());
		billDetail.setTotalBillAmount(billBean.getTotalBillAmount());
		billDetail.setSalesTransactions(new ArrayList<SalesTransaction>());

		ItemJPADAO itemDao = new ItemJPADAOImpl();
		BrokerJPADAO brokerDao = new BrokerJPADAOImpl();
		BillDetailJPADAO billDetailDao = new BillDetailJPADAOImpl();
		billDetailDao.beginTransaction();

		Timestamp createdDate = new Timestamp(System.currentTimeMillis());
		for (SalesBean salesBean : listSalesBean) {
			SalesTransaction salesTransaction = new SalesTransaction();
			salesTransaction.setItemDetail(itemDao.findByItemName(salesBean.getSelectedItemName()));
			salesTransaction.setRatePerUnit(salesBean.getRatePerUnit());
			salesTransaction.setQuantity(salesBean.getQuantity());
			salesTransaction.setBasicAmount(salesBean.getBasicAmount());
			salesTransaction.setBrokerDetail(brokerDao.findByBrokerName(salesBean.getSelectedBroker()));
			salesTransaction.setBrokeragePercentage(salesBean.getBrokeragePercentage());
			salesTransaction.setBrokerageAmount(salesBean.getBrokerageAmount());
			salesTransaction.setSalesTransCreatedBy(SessionUtility.getUser());
			salesTransaction.setCreatedDate(createdDate);

			billDetail.addSalesTransaction(salesTransaction);
		}
		billDetail.setBillCreatedBy(SessionUtility.getUser());
		billDetail.setCreatedDate(createdDate);
		billDetail.setBillUpdatedBy(null);
		billDetail.setUpdatedDate(null);

		billDetailDao.persist(billDetail);
		billDetailDao.commitAndCloseTransaction();
	}

	public void addItem(ItemBean itemBean) throws DryFruitBusinessException, Exception {
		ItemJPADAO itemDao = new ItemJPADAOImpl();
		try {

			ItemDetail itemDetail = new ItemDetail();
			itemDetail.setItemCreatedBy(SessionUtility.getUser());
			itemDetail.setCreatedDate(new Timestamp(System.currentTimeMillis()));

			String itemName = WordUtils.capitalizeFully(itemBean.getItemName().trim());
			itemDao.beginTransaction();
			if (itemDao.findByItemName(itemName) != null) {
				throw new DryFruitBusinessException("Item is already available");
			}
			itemDetail.setItemName(itemName);
			itemDetail.setRemarks(itemBean.getRemarks());
			itemDetail.setItemStatus("ACTIVE");

			itemDao.persist(itemDetail);

		} catch (DryFruitBusinessException e) {
			throw e;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			itemDao.commitAndCloseTransaction();
		}

	}

}