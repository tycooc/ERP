package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Assetname;

public class AssetNameDAO extends BaseDAO<Assetname> implements AssetNameIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Assetname> getEntityClass()
    {
        return Assetname.class;
    }
}
