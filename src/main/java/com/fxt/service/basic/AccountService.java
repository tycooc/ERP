package com.fxt.service.basic;
import com.fxt.base.BaseService;
import com.fxt.dao.basic.AccountIDAO;
import com.fxt.model.po.Account;

public class AccountService extends BaseService<Account> implements AccountIService
{
    @SuppressWarnings("unused")
    private AccountIDAO accountDao;

	public void setAccountDao(AccountIDAO accountDao)
    {
        this.accountDao = accountDao;
    }
    
    @Override
    protected Class<Account> getEntityClass()
    {
        return Account.class;
    }

}
