package com.retail.asales.DAOImpl;

import java.util.HashMap;
import java.util.Map;

import com.retail.asales.DAO.BrokerJPADAO;
import com.retail.asales.entity.BrokerDetail;

public class BrokerJPADAOImpl extends GenericJPADAO<Integer, BrokerDetail> implements BrokerJPADAO{

	public BrokerJPADAOImpl() {
		super(BrokerDetail.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public BrokerDetail findByBrokerName(String brokerName) {
		// TODO Auto-generated method stub
		Map<String, Object> parameters=new HashMap<String, Object>();
		parameters.put("brokerName", brokerName);

		return findSingleResultFromNamedQuery(BrokerDetail.FIND_BY_BROKER_NAME, parameters);
	}

}
