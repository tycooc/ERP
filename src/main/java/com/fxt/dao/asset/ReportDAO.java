package com.fxt.dao.asset;

import org.hibernate.Query;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.util.PageUtil;
import com.fxt.util.SearchConditionUtil;

public class ReportDAO extends HibernateDaoSupport implements ReportIDAO
{
    @SuppressWarnings("unchecked")
    @Override
    public void find(PageUtil<Asset> pageUtil,String reportType,String reportName) throws fxtException
    {
        Query query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createQuery("select count(" + reportType +") as dataSum, " + reportName + " from Asset asset where 1=1 " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
}
