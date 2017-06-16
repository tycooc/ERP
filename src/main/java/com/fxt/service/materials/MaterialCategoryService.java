package com.fxt.service.materials;

import com.fxt.base.BaseService;
import com.fxt.dao.materials.MaterialCategoryIDAO;
import com.fxt.model.po.MaterialCategory;

public class MaterialCategoryService extends BaseService<MaterialCategory> implements MaterialCategoryIService
{
	@SuppressWarnings("unused")
	private MaterialCategoryIDAO materialCategoryDao;

	
	public void setMaterialCategoryDao(MaterialCategoryIDAO materialCategoryDao) {
		this.materialCategoryDao = materialCategoryDao;
	}


	@Override
	protected Class<MaterialCategory> getEntityClass()
	{
		return MaterialCategory.class;
	}
    
}
