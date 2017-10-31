package com.retail.asales.DAOImpl;

import java.util.HashMap;
import java.util.Map;

import com.retail.asales.DAO.ItemJPADAO;
import com.retail.asales.DAO.PartyJPADAO;
import com.retail.asales.entity.ItemDetail;
import com.retail.asales.entity.PartyDetail;

public class PartyJPADAOImpl extends GenericJPADAO<Integer, PartyDetail> implements PartyJPADAO{

	public PartyJPADAOImpl(){
		super(PartyDetail.class);
		//Auto-generated constructor stub
	}

	@Override
	public PartyDetail findByPartyName(String partyName) {
		// Auto-generated method stub
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("partyName", partyName);

		return findSingleResultFromNamedQuery(PartyDetail.FIND_BY_PARTY_NAME, parameters);
	}

}
