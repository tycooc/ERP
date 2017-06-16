package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.FunctionsIDAO;
import com.fxt.dao.basic.UserBusinessIDAO;
import com.fxt.model.po.Functions;

public class FunctionsService extends BaseService<Functions> implements FunctionsIService
{
	@SuppressWarnings("unused")
	private FunctionsIDAO functionsDao;
	@SuppressWarnings("unused")
	private UserBusinessIDAO userBusinessDao;	


	public void setFunctionsDao(FunctionsIDAO functionsDao) 
	{
		this.functionsDao = functionsDao;
	}
	
	public void setUserBusinessDao(UserBusinessIDAO userBusinessDao) {
		this.userBusinessDao = userBusinessDao;
	}
	
	@Override
	protected Class<Functions> getEntityClass()
	{
		return Functions.class;
	}
    
}
