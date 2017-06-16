package com.fxt.service.materials;

import com.fxt.base.BaseService;
import com.fxt.dao.materials.PersonIDAO;
import com.fxt.model.po.Person;

public class PersonService extends BaseService<Person> implements PersonIService
{
	@SuppressWarnings("unused")
	private PersonIDAO personDao;

	
	public void setPersonDao(PersonIDAO personDao) {
		this.personDao = personDao;
	}


	@Override
	protected Class<Person> getEntityClass()
	{
		return Person.class;
	}
    
}
