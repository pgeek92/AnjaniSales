package com.retail.asales.DAOImpl;

import com.retail.asales.DAO.SaleDetailJPADAO;
import com.retail.asales.entity.SalesTransaction;

public class SaleDetailJPADAOImpl extends GenericJPADAO<Integer, SalesTransaction> implements SaleDetailJPADAO {

	public SaleDetailJPADAOImpl() {
		super(SalesTransaction.class);
		// Auto-generated constructor stub
	}

}
