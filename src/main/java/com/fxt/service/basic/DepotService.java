package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.DepotIDAO;
import com.fxt.dao.basic.UserBusinessIDAO;
import com.fxt.model.po.Depot;

public class DepotService extends BaseService<Depot> implements DepotIService
{
	@SuppressWarnings("unused")
	private DepotIDAO depotDao;
	@SuppressWarnings("unused")
	private UserBusinessIDAO userBusinessDao;


	public void setDepotDao(DepotIDAO depotDao) 
	{
		this.depotDao = depotDao;
	}
	
	public void setUserBusinessDao(UserBusinessIDAO userBusinessDao) {
		this.userBusinessDao = userBusinessDao;
	}


	@Override
	protected Class<Depot> getEntityClass()
	{
		return Depot.class;
	}
    
}
