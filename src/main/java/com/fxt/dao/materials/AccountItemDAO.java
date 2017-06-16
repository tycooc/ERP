package com.fxt.dao.materials;

import org.hibernate.Query;

import com.fxt.base.BaseDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.AccountItem;
import com.fxt.util.PageUtil;
import com.fxt.util.SearchConditionUtil;

public class AccountItemDAO extends BaseDAO<AccountItem> implements AccountItemIDAO
{
    /**
     * 设置dao映射基类
     * @return
     */
    @Override
    public Class<AccountItem> getEntityClass()
    {
        return AccountItem.class;
    }
}
