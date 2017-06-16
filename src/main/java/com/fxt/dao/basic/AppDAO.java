package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.App;

public class AppDAO extends BaseDAO<App> implements AppIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<App> getEntityClass()
    {
        return App.class;
    }
}
