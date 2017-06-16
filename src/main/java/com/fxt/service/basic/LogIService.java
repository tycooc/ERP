package com.fxt.service.basic;

import com.fxt.base.BaseIService;
import com.fxt.util.fxtException;
import com.fxt.model.po.Logdetails;

public interface LogIService extends BaseIService<Logdetails>
{
	/**
	 * 增加
	 * @param t 对象
	 * @throws fxtException
	 */
	@Override
	void save(Logdetails t);
}
