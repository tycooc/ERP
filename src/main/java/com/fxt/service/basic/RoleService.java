package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.RoleIDAO;
import com.fxt.dao.basic.UserBusinessIDAO;
import com.fxt.model.po.Role;

public class RoleService extends BaseService<Role> implements RoleIService
{
	@SuppressWarnings("unused")
	private RoleIDAO roleDao;
	@SuppressWarnings("unused")
	private UserBusinessIDAO userBusinessDao;

	public void setRoleDao(RoleIDAO roleDao) 
	{
		this.roleDao = roleDao;
	}
	
	public void setUserBusinessDao(UserBusinessIDAO userBusinessDao) {
		this.userBusinessDao = userBusinessDao;
	}
	
	@Override
	protected Class<Role> getEntityClass()
	{
		return Role.class;
	}
    
}
