package com.fxt.service.materials;

import com.fxt.base.BaseService;
import com.fxt.dao.materials.AccountHeadIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.AccountHead;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public class AccountHeadService extends BaseService<AccountHead> implements AccountHeadIService
{
    @SuppressWarnings("unused")
    private AccountHeadIDAO accountHeadDao;


    public void setAccountHeadDao(AccountHeadIDAO accountHeadDao) {
        this.accountHeadDao = accountHeadDao;
    }


    @Override
    protected Class<AccountHead> getEntityClass()
    {
        return AccountHead.class;
    }

    public void find(PageUtil<AccountHead> pageUtil, String maxid) throws fxtException
    {
        accountHeadDao.find(pageUtil, maxid);
    }
    
    public void findAllMoney(PageUtil<AccountHead> pageUtil, Integer supplierId, String type, String mode) throws fxtException
    {
    	accountHeadDao.findAllMoney(pageUtil, supplierId, type, mode);
    }
}
