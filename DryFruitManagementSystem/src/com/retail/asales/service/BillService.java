package com.retail.asales.service;

import com.retail.asales.DAO.BillDetailJPADAO;
import com.retail.asales.DAOImpl.BillDetailJPADAOImpl;
import com.retail.asales.entity.BillDetail;
import com.retail.asales.exceptions.DryFruitBusinessException;

public class BillService {

	public int findMaxBillId() throws DryFruitBusinessException, Exception {
		BillDetailJPADAO billDao = new BillDetailJPADAOImpl();
		try {

			billDao.beginTransaction();
			BillDetail billDetail = billDao.findMaxBillId();
			if (billDetail == null) {
				return 0;
			} else {
				return billDetail.getBillingId();
			}

		} catch (Exception exception) {
			exception.printStackTrace();
			throw exception;
		} finally {
			billDao.commitAndCloseTransaction();
		}

	}

}