package com.retail.asales.DAOImpl;

import java.util.HashMap;
import java.util.Map;

import com.retail.asales.DAO.ItemJPADAO;
import com.retail.asales.entity.ItemDetail;

public class ItemJPADAOImpl extends GenericJPADAO<Integer, ItemDetail> implements ItemJPADAO{

	public ItemJPADAOImpl(){
		super(ItemDetail.class);
		//Auto-generated constructor stub
	}

	@Override
	public ItemDetail findByItemName(String itemName) {
		// Auto-generated method stub
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("itemName", itemName);

		return findSingleResultFromNamedQuery(ItemDetail.FIND_BY_ITEM_NAME, parameters);
	}

}
