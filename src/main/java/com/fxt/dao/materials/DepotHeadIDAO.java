package com.fxt.dao.materials;

import com.fxt.base.BaseIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public interface DepotHeadIDAO extends BaseIDAO<DepotHead>
{
	/*
	 * 获取MaxId
	 */
    void find(PageUtil<DepotHead> pageUtil, String maxid) throws fxtException;
    
    void findAllMoney(PageUtil<DepotHead> pageUtil, Integer supplierId, String type, String subType, String mode) throws fxtException;
    
}
