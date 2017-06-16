package com.fxt.service.materials;

import java.io.InputStream;
import java.util.List;

import net.sf.json.JSONArray;

import com.fxt.base.BaseIService;
import com.fxt.util.fxtException;
import com.fxt.model.po.Asset;
import com.fxt.model.po.DepotHead;
import com.fxt.model.po.DepotItem;
import com.fxt.util.PageUtil;

public interface DepotItemIService extends BaseIService<DepotItem>
{
	void findByType(PageUtil<DepotItem> depotItem, String type, Long MId, String MonthTime, Boolean isPrev)throws fxtException;
	
	void buyOrSale(PageUtil<DepotItem> depotItem, String type, String subType, Long MId, String MonthTime, String sumType)throws fxtException;
	
	/**
	 * 导出信息
	 * @return
	 */
	InputStream exmportExcel(String isAllPage, JSONArray dataArray)throws fxtException;
}
