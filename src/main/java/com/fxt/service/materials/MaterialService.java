package com.fxt.service.materials;

import com.fxt.base.BaseService;
import com.fxt.dao.materials.MaterialIDAO;
import com.fxt.model.po.Material;

public class MaterialService extends BaseService<Material> implements MaterialIService
{
	@SuppressWarnings("unused")
	private MaterialIDAO materialDao;

	
	public void setMaterialDao(MaterialIDAO materialDao) {
		this.materialDao = materialDao;
	}


	@Override
	protected Class<Material> getEntityClass()
	{
		return Material.class;
	}
    
}
