package com.fxt.dao.materials;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.MaterialCategory;

public class MaterialCategoryDAO extends BaseDAO<MaterialCategory> implements MaterialCategoryIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<MaterialCategory> getEntityClass()
    {
        return MaterialCategory.class;
    }
}
