package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Depot;

public class DepotDAO extends BaseDAO<Depot> implements DepotIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Depot> getEntityClass()
    {
        return Depot.class;
    }
}
