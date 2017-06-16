package com.fxt.dao.materials;

import com.fxt.base.BaseIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.DepotItem;
import com.fxt.util.PageUtil;

public interface DepotItemIDAO extends BaseIDAO<DepotItem>
{
    public void findByType(PageUtil<DepotItem> pageUtil, String type, Long MId, String MonthTime, Boolean isPrev) throws fxtException;
    
    public void buyOrSale(PageUtil<DepotItem> pageUtil, String type, String subType, Long MId, String MonthTime, String sumType) throws fxtException;
}
