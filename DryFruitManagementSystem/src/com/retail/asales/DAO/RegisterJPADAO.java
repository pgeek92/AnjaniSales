package com.retail.asales.DAO;

import com.retail.asales.entity.User;

public interface RegisterJPADAO {
	public void persist(User user);

	public void beginTransaction();

	public void commitTransaction();

	public void commitAndCloseTransaction();

	public void closeTransaction();
}
