package com.fxt.model.po;

@SuppressWarnings("serial")
public class Depot implements java.io.Serializable
{
	private Long id;
	private String name;
	private String sort;
	private String remark;

	public Depot()
	{
		
	}
	
	public Depot(Long id)
	{
		this.id = id ;
	}

	public Depot(String name, String sort, String remark)
	{
		this.name = name;
		this.sort = sort;
		this.remark = remark;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}