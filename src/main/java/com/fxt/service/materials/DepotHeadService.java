package com.fxt.service.materials;

import com.fxt.base.BaseService;
import com.fxt.dao.materials.DepotHeadIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public class DepotHeadService extends BaseService<DepotHead> implements DepotHeadIService
{
	@SuppressWarnings("unused")
	private DepotHeadIDAO depotHeadDao;

	
	public void setDepotHeadDao(DepotHeadIDAO depotHeadDao) {
		this.depotHeadDao = depotHeadDao;
	}


	@Override
	protected Class<DepotHead> getEntityClass()
	{
		return DepotHead.class;
	}
	
    public void find(PageUtil<DepotHead> pageUtil, String maxid) throws fxtException
    {
    	depotHeadDao.find(pageUtil, maxid);
    }
    
    public void findAllMoney(PageUtil<DepotHead> pageUtil, Integer supplierId, String type, String subType, String mode) throws fxtException
    {
    	depotHeadDao.findAllMoney(pageUtil, supplierId, type, subType, mode);
    }
}
