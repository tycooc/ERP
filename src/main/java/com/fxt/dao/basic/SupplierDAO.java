package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Supplier;

public class SupplierDAO extends BaseDAO<Supplier> implements SupplierIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
	public Class<Supplier> getEntityClass()
	{
		return Supplier.class;
	}
	
    
}
