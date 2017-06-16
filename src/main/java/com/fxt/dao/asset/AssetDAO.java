package com.fxt.dao.asset;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Asset;

public class AssetDAO extends BaseDAO<Asset> implements AssetIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Asset> getEntityClass()
    {
        return Asset.class;
    }
}
