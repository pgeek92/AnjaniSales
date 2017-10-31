package com.retail.asales.DAOImpl;

import java.util.HashMap;
import java.util.Map;

import com.retail.asales.DAO.BillDetailJPADAO;
import com.retail.asales.entity.BillDetail;

public class BillDetailJPADAOImpl extends GenericJPADAO<Integer, BillDetail> implements BillDetailJPADAO {

	public BillDetailJPADAOImpl() {
		super(BillDetail.class);
		// Auto-generated constructor stub
	}

	@Override
	public BillDetail findMaxBillId() {
		// Auto-generated method stub
		Map<String, Object> parameters = new HashMap<String, Object>();
		return findSingleResultFromNamedQuery(BillDetail.FIND_MAX_BILL_ID, parameters);
	}

}
