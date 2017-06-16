package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.InOutItem;

public class InOutItemDAO extends BaseDAO<InOutItem> implements InOutItemIDAO
{
    /**
     * 设置dao映射基类
     * @return
     */
    @Override
    public Class<InOutItem> getEntityClass()
    {
        return InOutItem.class;
    }
}
