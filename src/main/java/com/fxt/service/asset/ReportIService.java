package com.fxt.service.asset;

import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.util.PageUtil;

public interface ReportIService
{
    /**
     * 查找报表数据
     * @param asset
     * @throws fxtException
     */
    void find(PageUtil<Asset> asset, String reportType, String reportName)throws fxtException;
}
