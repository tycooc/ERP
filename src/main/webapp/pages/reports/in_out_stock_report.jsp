<%@page import="com.fxt.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String clientIp = Tools.getCurrentUserIP();
%>
<!DOCTYPE html>
<html>
  	<head>
    	<title>进销存管理</title>
        <meta charset="utf-8">
		<!-- 指定以IE8的方式来渲染 -->
		<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    	<link rel="shortcut icon" href="<%=path%>/images/favicon.ico" type="image/x-icon" />
    	<script type="text/javascript" src="<%=path %>/js/jquery-1.8.0.min.js"></script>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/default/easyui.css"/>
		<link rel="stylesheet" type="text/css" href="<%=path %>/js/easyui-1.3.5/themes/icon.css"/>
		<link type="text/css" rel="stylesheet" href="<%=path %>/css/common.css" />
		<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/jquery.easyui.min.js"></script>
		<script type="text/javascript" src="<%=path %>/js/easyui-1.3.5/locale/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript" src="<%=path %>/js/My97DatePicker/WdatePicker.js"></script>
		<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
  	</head>
  	<body>
  		<!-- 查询 -->
		<div id = "searchPanel"	class="easyui-panel" style="padding:10px;" title="查询窗口" iconCls="icon-search" collapsible="true" closable="false">
			<table id="searchTable">
				<tr>
			    	<td>月份：</td>
					<td>
						<input type="text" name="searchMonth" id="searchMonth" onClick="WdatePicker({dateFmt:'yyyy-MM'})" class="txt Wdate" style="width:180px;"/>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="exprotBtn">导出</a> 
						&nbsp;&nbsp;<span class="total-count"></span>
					</td>
				</tr>
			</table>
		</div>
		
		<!-- 数据显示table -->
		<div id = "tablePanel"	class="easyui-panel" style="padding:1px;top:300px;" title="库存状况列表" iconCls="icon-list" collapsible="true" closable="false">
			<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
		</div>
			    
		<script type="text/javascript">
			var depotList = null;
			var depotID = null;
			//初始化界面
			$(function()
			{
				initTableData();
				ininPager();
				exportExcel();
			});	
			
			//导出EXCEL
			function exportExcel() {
				$("#exprotBtn").off("click").on("click",function(){
					if(!$("#searchPanel .total-count").text()) {
						$.messager.alert('导出提示','请先选择月份再进行查询！','error');
					}
					else {
						showPersonDetails(1,3000);
						//此处直接去做get请求，用下面的查询每月统计的方法，去获取list，参数长度虽长，但还是可以用get
						//window.location.href = "<%=path%>/depotItem/exportExcel.action?browserType=" + getOs();
					}
				});				
			}
			
			//初始化表格数据
			function initTableData()
			{
				$('#tableData').datagrid({
					//title:'列表',
					//iconCls:'icon-save',
					//width:700,
					height:heightInfo,
					nowrap: false,
					rownumbers: true,
					//动画效果
					animate:false,
					//选中单行
					singleSelect : true,
					//url:'<%=path %>/depotItem/findByAll.action?pageSize=' + initPageSize,
					pagination: true,
					//交替出现背景
					striped : true,
					//loadFilter: pagerFilter,
					pageSize: 10,
					pageList: [10,50,100],
					columns:[[
			          { title: '名称',field: 'MaterialName',width:60},
			          { title: '款号',field: 'MaterialModel',width:80},
			          { title: '颜色',field: 'MaterialColor',width:80},
			          { title: '单价',field: 'UnitPrice',width:60,formatter: function(value,row,index){
							return value.toFixed(2);
						}
					  },
			          { title: '上月结存数量',field: 'prevSum',width:80},
			          { title: '入库数量',field: 'InSum',width:60},
			          { title: '出库数量',field: 'OutSum',width:60},
			          { title: '本月结存数量',field: 'thisSum',width:80},
			          { title: '结存金额',field: 'thisAllPrice',width:60,
						formatter: function(value,row,index){
							return value.toFixed(2);
						}
			          }			          
					]],
					onLoadError:function()
					{
						$.messager.alert('页面加载提示','页面加载异常，请稍后再试！','error');
						return;
					}    
				});
			}
			
			//初始化键盘enter事件
			$(document).keydown(function(event)
			{  
			   	//兼容 IE和firefox 事件 
			    var e = window.event || event;  
			    var k = e.keyCode||e.which||e.charCode;  
			    //兼容 IE,firefox 兼容  
			    var obj = e.srcElement ? e.srcElement : e.target;  
			    //绑定键盘事件为 id是指定的输入框才可以触发键盘事件 13键盘事件 ---遗留问题 enter键效验 对话框会关闭问题
			    if(k == "13"&&(obj.id=="Type"||obj.id=="Name"))
			    {  
			        $("#savePerson").click();
			    }
			    //搜索按钮添加快捷键
			    if(k == "13"&&(obj.id=="searchType"))
			    {  
			        $("#searchBtn").click();
			    }  
			}); 
			//分页信息处理
			function ininPager()
			{
				try
				{
					var opts = $("#tableData").datagrid('options');  
					var pager = $("#tableData").datagrid('getPager'); 
					pager.pagination({  
						onSelectPage:function(pageNum, pageSize)
						{  
							opts.pageNumber = pageNum;  
							opts.pageSize = pageSize;  
							pager.pagination('refresh',
							{  
								pageNumber:pageNum,  
								pageSize:pageSize  
							});  
							showPersonDetails(pageNum,pageSize);
						}  
					}); 
				}
				catch (e) 
				{
					$.messager.alert('异常处理提示',"分页信息异常 :  " + e.name + ": " + e.message,'error');
				}
			}
			
			//增加
			var url;
			var personID = 0;
			//保存编辑前的名称
			var orgPerson = "";

			//搜索处理
			$("#searchBtn").unbind().bind({
				click:function()
				{
					showPersonDetails(1,initPageSize);	
					var opts = $("#tableData").datagrid('options');  
					var pager = $("#tableData").datagrid('getPager'); 
					opts.pageNumber = 1;  
					opts.pageSize = initPageSize;  
					pager.pagination('refresh',
					{  
						pageNumber:1,  
						pageSize:initPageSize  
					});  
				}
			});
			
			function showPersonDetails(pageNo,pageSize)
			{
				$.ajax({
					type:"post",
					url: "<%=path %>/depotHead/findByMonth.action",
					dataType: "json",
					data: ({
						MonthTime:$("#searchMonth").val()
					}),
					success: function (res)
					{
						var HeadIds = res.HeadIds;
						if(HeadIds) {
							//获取排序后的产品ID
							$.ajax({
								type:"post",
								url: "<%=path %>/material/findByOrder.action",
								dataType: "json",
								success: function (resNew)
								{
									var MIds = resNew.mIds;
									if(MIds) {
										if(pageSize === 3000) {
											window.location.href = "<%=path%>/depotItem/exportExcel.action?browserType=" + getOs() + "&pageNo=" + pageNo + "&pageSize=" + pageSize + "&MonthTime=" + $("#searchMonth").val() + "&HeadIds=" + HeadIds + "&MaterialIds=" + MIds;											
										}
										else {
											$.ajax({
												type:"get",
												url: "<%=path %>/depotItem/findByAll.action",
												dataType: "json",
												data: ({
													pageNo:pageNo,
													pageSize:pageSize,
													MonthTime:$("#searchMonth").val(),
													HeadIds:HeadIds,
													MaterialIds:MIds
												}),
												success: function (data)
												{
													$("#tableData").datagrid('loadData',data);											
													
												},
												//此处添加错误处理
									    		error:function()
									    		{
									    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
													return;
												}
											});										
																					
											//总金额
											$.ajax({
												type:"post",
												url: "<%=path %>/depotItem/totalCountMoney.action",
												dataType: "json",
												data: ({
													MonthTime:$("#searchMonth").val(),
													HeadIds:HeadIds,
													MaterialIds:MIds
												}),
												success: function (data)
												{
													if(data && data.totalCount) {
														var count = data.totalCount.toString();
														count = count.substring(0,count.lastIndexOf('.')+3);
														$("#searchPanel .total-count").text("本月合计金额:" + count + "元");//本月合计金额
													}
												},
												//此处添加错误处理
									    		error:function()
									    		{
									    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
													return;
												}
											});
										}										
									}
									else {
										$.messager.alert('查询提示','本月无数据！','error');
									}
								},
								//此处添加错误处理							
					    		error:function()
					    		{
					    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
									return;
								}
							});
						}
						else {
							$.messager.alert('查询提示','本月无数据！','error');
						}
					},
					//此处添加错误处理
		    		error:function()
		    		{
		    			$.messager.alert('查询提示','查询数据后台异常，请稍后再试！','error');
						return;
					}
				});
			}			
		</script>
	</body>
</html>