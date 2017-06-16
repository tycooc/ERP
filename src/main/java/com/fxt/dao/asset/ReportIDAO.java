package com.fxt.dao.asset;

import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.util.PageUtil;

public interface ReportIDAO
{
    /**
     * 查找资产列表
     * @param pageUtil 分页工具类
     * @param reportType 报表统计字段
     * @throws fxtException
     */
    void find(PageUtil<Asset> pageUtil, String reportType, String reportName) throws fxtException;
}
