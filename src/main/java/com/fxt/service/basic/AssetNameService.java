package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.AssetNameIDAO;
import com.fxt.model.po.Assetname;

public class AssetNameService extends BaseService<Assetname> implements AssetNameIService
{
	@SuppressWarnings("unused")
	private AssetNameIDAO assetNameDao;

	public void setAssetNameDao(AssetNameIDAO assetNameDao)
    {
        this.assetNameDao = assetNameDao;
    }

	@Override
	protected Class<Assetname> getEntityClass()
	{
		return Assetname.class;
	}
}
