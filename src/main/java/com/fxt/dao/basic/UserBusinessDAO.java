package com.fxt.dao.basic;

import org.hibernate.Query;

import com.fxt.base.BaseDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.model.po.UserBusiness;
import com.fxt.util.PageUtil;
import com.fxt.util.SearchConditionUtil;

public class UserBusinessDAO extends BaseDAO<UserBusiness> implements UserBusinessIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<UserBusiness> getEntityClass()
    {
        return UserBusiness.class;
    }
	
    @SuppressWarnings("unchecked")
    @Override
	public void find(PageUtil<UserBusiness> pageUtil,String ceshi) throws fxtException
    {
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select count(id),sum(id) from UserBusiness userBusiness where 1=1 " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
}
