package com.retail.asales.DAO;

import java.util.List;

import com.retail.asales.entity.BillDetail;
import com.retail.asales.entity.ItemDetail;
import com.retail.asales.entity.SalesTransaction;

public interface SaleDetailJPADAO {
	
		public void persist(SalesTransaction t);
		
		public void beginTransaction();

		public void commitTransaction();
		
		public void commitAndCloseTransaction();
		
		public void closeTransaction();
		
}
