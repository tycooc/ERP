package com.fxt.action.materials;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.dao.DataAccessException;

import com.fxt.base.BaseAction;
import com.fxt.base.Log;
import com.fxt.model.po.Account;
import com.fxt.model.po.AccountHead;
import com.fxt.model.po.Logdetails;
import com.fxt.model.po.Person;
import com.fxt.model.po.Supplier;
import com.fxt.model.vo.materials.AccountHeadModel;
import com.fxt.service.materials.AccountHeadIService;
import com.fxt.service.materials.DepotHeadIService;
import com.fxt.util.fxtException;
import com.fxt.util.PageUtil;
import com.fxt.util.Tools;
/*
 * 财务表头管理
 * @author
 */
@SuppressWarnings("serial")
public class AccountHeadAction extends BaseAction<AccountHeadModel>
{
    private AccountHeadIService accountHeadService;
    private AccountHeadModel model = new AccountHeadModel();

    /*
     * 获取MaxId
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public String getMaxId()  
    {
        Map<String,List> mapData = model.getShowModel().getMap();
        PageUtil pageUtil = new  PageUtil();
        pageUtil.setPageSize(0);
        pageUtil.setCurPage(0);
        try
        {
            accountHeadService.find(pageUtil,"maxId");
            mapData.put("accountHeadMax", pageUtil.getPageList());
        }
        catch (Exception e)
        {
            Log.errorFileSync(">>>>>>>>>>>>>查找最大的Id信息异常", e);
            model.getShowModel().setMsgTip("exceptoin");
        }
        return SUCCESS;
    }

    /**
     * 增加财务
     * @return
     */
    public void create()
    {
        Log.infoFileSync("==================开始调用增加财务信息方法create()===================");
        Boolean flag = false;
        try
        {
            AccountHead accountHead = new AccountHead();
            accountHead.setType(model.getType());
            if(model.getOrganId()!=null){accountHead.setOrganId(new Supplier(model.getOrganId()));} 
            if(model.getHandsPersonId()!=null){accountHead.setHandsPersonId(new Person(model.getHandsPersonId()));}     
            accountHead.setChangeAmount(model.getChangeAmount());
            accountHead.setTotalPrice(model.getTotalPrice());
            if(model.getAccountId()!=null){accountHead.setAccountId(new Account(model.getAccountId()));}
            accountHead.setBillNo(model.getBillNo());
            try
            {
                accountHead.setBillTime(new Timestamp(Tools.parse(model.getBillTime(), "yyyy-MM-dd").getTime()));
            }
            catch (ParseException e)
            {
                Log.errorFileSync(">>>>>>>>>>>>>>>解析购买日期格式异常", e);
            } 
            accountHead.setRemark(model.getRemark());
            accountHeadService.create(accountHead);

            //========标识位===========
            flag = true;
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        }
        catch (DataAccessException e)
        {
            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>增加财务信息异常", e);
            flag = false;
            tipMsg = "失败";
            tipType = 1;
        }
        finally
        {
            try 
            {
                toClient(flag.toString());
            } 
            catch (IOException e) 
            {
                Log.errorFileSync(">>>>>>>>>>>>增加财务信息回写客户端结果异常", e);
            }
        }

        logService.create(new Logdetails(getUser(), "增加财务", model.getClientIp(),
                   new Timestamp(System.currentTimeMillis())
              , tipType, "增加财务编号为  "+ model.getBillNo() + " " + tipMsg + "！", "增加财务" + tipMsg));
        Log.infoFileSync("==================结束调用增加财务方法create()===================");
    }

    /**
     * 删除财务
     * @return
     */
    public String delete()
    {
        Log.infoFileSync("====================开始调用删除财务信息方法delete()================");
        try 
        {
            accountHeadService.delete(model.getAccountHeadID());
            tipMsg = "成功";
            tipType = 0;
        } 
        catch (DataAccessException e) 
        {
            Log.errorFileSync(">>>>>>>>>>>删除ID为 " + model.getAccountHeadID() + "  的财务异常", e);
            tipMsg = "失败";
            tipType = 1;
        }
        model.getShowModel().setMsgTip(tipMsg);
        logService.create(new Logdetails(getUser(), "删除财务", model.getClientIp(),
             new Timestamp(System.currentTimeMillis())
          , tipType, "删除财务ID为  "+ model.getAccountHeadID() + " " + tipMsg + "！", "删除财务" + tipMsg));
        Log.infoFileSync("====================结束调用删除财务信息方法delete()================");
        return SUCCESS;
    }

    /**
     * 更新财务
     * @return
     */
    public void update()
    {
        Boolean flag = false;
        try
        {
            AccountHead accountHead = accountHeadService.get(model.getAccountHeadID());
            accountHead.setType(model.getType());
            if(model.getOrganId()!=null){accountHead.setOrganId(new Supplier(model.getOrganId()));} 
            if(model.getHandsPersonId()!=null){accountHead.setHandsPersonId(new Person(model.getHandsPersonId()));}     
            accountHead.setChangeAmount(model.getChangeAmount());
            accountHead.setTotalPrice(model.getTotalPrice());
            if(model.getAccountId()!=null){accountHead.setAccountId(new Account(model.getAccountId()));}
            accountHead.setBillNo(model.getBillNo());
            try
            {
                accountHead.setBillTime(new Timestamp(Tools.parse(model.getBillTime(), "yyyy-MM-dd").getTime()));
            }
            catch (ParseException e)
            {
                Log.errorFileSync(">>>>>>>>>>>>>>>解析购买日期格式异常", e);
            } 
            accountHead.setRemark(model.getRemark());
            accountHeadService.update(accountHead);

            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } 
        catch (DataAccessException e) 
        {
            Log.errorFileSync(">>>>>>>>>>>>>修改财务ID为 ： " + model.getAccountHeadID() + "信息失败", e);
            flag = false;
            tipMsg = "失败";
            tipType = 1;
        }
        finally
        {
            try 
            {
                toClient(flag.toString());
            } 
            catch (IOException e) 
            {
                Log.errorFileSync(">>>>>>>>>>>>修改财务回写客户端结果异常", e);
            }
        }
        logService.create(new Logdetails(getUser(), "更新财务", model.getClientIp(),
                   new Timestamp(System.currentTimeMillis())
             , tipType, "更新财务ID为  "+ model.getAccountHeadID() + " " + tipMsg + "！", "更新财务" + tipMsg));
    }

    /**
     * 批量删除指定ID财务
     * @return
     */
    public String batchDelete()
    {
        try
        {
            accountHeadService.batchDelete(model.getAccountHeadIDs());
            model.getShowModel().setMsgTip("成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } 
        catch (DataAccessException e) 
        {
            Log.errorFileSync(">>>>>>>>>>>批量删除财务ID为：" + model.getAccountHeadIDs() + "信息异常", e);
            tipMsg = "失败";
            tipType = 1;
        }

        logService.create(new Logdetails(getUser(), "批量删除财务", model.getClientIp(),
                   new Timestamp(System.currentTimeMillis())
        , tipType, "批量删除财务ID为  "+ model.getAccountHeadIDs() + " " + tipMsg + "！", "批量删除财务" + tipMsg));
        return SUCCESS;
    }

    /**
     * 查找财务信息
     * @return
     */
    public void findBy()
    {
        try 
        {
            PageUtil<AccountHead> pageUtil = new  PageUtil<AccountHead>();
            pageUtil.setPageSize(model.getPageSize());
            pageUtil.setCurPage(model.getPageNo());
            pageUtil.setAdvSearch(getCondition());
            accountHeadService.find(pageUtil);
            List<AccountHead> dataList = pageUtil.getPageList();

            JSONObject outer = new JSONObject();
            outer.put("total", pageUtil.getTotalCount());
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if(null != dataList)
            {
                for(AccountHead accountHead:dataList)
                {
                    JSONObject item = new JSONObject();
                    item.put("Id", accountHead.getId());                  
                    item.put("OrganId", accountHead.getOrganId()==null?"":accountHead.getOrganId().getId());
                    item.put("OrganName", accountHead.getOrganId()==null?"":accountHead.getOrganId().getSupplier());
                    item.put("HandsPersonId", accountHead.getHandsPersonId()==null?"":accountHead.getHandsPersonId().getId());
                    item.put("HandsPersonName", accountHead.getHandsPersonId()==null?"":accountHead.getHandsPersonId().getName());
                    item.put("AccountId", accountHead.getAccountId()==null?"":accountHead.getAccountId().getId());
                    item.put("AccountName", accountHead.getAccountId()==null?"":accountHead.getAccountId().getName());
                    item.put("BillNo", accountHead.getBillNo());
                    item.put("BillTime", Tools.getCurrentMonth(accountHead.getBillTime()));
                    item.put("ChangeAmount", accountHead.getChangeAmount()==null?"":Math.abs(accountHead.getChangeAmount()));
                    item.put("TotalPrice", accountHead.getTotalPrice()==null?"":Math.abs(accountHead.getTotalPrice()));
                    item.put("Remark", accountHead.getRemark());
                    item.put("op", 1);
                    dataArray.add(item);
                }
            }
            outer.put("rows", dataArray);
            //回写查询结果
            toClient(outer.toString());
        } 
        catch (DataAccessException e) 
        {
            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找财务信息异常", e);
        } 
        catch (IOException e) 
        {
            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询财务信息结果异常", e);
        }
    }
    
    /**
     * 查询单位的累计应收和累计应付
     * @return
     */
    public void findTotalPay() {
    	try
	    {
			JSONObject outer = new JSONObject();     	
			Double sum = 0.0;
			String getS = model.getSupplierId();
			//进销部分
			sum = sum - (allMoney(getS, "付款", "合计") + allMoney(getS, "付款", "实际"));
			sum = sum + (allMoney(getS, "收款", "合计") + allMoney(getS, "收款", "实际"));
			sum = sum - (allMoney(getS, "收入", "合计") - allMoney(getS, "收入", "实际"));
			sum = sum + (allMoney(getS, "支出", "合计") - allMoney(getS, "支出", "实际"));
			//收付款部分
			
	    	outer.put("getAllMoney", sum);
            toClient(outer.toString());
        }
	    catch (DataAccessException e) 
	    {
	        Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找异常", e);
        } 
	    catch (IOException e) 
	    {
            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询结果异常", e);
        }
	}
    
    /**
     * 统计总金额
     * @param type
     * @param mode 合计或者金额
     * @return
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public Double allMoney(String getS, String type, String mode) {
		Log.infoFileSync("getS:" + getS);
    	Double allMoney = 0.0;
    	String allReturn = "";
		PageUtil pageUtil = new  PageUtil();
        pageUtil.setPageSize(0);
        pageUtil.setCurPage(0);
        try {        	
        	Integer supplierId = Integer.valueOf(getS);
        	accountHeadService.findAllMoney(pageUtil, supplierId, type, mode);
			allReturn = pageUtil.getPageList().toString();
			allReturn = allReturn.substring(1,allReturn.length()-1);
			if(allReturn.equals("null")){
				allReturn = "0";
			}
		} catch (fxtException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        allMoney = Double.parseDouble(allReturn);
        //返回正数，如果负数也转为正数
        if(allMoney<0){
        	allMoney = -allMoney;
        }
		return allMoney;    	     
    }

    /**
     * 拼接搜索条件
     * @return
     */
    private Map<String,Object> getCondition()
    {
        /**
         * 拼接搜索条件
         */
        Map<String,Object> condition = new HashMap<String,Object>();
        {condition.put("BillNo_s_like", model.getBillNo());}
        condition.put("Type_s_eq",model.getType());
        condition.put("BillTime_s_gteq",model.getBeginTime());
        condition.put("BillTime_s_lteq",model.getEndTime());
        condition.put("Id_s_order","desc");
        return condition;
    }

    //=============以下spring注入以及Model驱动公共方法，与Action处理无关==================
    public AccountHeadModel getModel()
    {
        return model;
    }
    public void setAccountHeadService(AccountHeadIService accountHeadService)
    {
        this.accountHeadService = accountHeadService;
    }
}
