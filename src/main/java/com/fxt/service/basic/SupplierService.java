package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.SupplierIDAO;
import com.fxt.model.po.Supplier;

public class SupplierService extends BaseService<Supplier> implements SupplierIService
{
	@SuppressWarnings("unused")
	private SupplierIDAO supplierDao;
	
	public void setSupplierDao(SupplierIDAO supplierDao)
	{
		this.supplierDao = supplierDao;
	}
	/**
     * 设置映射基类
     * @return
     */
	@Override
	protected Class<Supplier> getEntityClass()
    {
        return Supplier.class;
    }
}
