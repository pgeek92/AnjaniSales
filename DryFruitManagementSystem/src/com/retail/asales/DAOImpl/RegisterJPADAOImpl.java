package com.retail.asales.DAOImpl;

import com.retail.asales.DAO.RegisterJPADAO;
import com.retail.asales.entity.User;

public class RegisterJPADAOImpl extends GenericJPADAO<Integer, User> implements RegisterJPADAO{
	public RegisterJPADAOImpl(){
		super(User.class);
	}
}
