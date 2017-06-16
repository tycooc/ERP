package com.fxt.dao.materials;

import com.fxt.base.BaseIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.AccountHead;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public interface AccountHeadIDAO extends BaseIDAO<AccountHead>
{
    /*
     * 获取MaxId
     */
    void find(PageUtil<AccountHead> pageUtil, String maxid) throws fxtException;
    
    void findAllMoney(PageUtil<AccountHead> pageUtil, Integer supplierId, String type, String mode) throws fxtException;
}
