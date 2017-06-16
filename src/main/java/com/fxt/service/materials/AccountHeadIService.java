package com.fxt.service.materials;

import com.fxt.base.BaseIService;
import com.fxt.util.fxtException;
import com.fxt.model.po.AccountHead;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public interface AccountHeadIService extends BaseIService<AccountHead>
{
    /*
     * 获取MaxId
     */
    void find(PageUtil<AccountHead> accountHead, String maxid)throws fxtException;
    
    void findAllMoney(PageUtil<AccountHead> accountHead, Integer supplierId, String type, String mode)throws fxtException;
}
