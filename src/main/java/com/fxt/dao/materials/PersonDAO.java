package com.fxt.dao.materials;

import com.fxt.base.BaseDAO;
import com.fxt.model.po.Person;

public class PersonDAO extends BaseDAO<Person> implements PersonIDAO
{
	/**
     * 设置dao映射基类
     * @return
     */
	@Override
    public Class<Person> getEntityClass()
    {
        return Person.class;
    }
}
