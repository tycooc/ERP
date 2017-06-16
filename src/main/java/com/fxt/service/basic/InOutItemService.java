package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.dao.basic.InOutItemIDAO;
import com.fxt.model.po.InOutItem;

public class InOutItemService extends BaseService<InOutItem> implements InOutItemIService
{
    @SuppressWarnings("unused")
    private InOutItemIDAO inOutItemDao;

    public void setInOutItemDao(InOutItemIDAO inOutItemDao)
    {
        this.inOutItemDao = inOutItemDao;
    }

    @Override
    protected Class<InOutItem> getEntityClass()
    {
        return InOutItem.class;
    }

}
