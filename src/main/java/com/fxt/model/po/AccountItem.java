package com.fxt.model.po;

@SuppressWarnings("serial")
public class AccountItem implements java.io.Serializable
{
    private Long Id;
    private AccountHead HeaderId;
    private Account AccountId;
    private InOutItem InOutItemId;
    private Double EachAmount;
    private String Remark;

    public AccountItem()
    {

    }

    public AccountItem(Long Id)
    {
        this.Id = Id ;
    }

    public AccountItem(AccountHead headerId, Account accountId,
                       InOutItem inOutItemId, Double eachAmount, String remark)
    {
        super();
        HeaderId = headerId;
        AccountId = accountId;
        InOutItemId = inOutItemId;
        EachAmount = eachAmount;
        Remark = remark;
    }

    public void setId(Long id)
    {
        Id = id;
    }

    public Long getId()
    {
        return Id;
    }

    public void setHeaderId(AccountHead headerId)
    {
        HeaderId = headerId;
    }

    public AccountHead getHeaderId()
    {
        return HeaderId;
    }

    public void setAccountId(Account accountId)
    {
        AccountId = accountId;
    }

    public Account getAccountId()
    {
        return AccountId;
    }

    public void setInOutItemId(InOutItem inOutItemId)
    {
        InOutItemId = inOutItemId;
    }

    public InOutItem getInOutItemId()
    {
        return InOutItemId;
    }

    public void setEachAmount(Double eachAmount)
    {
        EachAmount = eachAmount;
    }

    public Double getEachAmount()
    {
        return EachAmount;
    }

    public void setRemark(String remark)
    {
        Remark = remark;
    }

    public String getRemark()
    {
        return Remark;
    }

}
