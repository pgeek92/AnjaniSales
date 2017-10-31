package com.retail.asales.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.retail.asales.DAO.ItemJPADAO;
import com.retail.asales.DAOImpl.ItemJPADAOImpl;
import com.retail.asales.entity.ItemDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.ItemBean;
import com.retail.asales.util.SessionUtility;

public class ItemService {

	public void addItem(ItemBean itemBean) throws DryFruitBusinessException, Exception {
		ItemJPADAO itemDao = new ItemJPADAOImpl();
		try {

			ItemDetail itemDetail = new ItemDetail();
			String itemName = WordUtils.capitalizeFully(itemBean.getItemName().trim());
			itemDao.beginTransaction();
			if (itemDao.findByItemName(itemName) != null) {
				throw new DryFruitBusinessException("Item is already available");
			}
			itemDetail.setItemName(itemName);
			itemDetail.setRemarks(itemBean.getRemarks());
			itemDetail.setItemStatus("ACTIVE");

			itemDetail.setItemCreatedBy(SessionUtility.getUser());
			itemDetail.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			itemDetail.setUpdatedDate(null);
			itemDetail.setItemUpdatedBy(null);
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

	public void updateItem(String itemName, String remarks, String status, ItemDetail selectedEntity)
			throws DryFruitBusinessException, Exception {
		ItemJPADAO itemDao = new ItemJPADAOImpl();
		try {
			itemName = WordUtils.capitalizeFully(itemName.trim());
			itemDao.beginTransaction();
			selectedEntity.setItemName(itemName);
			selectedEntity.setRemarks(remarks);
			selectedEntity.setItemStatus(status);
			selectedEntity.setItemUpdatedBy(SessionUtility.getUser());
			selectedEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

			itemDao.update(selectedEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			itemDao.commitAndCloseTransaction();
		}

	}

	public List<String> getItemNamesList() throws DryFruitBusinessException, Exception {
		try {
			List<ItemDetail> itemList = getAllItemDetailsList();
			if (itemList == null || itemList.isEmpty()) {
				throw new DryFruitBusinessException("No Items available for update");
			}
			List<String> itemNameList = new ArrayList<String>(itemList.size());
			for (ItemDetail itemDetail : itemList) {
				itemNameList.add(itemDetail.getItemName());
			}
			return itemNameList;
		} catch (DryFruitBusinessException exception) {
			exception.printStackTrace();
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public List<ItemDetail> getAllItemDetailsList() throws Exception {
		ItemJPADAO itemDao = new ItemJPADAOImpl();
		try {
			itemDao.beginTransaction();
			List<ItemDetail> itemList = itemDao.findAll();
			return itemList;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			itemDao.commitAndCloseTransaction();
		}
	}

	public Map<String, ItemBean> getItemsList(List<ItemDetail> itemList) throws DryFruitBusinessException, Exception {

		Map<String, ItemBean> itemBeanMap = new HashMap<String, ItemBean>();
		for (ItemDetail itemDetail : itemList) {
			ItemBean itemBean = new ItemBean();
			itemBean.setId(itemDetail.getItemId());
			itemBean.setItemName(itemDetail.getItemName());
			itemBean.setRemarks(itemDetail.getRemarks());
			itemBean.setStatus(itemDetail.getItemStatus());
			itemBeanMap.put(itemDetail.getItemName(), itemBean);
		}
		return itemBeanMap;
	}

	public List<String> getItemsNamesList(Map<String, ItemBean> itemMap) throws DryFruitBusinessException, Exception {
		List<String> itemNameList = new ArrayList<String>();
		for (Map.Entry<String, ItemBean> entry : itemMap.entrySet()) {
			itemNameList.add(entry.getKey());
		}
		return itemNameList;
	}

	public ItemDetail findByItemName(String itemName) throws Exception {
		itemName = WordUtils.capitalizeFully(itemName.trim());
		ItemJPADAO itemDao = new ItemJPADAOImpl();
		try {
			itemDao.beginTransaction();
			return itemDao.findByItemName(itemName);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			itemDao.commitAndCloseTransaction();
		}

	}
}