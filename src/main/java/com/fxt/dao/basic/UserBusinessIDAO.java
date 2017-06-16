package com.fxt.dao.basic;

import com.fxt.base.BaseIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public interface UserBusinessIDAO extends BaseIDAO<UserBusiness>
{
	/*
	 * 测试hql语句
	 */
    void find(PageUtil<UserBusiness> pageUtil, String ceshi) throws fxtException;
}
