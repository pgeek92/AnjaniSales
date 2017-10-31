package com.retail.asales.DAO;

import java.util.List;

import com.retail.asales.entity.BrokerDetail;

public interface BrokerJPADAO {
	public void persist(BrokerDetail brokerDetail);
	
	public List<BrokerDetail> findAll();
	
	public BrokerDetail findByBrokerName(String brokerName);
	
	public void beginTransaction();

	public void commitTransaction();
	
	public void commitAndCloseTransaction();
	
	public void closeTransaction();
	
	public BrokerDetail update(BrokerDetail brokerDetail);

}
