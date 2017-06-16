package com.fxt.service.asset;

import com.fxt.dao.asset.ReportIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.util.PageUtil;

public class ReportService implements ReportIService
{
    private ReportIDAO reportDao;

    public void setReportDao(ReportIDAO reportDao)
    {
        this.reportDao = reportDao;
    }

    @Override
    public void find(PageUtil<Asset> pageUtil, String reportType,String reportName) throws fxtException
    {
        reportDao.find(pageUtil, reportType,reportName);
    }
    
}
