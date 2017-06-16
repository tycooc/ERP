package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Category;

public class CategoryDAO extends BaseDAO<Category> implements CategoryIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Category> getEntityClass()
    {
        return Category.class;
    }
}
