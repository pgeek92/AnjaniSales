package com.retail.asales.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.text.WordUtils;

import com.retail.asales.DAO.ItemJPADAO;
import com.retail.asales.DAO.PartyJPADAO;
import com.retail.asales.DAOImpl.ItemJPADAOImpl;
import com.retail.asales.DAOImpl.PartyJPADAOImpl;
import com.retail.asales.entity.ItemDetail;
import com.retail.asales.entity.PartyDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;
import com.retail.asales.models.ItemBean;
import com.retail.asales.models.PartyBean;
import com.retail.asales.util.SessionUtility;

public class PartyService {

	public void addParty(PartyBean partyBean) throws DryFruitBusinessException, Exception {
		PartyJPADAO partyDao = new PartyJPADAOImpl();
		try {

			PartyDetail partyDetail = new PartyDetail();
			partyDetail.setPartyCreatedBy(SessionUtility.getUser());
			partyDetail.setCreatedDate(new Timestamp(System.currentTimeMillis()));

			String partyName = WordUtils.capitalizeFully(partyBean.getPartyName().trim());
			partyDao.beginTransaction();
			if (partyDao.findByPartyName(partyName) != null) {
				throw new DryFruitBusinessException("Party is already available");
			}
			partyDetail.setPartyName(partyName);
			partyDetail.setRemarks(partyBean.getRemarks());
			partyDetail.setPartyStatus("ACTIVE");
			partyDetail.setAddress(partyBean.getAddress());
			partyDetail.setLandmark(partyBean.getLandmark());
			partyDetail.setCity(partyBean.getCity());
			partyDetail.setPartyCategory(partyBean.getCategory());
			partyDetail.setRemarks(partyBean.getRemarks());
			partyDetail.setTotalOutstandingAmount(partyBean.getTotalOutstandingAmount());

			partyDao.persist(partyDetail);

		} catch (DryFruitBusinessException e) {
			throw e;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			partyDao.commitAndCloseTransaction();
		}

	}
	
	public List<String> getPartyNamesList() throws DryFruitBusinessException, Exception {
		try {
			List<PartyDetail> partyList = getAllPartyDetailsList();
			List<String> partyNameList = new ArrayList<String>(partyList.size());
			for (PartyDetail partyDetail : partyList) {
				partyNameList.add(partyDetail.getPartyName());
			}
			return partyNameList;
		} catch (DryFruitBusinessException exception) {
			exception.printStackTrace();
			throw exception;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		}
	}

	public void updateParty(PartyBean partyBean, PartyDetail selectedEntity)
			throws DryFruitBusinessException, Exception {
		PartyJPADAO partyDao = new PartyJPADAOImpl();
		try {
			String partyName = WordUtils.capitalizeFully(partyBean.getPartyName().trim());
			partyDao.beginTransaction();
			selectedEntity.setPartyName(partyName);
			selectedEntity.setRemarks(partyBean.getRemarks());
			selectedEntity.setAddress(partyBean.getAddress());
			selectedEntity.setPartyCategory(partyBean.getCategory());
			selectedEntity.setLandmark(partyBean.getLandmark());
			selectedEntity.setPartyStatus(partyBean.getPartyStatus());
			selectedEntity.setTotalOutstandingAmount(partyBean.getTotalOutstandingAmount());
			selectedEntity.setCity(partyBean.getCity());
			selectedEntity.setPartyUpdatedBy(SessionUtility.getUser());
			selectedEntity.setUpdatedDate(new Timestamp(System.currentTimeMillis()));

			partyDao.update(selectedEntity);
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			partyDao.commitAndCloseTransaction();
		}

	}
	
	public List<PartyDetail> getAllPartyDetailsList() throws Exception {
		PartyJPADAO partyDao = new PartyJPADAOImpl();
		try {
			partyDao.beginTransaction();
			List<PartyDetail> partyList = partyDao.findAll();
			return partyList;
		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			partyDao.commitAndCloseTransaction();
		}
	}
	
	public PartyDetail findByPartyName(String partyName) throws Exception {
		partyName = WordUtils.capitalizeFully(partyName.trim());
		PartyJPADAO partyDao = new PartyJPADAOImpl();
		try {
			partyDao.beginTransaction();
			return partyDao.findByPartyName(partyName);

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			partyDao.commitAndCloseTransaction();
		}

	}

}