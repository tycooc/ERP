package com.fxt.dao.materials;

import org.hibernate.Query;

import com.fxt.base.BaseDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.AccountHead;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;
import com.fxt.util.SearchConditionUtil;

public class AccountHeadDAO extends BaseDAO<AccountHead> implements AccountHeadIDAO
{
    /**
     * 设置dao映射基类
     * @return
     */
    @Override
    public Class<AccountHead> getEntityClass()
    {
        return AccountHead.class;
    }
    
    @SuppressWarnings("unchecked")
    public void find(PageUtil<AccountHead> pageUtil,String maxid) throws fxtException
    {
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select max(Id) as Id from AccountHead accountHead where 1=1 " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
    
    @SuppressWarnings("unchecked")
    public void findAllMoney(PageUtil<AccountHead> pageUtil, Integer supplierId, String type, String mode) throws fxtException
    {
        Query query;
        String modeName = "";
        if(mode.equals("实际")){
        	modeName = "ChangeAmount";
        }
        else if(mode.equals("合计")){
        	modeName = "TotalPrice";
        }
        query= this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select sum(" + modeName + ") as allMoney from AccountHead accountHead where Type='" + type + "' and OrganId =" + supplierId + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
}
