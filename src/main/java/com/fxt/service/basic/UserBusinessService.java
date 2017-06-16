package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.UserBusinessIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public class UserBusinessService extends BaseService<UserBusiness> implements UserBusinessIService
{
	@SuppressWarnings("unused")
	private UserBusinessIDAO userBusinessDao;

	public void setUserBusinessDao(UserBusinessIDAO userBusinessDao) 
	{
		this.userBusinessDao = userBusinessDao;
	}

	@Override
	protected Class<UserBusiness> getEntityClass()
	{
		return UserBusiness.class;
	}

    @Override
    public void find(PageUtil<UserBusiness> pageUtil, String ceshi) throws fxtException
    {
    	userBusinessDao.find(pageUtil, ceshi);
    }

	    
}
