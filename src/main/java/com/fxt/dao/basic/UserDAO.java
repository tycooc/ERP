package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Basicuser;

public class UserDAO extends BaseDAO<Basicuser> implements UserIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
	public Class<Basicuser> getEntityClass()
	{
		return Basicuser.class;
	}
	
    
}
