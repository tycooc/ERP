package com.fxt.service.materials;

import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.fxt.base.BaseService;
import com.fxt.base.Log;
import com.fxt.util.AssetConstants;
import com.fxt.dao.materials.AccountItemIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.model.po.AccountHead;
import com.fxt.model.po.AccountItem;
import com.fxt.util.PageUtil;
import com.fxt.util.Tools;

public class AccountItemService extends BaseService<AccountItem> implements AccountItemIService
{
    @SuppressWarnings("unused")
    private AccountItemIDAO accoumtItemDao;


    public void setAccountItemDao(AccountItemIDAO accoumtItemDao) {
        this.accoumtItemDao = accoumtItemDao;
    }


    @Override
    protected Class<AccountItem> getEntityClass()
    {
        return AccountItem.class;
    }


}
