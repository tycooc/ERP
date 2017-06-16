package com.fxt.dao.materials;

import org.hibernate.Query;

import com.fxt.base.BaseDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.DepotItem;
import com.fxt.util.PageUtil;
import com.fxt.util.SearchConditionUtil;

public class DepotItemDAO extends BaseDAO<DepotItem> implements DepotItemIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<DepotItem> getEntityClass()
    {
        return DepotItem.class;
    }
	
    @SuppressWarnings("unchecked")
    @Override
	public void findByType(PageUtil<DepotItem> pageUtil,String type,Long MId,String MonthTime,Boolean isPrev) throws fxtException
    {
    	//多表联查,多表连查，此处用到了createSQLQuery，可以随便写sql语句，很方便
    	Query query;
    	if(isPrev) {
    		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select sum(OperNumber) as OperNumber from fxt_depotitem,fxt_depothead  where fxt_depotitem.HeaderId = fxt_depothead.id and type='" + type +"' and MaterialId ="+ MId + " and fxt_depothead.OperTime <'"+ MonthTime +"-01 00:00:00' " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
    	}
    	else {
    		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select sum(OperNumber) as OperNumber from fxt_depotitem,fxt_depothead  where fxt_depotitem.HeaderId = fxt_depothead.id and type='" + type +"' and MaterialId ="+ MId + " and fxt_depothead.OperTime >='"+ MonthTime +"-01 00:00:00' and fxt_depothead.OperTime <='"+ MonthTime +"-31 00:00:00' " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
    	}        
        pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
    
    @SuppressWarnings("unchecked")
    @Override
	public void buyOrSale(PageUtil<DepotItem> pageUtil,String type, String subType,Long MId,String MonthTime, String sumType) throws fxtException
    {
    	//多表联查,多表连查，此处用到了createSQLQuery，可以随便写sql语句，很方便
    	Query query;
    	if(sumType.equals("Number")) {
    		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select sum(OperNumber) as OperNumber from fxt_depotitem,fxt_depothead  where fxt_depotitem.HeaderId = fxt_depothead.id and type='" + type +"' and subType='" + subType +"' and MaterialId ="+ MId + " and fxt_depothead.OperTime >='"+ MonthTime +"-01 00:00:00' and fxt_depothead.OperTime <='"+ MonthTime +"-31 00:00:00' " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
    	}
    	else {
    		query = this.getHibernateTemplate().getSessionFactory().getCurrentSession().createSQLQuery("select sum(AllPrice) as AllPrice from fxt_depotitem,fxt_depothead  where fxt_depotitem.HeaderId = fxt_depothead.id and type='" + type +"' and subType='" + subType +"' and MaterialId ="+ MId + " and fxt_depothead.OperTime >='"+ MonthTime +"-01 00:00:00' and fxt_depothead.OperTime <='"+ MonthTime +"-31 00:00:00' " + SearchConditionUtil.getCondition(pageUtil.getAdvSearch()));
        }
    	pageUtil.setTotalCount(query.list().size());
        pageUtil.setPageList(query.list());
    }
}



