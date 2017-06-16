package com.fxt.dao.basic;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Role;

public class RoleDAO extends BaseDAO<Role> implements RoleIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Role> getEntityClass()
    {
        return Role.class;
    }
}
