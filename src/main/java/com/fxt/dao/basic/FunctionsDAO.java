package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Functions;

public class FunctionsDAO extends BaseDAO<Functions> implements FunctionsIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Functions> getEntityClass()
    {
        return Functions.class;
    }
}
