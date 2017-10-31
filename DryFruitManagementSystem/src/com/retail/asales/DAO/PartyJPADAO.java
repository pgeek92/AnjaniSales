package com.retail.asales.DAO;

import java.util.List;
import com.retail.asales.entity.PartyDetail;

public interface PartyJPADAO {
	
		public void persist(PartyDetail t);
		
		public List<PartyDetail> findAll();
		
		public PartyDetail findByPartyName(String partyName);
		
		public void beginTransaction();

		public void commitTransaction();
		
		public void commitAndCloseTransaction();
		
		public void closeTransaction();
		
		public PartyDetail update(PartyDetail partyDetail);
	

}
