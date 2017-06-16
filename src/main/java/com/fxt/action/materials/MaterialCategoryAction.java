package com.fxt.action.materials;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.dao.DataAccessException;
import com.fxt.base.BaseAction;
import com.fxt.base.Log;
import com.fxt.model.po.MaterialCategory;
import com.fxt.model.po.Logdetails;
import com.fxt.model.vo.materials.MaterialCategoryModel;
import com.fxt.service.materials.MaterialCategoryIService;
import com.fxt.util.PageUtil;
/*
 * 商品类型管理
 * @author jishenghua  qq:752718920
*/
@SuppressWarnings("serial")
public class MaterialCategoryAction extends BaseAction<MaterialCategoryModel>
{
    private MaterialCategoryIService materialCategoryService;
    private MaterialCategoryModel model = new MaterialCategoryModel();
    
    
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public String getBasicData()
    {
        Map<String,List> mapData = model.getShowModel().getMap();
        PageUtil pageUtil = new  PageUtil();
        pageUtil.setPageSize(0);
        pageUtil.setCurPage(0);
        try
        {
            Map<String,Object> condition = pageUtil.getAdvSearch();
            condition.put("ParentId_n_eq", model.getParentId());
            condition.put("Id_n_neq", 1);
            condition.put("Id_s_order", "asc");
            materialCategoryService.find(pageUtil);
            mapData.put("materialCategoryList", pageUtil.getPageList());
        }
        catch (Exception e)
        {
            Log.errorFileSync(">>>>>>>>>>>>>查找商品类别信息异常", e);
            model.getShowModel().setMsgTip("exceptoin");
        }
        return SUCCESS;
    }
    
	/**
	 * 增加商品类别
	 * @return
	 */
	public void create()
	{
	    Log.infoFileSync("==================开始调用增加商品类别信息方法create()===================");
	    Boolean flag = false;
		try
		{
			MaterialCategory materialCategory = new MaterialCategory();
			materialCategory.setMaterialCategory(new MaterialCategory(model.getParentId()));
			
			materialCategory.setCategoryLevel(model.getCategoryLevel());
			materialCategory.setName(model.getName());
			materialCategoryService.create(materialCategory);
			
			//========标识位===========
			flag = true;
			//记录操作日志使用
			tipMsg = "成功";
            tipType = 0;
		}
		catch (DataAccessException e)
		{
			Log.errorFileSync(">>>>>>>>>>>>>>>>>>>增加商品类别信息异常", e);
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
                Log.errorFileSync(">>>>>>>>>>>>增加商品类别信息回写客户端结果异常", e);
            }
		}
		
		logService.create(new Logdetails(getUser(), "增加商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
        , tipType, "增加商品类别名称为  "+ model.getName() + " " + tipMsg + "！", "增加商品类别" + tipMsg));
		Log.infoFileSync("==================结束调用增加商品类别方法create()===================");
	}
	
	/**
	 * 删除商品类别
	 * @return
	 */
	public String delete()
	{
	    Log.infoFileSync("====================开始调用删除商品类别信息方法delete()================");
	    try 
	    {
	    	materialCategoryService.delete(model.getMaterialCategoryID());
            tipMsg = "成功";
            tipType = 0;
        } 
	    catch (DataAccessException e) 
	    {
	        Log.errorFileSync(">>>>>>>>>>>删除ID为 " + model.getMaterialCategoryID() + "  的商品类别异常", e);
	        tipMsg = "失败";
            tipType = 1;
        }
	    model.getShowModel().setMsgTip(tipMsg);
	    logService.create(new Logdetails(getUser(), "删除商品类别", model.getClientIp(),
	            new Timestamp(System.currentTimeMillis())
	    , tipType, "删除商品类别ID为  "+ model.getMaterialCategoryID() + " " + tipMsg + "！", "删除商品类别" + tipMsg));
	    Log.infoFileSync("====================结束调用删除商品类别信息方法delete()================");
	    return SUCCESS;
	}
	
	/**
	 * 更新商品类别
	 * @return
	 */
	public void update()
	{
	    Boolean flag = false;
        try
        {
        	MaterialCategory materialCategory = materialCategoryService.get(model.getMaterialCategoryID());
        	materialCategory.setMaterialCategory(new MaterialCategory(model.getParentId()));
			
			materialCategory.setCategoryLevel(model.getCategoryLevel());
			materialCategory.setName(model.getName());
        	materialCategoryService.update(materialCategory);
            
            flag = true;
            tipMsg = "成功";
            tipType = 0;
        } 
        catch (DataAccessException e) 
        {
            Log.errorFileSync(">>>>>>>>>>>>>修改商品类别ID为 ： " + model.getMaterialCategoryID() + "信息失败", e);
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
                Log.errorFileSync(">>>>>>>>>>>>修改商品类别回写客户端结果异常", e);
            }
        }
        logService.create(new Logdetails(getUser(), "更新商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
        , tipType, "更新商品类别ID为  "+ model.getMaterialCategoryID() + " " + tipMsg + "！", "更新商品类别" + tipMsg));
	}
	
	/**
	 * 批量删除指定ID商品类别
	 * @return
	 */
	public String batchDelete()
	{
	    try
	    {
	    	materialCategoryService.batchDelete(model.getMaterialCategoryIDs());
            model.getShowModel().setMsgTip("成功");
            //记录操作日志使用
            tipMsg = "成功";
            tipType = 0;
        } 
	    catch (DataAccessException e) 
	    {
	        Log.errorFileSync(">>>>>>>>>>>批量删除商品类别ID为：" + model.getMaterialCategoryIDs() + "信息异常", e);
	        tipMsg = "失败";
            tipType = 1;
        }
	    
	    logService.create(new Logdetails(getUser(), "批量删除商品类别", model.getClientIp(),
                new Timestamp(System.currentTimeMillis())
        , tipType, "批量删除商品类别ID为  "+ model.getMaterialCategoryIDs() + " " + tipMsg + "！", "批量删除商品类别" + tipMsg));
	    return SUCCESS;
	}
	
	/**
	 * 查找商品类别信息
	 * @return
	 */
    public void findBy()
	{
	    try 
	    {
	        PageUtil<MaterialCategory> pageUtil = new  PageUtil<MaterialCategory>();
            pageUtil.setPageSize(model.getPageSize());
            pageUtil.setCurPage(model.getPageNo());
            pageUtil.setAdvSearch(getCondition());
            materialCategoryService.find(pageUtil);
            List<MaterialCategory> dataList = pageUtil.getPageList();
            
            //开始拼接json数据
//            {"total":28,"rows":[
//                {"productid":"AV-CB-01","attr1":"Adult Male","itemid":"EST-18"}
//            ]}
            JSONObject outer = new JSONObject();
            outer.put("total", pageUtil.getTotalCount());
            //存放数据json数组
            JSONArray dataArray = new JSONArray();
            if(null != dataList)
            {
                for(MaterialCategory materialCategory:dataList)
                {
                    JSONObject item = new JSONObject();
                    item.put("Id", materialCategory.getId());
                    item.put("ParentId", materialCategory.getMaterialCategory().getId());
                    item.put("ParentName", materialCategory.getMaterialCategory().getName());
                    item.put("CategoryLevel", materialCategory.getCategoryLevel());
                    item.put("Name", materialCategory.getName());
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
	        Log.errorFileSync(">>>>>>>>>>>>>>>>>>>查找商品类别信息异常", e);
        } 
	    catch (IOException e) 
	    {
            Log.errorFileSync(">>>>>>>>>>>>>>>>>>>回写查询商品类别信息结果异常", e);
        }
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
        condition.put("ParentId_n_eq", model.getParentId());
        condition.put("Id_n_neq", 1);
        condition.put("Id_s_order", "asc");
        return condition;
    }
	
	//=============以下spring注入以及Model驱动公共方法，与Action处理无关==================
	@Override
	public MaterialCategoryModel getModel()
	{
		return model;
	}
	public void setMaterialCategoryService(MaterialCategoryIService materialCategoryService)
    {
        this.materialCategoryService = materialCategoryService;
    }
}
