package com.fxt.model.po;

@SuppressWarnings("serial")
public class UserBusiness implements java.io.Serializable
{
	private Long Id;
	private String Type;
	private String KeyId;
	private String Value;

	public UserBusiness()
	{
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getKeyId() {
		return KeyId;
	}

	public void setKeyId(String keyId) {
		KeyId = keyId;
	}

	public String getValue() {
		return Value;
	}

	public void setValue(String value) {
		Value = value;
	}
	

}