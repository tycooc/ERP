package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Account;

public class AccountDAO extends BaseDAO<Account> implements AccountIDAO
{
    /**
     * 设置dao映射基类
     * @return
     */
    @Override
    public Class<Account> getEntityClass()
    {
        return Account.class;
    }
}
