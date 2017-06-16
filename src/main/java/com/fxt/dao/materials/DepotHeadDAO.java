package com.fxt.dao.materials;

import org.hibernate.Query;

import com.fxt.base.BaseDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;
import com.fxt.util.SearchConditionUtil;

public class DepotHeadDAO extends BaseDAO<DepotHead> implements DepotHeadIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<DepotHead> getEntityClass()
    {
        return DepotHead.class;
    }
	
    @SuppressWarnings("unchecked")
    public void find(PageUtil<DepotHead> pageUtil,String maxid) throws fxtException
    {
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select max(Id) as Id from DepotHead depotHead where 1=1 " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
    
    @SuppressWarnings("unchecked")
    public void findAllMoney(PageUtil<DepotHead> pageUtil, Integer supplierId, String type, String subType, String mode) throws fxtException
    {
        Query query;
        String modeName = "";
        if(mode.equals("实际")){
        	modeName = "ChangeAmount";
        }
        else if(mode.equals("合计")){
        	modeName = "TotalPrice";
        }
        query= this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select sum(" + modeName + ") as allMoney from DepotHead depotHead where Type='" + type + "' and SubType = '" + subType + "' and OrganId =" + supplierId + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
}
