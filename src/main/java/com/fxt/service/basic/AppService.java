package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.AppIDAO;
import com.fxt.dao.basic.UserBusinessIDAO;
import com.fxt.model.po.App;

public class AppService extends BaseService<App> implements AppIService
{
	@SuppressWarnings("unused")
	private AppIDAO appDao;
	@SuppressWarnings("unused")
	private UserBusinessIDAO userBusinessDao;


	public void setAppDao(AppIDAO appDao) 
	{
		this.appDao = appDao;
	}

	public void setUserBusinessDao(UserBusinessIDAO userBusinessDao) {
		this.userBusinessDao = userBusinessDao;
	}

	@Override
	protected Class<App> getEntityClass()
	{
		return App.class;
	}
    
}
