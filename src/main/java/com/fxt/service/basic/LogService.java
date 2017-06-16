package com.fxt.service.basic;

import com.fxt.base.BaseService;
import com.fxt.base.Log;
import com.fxt.dao.basic.LogIDAO;
import com.fxt.model.po.Logdetails;

public class LogService extends BaseService<Logdetails> implements LogIService 
{
    @SuppressWarnings("unused")
	private LogIDAO logDao;

    public void setLogDao(LogIDAO logDao) 
    {
        this.logDao = logDao;
    }

	@Override
	protected Class<Logdetails> getEntityClass()
	{
		return Logdetails.class;
	}
	
	@Override
	public void save(Logdetails t)
	{
		try
		{
			super.save(t);
		}
		catch (Exception e)
		{
			Log.errorFileSync(">>>>>>>>>>>>>>>>创建操作日志异常", e);
		}
	}
}