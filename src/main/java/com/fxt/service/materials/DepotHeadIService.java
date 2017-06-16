package com.fxt.service.materials;

import com.fxt.base.BaseIService;
import com.fxt.util.fxtException;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public interface DepotHeadIService extends BaseIService<DepotHead>
{
	/*
	 * 获取MaxId
	 */
	void find(PageUtil<DepotHead> depotHead, String maxid)throws fxtException;
	
	void findAllMoney(PageUtil<DepotHead> depotHead, Integer supplierId, String type, String subType, String mode)throws fxtException;
}
