package com.fxt.model.vo.asset;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ReportModel implements Serializable
{
    private ReportShowModel showModel = new ReportShowModel();

    /**======开始接受页面参数=================**/
    /**
     * 资产名称ID
     */
    private Long assetNameID ;
    
    /**
     * 资产类型ID
     */
    private Long assetCategoryID ;
    
    /**
     * 位置属性
     */
    private String location = "";
    
    /**
     * 状态属性
     */
    private Short status ;
    
    /**
     * 用户ID
     */
    private Long usernameID ;
    
    /**
     * 资产单价
     */
    private float price = 0;
    
    /**
     * 购买日期
     */
    private String purchasedate = "";
    
    /**
     * 有效日期
     */
    private String periodofvalidity = "";
    
    /**
     * 保修日期
     */
    private String warrantydate = "";
    
    /**
     * 资产编号
     */
    private String assetnum = "";
    
    /**
     * 资产序列号
     */
    private String serialnum = "";
    
    /**
     * 标签
     */
    private String labels = "";
    
    /**
     * 资产ID
     */
    private Long supplierID; 
    
    /**
     * 描述信息
     */
    private String description = "";
    
    
    /**
     * 资产ID
     */
    private Long assetID;
    
    /**
     * 资产IDs 批量操作使用
     */
    private String assetIDs = "";
    
    /**
     * 每页显示的个数
     */
    private int pageSize = 10;
    
    /**
     * 当前页码
     */
    private int pageNo = 1;
    
    /**
     * 用户IP，用户记录操作日志
     */
    private String clientIp = "";
    
    /**
     * 报表类型
     */
    private Integer reportType;

    public ReportShowModel getShowModel()
    {
        return showModel;
    }

    public void setShowModel(ReportShowModel showModel)
    {
        this.showModel = showModel;
    }

    public Long getAssetNameID()
    {
        return assetNameID;
    }

    public void setAssetNameID(Long assetNameID)
    {
        this.assetNameID = assetNameID;
    }

    public Long getAssetCategoryID()
    {
        return assetCategoryID;
    }

    public void setAssetCategoryID(Long assetCategoryID)
    {
        this.assetCategoryID = assetCategoryID;
    }

    public String getLocation()
    {
        return location;
    }

    public void setLocation(String location)
    {
        this.location = location;
    }

    public Short getStatus()
    {
        return status;
    }

    public void setStatus(Short status)
    {
        this.status = status;
    }

    public Long getUsernameID()
    {
        return usernameID;
    }

    public void setUsernameID(Long usernameID)
    {
        this.usernameID = usernameID;
    }

    public float getPrice()
    {
        return price;
    }

    public void setPrice(float price)
    {
        this.price = price;
    }

    public String getPurchasedate()
    {
        return purchasedate;
    }

    public void setPurchasedate(String purchasedate)
    {
        this.purchasedate = purchasedate;
    }

    public String getPeriodofvalidity()
    {
        return periodofvalidity;
    }

    public void setPeriodofvalidity(String periodofvalidity)
    {
        this.periodofvalidity = periodofvalidity;
    }

    public String getWarrantydate()
    {
        return warrantydate;
    }

    public void setWarrantydate(String warrantydate)
    {
        this.warrantydate = warrantydate;
    }

    public String getAssetnum()
    {
        return assetnum;
    }

    public void setAssetnum(String assetnum)
    {
        this.assetnum = assetnum;
    }

    public String getSerialnum()
    {
        return serialnum;
    }

    public void setSerialnum(String serialnum)
    {
        this.serialnum = serialnum;
    }

    public String getLabels()
    {
        return labels;
    }

    public void setLabels(String labels)
    {
        this.labels = labels;
    }

    public Long getSupplierID()
    {
        return supplierID;
    }

    public void setSupplierID(Long supplierID)
    {
        this.supplierID = supplierID;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Long getAssetID()
    {
        return assetID;
    }

    public void setAssetID(Long assetID)
    {
        this.assetID = assetID;
    }

    public String getAssetIDs()
    {
        return assetIDs;
    }

    public void setAssetIDs(String assetIDs)
    {
        this.assetIDs = assetIDs;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize(int pageSize)
    {
        this.pageSize = pageSize;
    }

    public int getPageNo()
    {
        return pageNo;
    }

    public void setPageNo(int pageNo)
    {
        this.pageNo = pageNo;
    }

    public String getClientIp()
    {
        return clientIp;
    }

    public void setClientIp(String clientIp)
    {
        this.clientIp = clientIp;
    }

    public Integer getReportType()
    {
        return reportType;
    }

    public void setReportType(Integer reportType)
    {
        this.reportType = reportType;
    }
}
