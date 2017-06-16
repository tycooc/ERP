package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Logdetails;

public class LogDAO extends BaseDAO<Logdetails> implements LogIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
	public Class<Logdetails> getEntityClass()
	{
		return Logdetails.class;
	}

}
