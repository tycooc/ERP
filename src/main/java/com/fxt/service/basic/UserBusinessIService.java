package com.fxt.service.basic;

import com.fxt.base.BaseIService;
import com.fxt.util.fxtException;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;

public interface UserBusinessIService extends BaseIService<UserBusiness>
{
	/*
	 * 测试一下自定义hql语句
	 */
	void find(PageUtil<UserBusiness> userBusiness, String ceshi)throws fxtException;

}
