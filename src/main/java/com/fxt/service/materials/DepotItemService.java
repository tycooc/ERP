package com.fxt.service.materials;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import com.fxt.base.BaseService;
import com.fxt.base.Log;
import com.fxt.util.AssetConstants;
import com.fxt.dao.materials.DepotItemIDAO;
import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.DepotItem;
import com.fxt.util.PageUtil;
import com.fxt.util.Tools;

public class DepotItemService extends BaseService<DepotItem> implements DepotItemIService
{
	@SuppressWarnings("unused")
	private DepotItemIDAO depotItemDao;

	
	public void setDepotItemDao(DepotItemIDAO depotItemDao) {
		this.depotItemDao = depotItemDao;
	}


	@Override
	protected Class<DepotItem> getEntityClass()
	{
		return DepotItem.class;
	}
	
    @Override
    public void findByType(PageUtil<DepotItem> pageUtil, String type,Long MId, String MonthTime,Boolean isPrev) throws fxtException
    {
    	depotItemDao.findByType(pageUtil, type, MId, MonthTime,isPrev);
    }
    
    @Override
    public void buyOrSale(PageUtil<DepotItem> pageUtil, String type,String subType, Long MId, String MonthTime, String sumType) throws fxtException
    {
    	depotItemDao.buyOrSale(pageUtil, type, subType, MId, MonthTime, sumType);
    }
    
	/**
	 * 导出Excel表格
	 */
	@Override
	public InputStream  exmportExcel(String isAllPage,JSONArray dataArray)throws fxtException
	{
		try
		{			
			//将OutputStream转化为InputStream
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			putDataOnOutputStream(out,dataArray);
			return new ByteArrayInputStream(out.toByteArray());
		}
		catch (Exception e)
		{
			Log.errorFileSync(">>>>>>>>>>>>>>>>>>>>>>>导出信息为excel表格异常", e);
			throw new fxtException("export asset info to excel exception",e);
		}
	}
	
	/**
	 * 生成excel表格
	 * @param os
	 */
	@SuppressWarnings("deprecation")
	private void putDataOnOutputStream(OutputStream os,JSONArray dataArray) 
	{
		WritableWorkbook workbook = null;
		try 
		{
			workbook = Workbook.createWorkbook(os);
			WritableSheet sheet = workbook.createSheet("进销存报表", 0);
			//增加列头
			int[] colunmWidth = {10,10,10,10,15,15,15,15,15};
			String[] colunmName = {"名称","款号","颜色","单价","上月结存数量","入库数量","出库数量","本月结存数量","结存金额"};
			for(int i = 0 ;i < colunmWidth.length;i ++)
			{
				sheet.setColumnView(i,colunmWidth[i]);
				sheet.addCell(new Label(i, 0, colunmName[i]));
			}
			if (null != dataArray &&dataArray.size() > 0) 
			{
			    for(int j=0; j < dataArray.size(); j++){ 
			    	JSONObject jo = JSONObject.fromObject(dataArray.get(j)); 
		    		sheet.addCell(new Label(0, j+1, jo.getString("MaterialName")));
		    		sheet.addCell(new Label(1, j+1, jo.getString("MaterialModel")));
		    		sheet.addCell(new Label(2, j+1, jo.getString("MaterialColor")));
		    		sheet.addCell(new Label(3, j+1, jo.getString("UnitPrice")));
		    		sheet.addCell(new Label(4, j+1, jo.getString("prevSum")));
		    		sheet.addCell(new Label(5, j+1, jo.getString("InSum")));
		    		sheet.addCell(new Label(6, j+1, jo.getString("OutSum")));
		    		sheet.addCell(new Label(7, j+1, jo.getString("thisSum")));
		    		double d = Double.parseDouble(jo.getString("thisAllPrice").toString()); 
		    		String s1 = String.format("%.2f", d);  		
		    		sheet.addCell(new Label(8, j+1, s1));
			    }
			}			
			workbook.write();
			workbook.close();
		}
		catch (Exception e)
		{
			Log.errorFileSync(">>>>>>>>>>>>>>>>>>>>>>>导出资产信息为excel表格异常", e);
		}
	}
}
