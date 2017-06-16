package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.CategoryIDAO;
import com.fxt.model.po.Category;

public class CategoryService extends BaseService<Category> implements CategoryIService
{
	@SuppressWarnings("unused")
	private CategoryIDAO categoryDao;

	public void setCategoryDao(CategoryIDAO categoryDao)
    {
        this.categoryDao = categoryDao;
    }

	@Override
	protected Class<Category> getEntityClass()
	{
		return Category.class;
	}
    
}
