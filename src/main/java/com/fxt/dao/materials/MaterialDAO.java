package com.fxt.dao.materials;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Material;

public class MaterialDAO extends BaseDAO<Material> implements MaterialIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Material> getEntityClass()
    {
        return Material.class;
    }
}
