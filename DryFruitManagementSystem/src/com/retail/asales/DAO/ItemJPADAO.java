package com.retail.asales.DAO;

import java.util.List;

import com.retail.asales.entity.ItemDetail;

public interface ItemJPADAO {
	
		public void persist(ItemDetail t);
		
		public List<ItemDetail> findAll();
		
		public ItemDetail findByItemName(String itemName);
		
		public void beginTransaction();

		public void commitTransaction();
		
		public void commitAndCloseTransaction();
		
		public void closeTransaction();
		
		public ItemDetail update(ItemDetail itemDetail);
	

}
