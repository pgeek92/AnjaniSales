package com.retail.asales.DAO;

import com.retail.asales.entity.BillDetail;

public interface BillDetailJPADAO {

	public void persist(BillDetail t);

	public BillDetail findMaxBillId();

	public void beginTransaction();

	public void commitTransaction();

	public void commitAndCloseTransaction();

	public void closeTransaction();

}
