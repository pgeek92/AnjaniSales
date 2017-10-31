package com.retail.asales.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.retail.asales.DAO.BrokerJPADAO;
import com.retail.asales.DAOImpl.BrokerJPADAOImpl;
import com.retail.asales.entity.BrokerDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.BrokerBean;
import com.retail.asales.util.SessionUtility;

public class BrokerService {
	public void addBroker(BrokerBean brokerBean) throws DryFruitBusinessException, Exception {
		BrokerJPADAO brokerDao = new BrokerJPADAOImpl();
		try {

			BrokerDetail brokerDetail = new BrokerDetail();

			String brokerName = WordUtils.capitalizeFully(brokerBean.getBrokerName().trim());
			brokerDao.beginTransaction();
			if (brokerDao.findByBrokerName(brokerName) != null) {
				throw new DryFruitBusinessException("Broker is already available");
			}
			brokerDetail.setBrokerName(brokerName);
			brokerDetail.setTotalAmountRemaining(brokerBean.getTotalAmountRemaining());
			brokerDetail.setRemarks(brokerBean.getRemarks());
			brokerDetail.setBrokerStatus("ACTIVE");

			brokerDetail.setBrokerCreatedBy(SessionUtility.getUser());
			brokerDetail.setCreatedDate(new Timestamp(System.currentTimeMillis()));
			brokerDetail.setUpdatedDate(null);
			brokerDetail.setBrokerUpdatedBy(null);
			brokerDao.persist(brokerDetail);

		} catch (DryFruitBusinessException e) {
			throw e;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			brokerDao.commitAndCloseTransaction();
		}
	}

	public void updateBroker(BrokerBean brokerBean, BrokerDetail selectedEntity)
			throws DryFruitBusinessException, Exception {
		BrokerJPADAO brokerDao = new BrokerJPADAOImpl();
		try {
			String brokerName = WordUtils.capitalizeFully(brokerBean.getBrokerName().trim());
			brokerDao.beginTransaction();
			selectedEntity.setBrokerName(brokerName);
			selectedEntity.setRemarks(brokerBean.getRemarks());
			selectedEntity.setBrokerStatus(brokerBean.getBrokerStatus());
			selectedEntity.setTotalAmountRemaining(brokerBean.getTotalAmountRemaining());
			selectedEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));
			selectedEntity.setBrokerUpdatedBy(SessionUtility.getUser());

			brokerDao.update(selectedEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			brokerDao.commitAndCloseTransaction();
		}

	}

	public List<String> getBrokerNamesList() throws DryFruitBusinessException, Exception {
		try {
			List<BrokerDetail> brokerList = getAllBrokerDetailsList();
			if (brokerList == null || brokerList.isEmpty()) {
				throw new DryFruitBusinessException("No Brokers available for update");
			}
			List<String> brokerNameList = new ArrayList<String>(brokerList.size());
			for (BrokerDetail brokerDetail : brokerList) {
				brokerNameList.add(brokerDetail.getBrokerName());
			}
			return brokerNameList;
		} catch (DryFruitBusinessException exception) {
			exception.printStackTrace();
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public List<BrokerDetail> getAllBrokerDetailsList() throws Exception {
		BrokerJPADAO brokerDao = new BrokerJPADAOImpl();
		try {
			brokerDao.beginTransaction();
			List<BrokerDetail> brokerList = brokerDao.findAll();
			return brokerList;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			brokerDao.commitAndCloseTransaction();
		}
	}

	public Map<String, BrokerBean> getBrokersList(List<BrokerDetail> brokerList)
			throws DryFruitBusinessException, Exception {

		Map<String, BrokerBean> brokerBeanMap = new HashMap<String, BrokerBean>();
		for (BrokerDetail brokerDetail : brokerList) {
			BrokerBean brokerBean = new BrokerBean();
			brokerBean.setBrokerId(brokerDetail.getBrokerId());
			brokerBean.setBrokerName(brokerDetail.getBrokerName());
			brokerBean.setRemarks(brokerDetail.getRemarks());
			brokerBean.setBrokerStatus(brokerDetail.getBrokerStatus());
			brokerBeanMap.put(brokerDetail.getBrokerName(), brokerBean);
		}
		return brokerBeanMap;
	}

	public List<String> getBrokersNamesList(Map<String, BrokerBean> brokerMap)
			throws DryFruitBusinessException, Exception {
		List<String> brokerNameList = new ArrayList<String>();
		for (Map.Entry<String, BrokerBean> entry : brokerMap.entrySet()) {
			brokerNameList.add(entry.getKey());
		}
		return brokerNameList;
	}

	public BrokerDetail findByBrokerName(String brokerName) throws Exception {
		brokerName = WordUtils.capitalizeFully(brokerName.trim());
		BrokerJPADAO brokerDao = new BrokerJPADAOImpl();
		try {
			brokerDao.beginTransaction();
			return brokerDao.findByBrokerName(brokerName);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			brokerDao.commitAndCloseTransaction();
		}

	}
}
