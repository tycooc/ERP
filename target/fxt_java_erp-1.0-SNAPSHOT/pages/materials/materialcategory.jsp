<%@page import="com.fxt.util.Tools"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String clientIp = Tools.getCurrentUserIP();
%>
<!DOCTYPE html>
<html>
  	<head>
    	<title>商品类别管理</title>
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
		<script type="text/javascript" src="<%=path %>/js/common/common.js"></script>
  	</head>
  	<body>
  		<!-- 查询 -->
		<div id = "searchPanel"	class="easyui-panel" style="padding:10px;" title="查询窗口" iconCls="icon-search" collapsible="true" closable="false">
			<table id="searchTable">
				<tr>
			    	<td>类别：</td>
					<td>
						<select name="searchParentId_f" id="searchParentId_f"  style="width:100px;"></select>
						<select name="searchParentId_s" id="searchParentId_s"  style="width:100px;"></select>
						<select name="searchParentId_t" id="searchParentId_t"  style="width:100px;"></select>
					</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>&nbsp;</td>
					<td>
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-search" id="searchBtn">查询</a>&nbsp;&nbsp;
						<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-redo" id="searchResetBtn">重置</a> 
					</td>
				</tr>
			</table>
		</div>
		
		<!-- 数据显示table -->
		<div id = "tablePanel"	class="easyui-panel" style="padding:1px;top:300px;" title="商品类别列表" iconCls="icon-list" collapsible="true" closable="false">
			<table id="tableData" style="top:300px;border-bottom-color:#FFFFFF"></table>
		</div>
		
	    <div id="materialCategoryDlg" class="easyui-dialog" style="width:380px;padding:10px 20px"
	            closed="true" buttons="#dlg-buttons" modal="true" cache="false" collapsible="false" closable="true">
	        <form id="materialCategoryFM" method="post"  novalidate>
	            <table>
	            <tr>
	            <td>名称</td>
	            <td style="padding:5px"><input name="Name" id="Name" class="easyui-validatebox" data-options="required:true,validType:'length[2,30]'" style="width: 100px;height: 20px"/></td>
	            </tr>
	            <tr>
	            <td>层次</td>
	            <td style="padding:5px">
	            <select name="CategoryLevel" id="CategoryLevel"  style="width:100px;">
						<option value="1">一级分类</option>
						<option value="2">二级分类</option>
						<option value="3">三级分类</option>
						</select>
			    </td>
			    </tr>
	            <tr>
	            <td>上级</td>
	            <td style="padding:5px">
                <select name="ParentId_f" id="ParentId_f" style="width:100px;height: 20px"></select>
                <select name="ParentId_s" id="ParentId_s" style="width:100px;height: 20px"></select>
                </td>
	            </tr>
	            </table>
	            <input type="hidden" name="clientIp" id="clientIp" value="<%=clientIp %>"/>
	        </form>
	    </div>
	    <div id="dlg-buttons">
	        <a href="javascript:void(0)" id="saveMaterialCategory" class="easyui-linkbutton" iconCls="icon-ok">保存</a>
	        <a href="javascript:void(0)" id="cancelMaterialCategory" class="easyui-linkbutton" iconCls="icon-cancel" onclick="javascript:$('#materialCategoryDlg').dialog('close')">取消</a>
	    </div>
	    
		<script type="text/javascript">
			var materialCategoryList = null;
			var materialCategoryID = null;
			var parentid_search=null;
			var lei=null;
			//初始化界面
			$(function()
			{
				//初始化系统基础信息
				initSystemData(1);
				initSelectInfo(1);
				initTableData();
				ininPager();
				initForm();
			});	
		
			//初始化系统基础信息
			function initSystemData(parentid_search)
			{
				$.ajax({
					type:"post",
					url: "<%=path%>/materialCategory/getBasicData.action",
					data: ({
						ParentId:parentid_search
					}),
					//设置为同步
					async:false,
					dataType: "json",
					success: function (systemInfo)
					{
						materialCategoryList = systemInfo.showModel.map.materialCategoryList;
						var msgTip = systemInfo.showModel.msgTip;
						if(msgTip == "exceptoin")
						{
							$.messager.alert('提示','查找商品类别异常,请与管理员联系！','error');
							return;
						}	
					}
				});				
			}
			//初始化页面选项卡
			function initSelectInfo(lei)
			{
				var options = "";
				
				if(materialCategoryList !=null)
				{
					options = "";
					for(var i = 0 ;i < materialCategoryList.length;i ++)
					{
						var materialCategory = materialCategoryList[i];
						if(0 == i)
						{
							materialCategoryID = materialCategory.id;
						}
						options += '<option value="' + materialCategory.id + '">' + materialCategory.name + '</option>';
					}	
					//$("#ParentId").empty().append(options);
					if(lei==1)
					{
						$("#searchParentId_f").empty().append('<option value="">全部</option>').append(options);
					}
					else if(lei==2)
					{
						$("#searchParentId_s").empty().append('<option value="">全部</option>').append(options);
					}
					else if(lei==3)
					{
						$("#searchParentId_t").empty().append('<option value="">全部</option>').append(options);
					}
					else if(lei==11)
					{
						$("#ParentId_f").empty().append('<option value="">全部</option>').append(options);
					}
					else if(lei==12)
					{
						$("#ParentId_s").empty().append('<option value="">全部</option>').append(options);
					}
				}
			}
			
			$("#searchParentId_f").change(
				function(){
					var parentid_search=$("#searchParentId_f").val();
					if(parentid_search!='')
					{
						initSystemData(parentid_search);
						initSelectInfo(2);
					}
				}
			);
			
			$("#searchParentId_s").change(
				function(){
					var parentid_search=$("#searchParentId_s").val();
					if(parentid_search!='')
					{
						initSystemData(parentid_search);
						initSelectInfo(3);
					}
				}
			);
			
			$("#CategoryLevel").change(
				function(){
					var CategoryLevel=$("#CategoryLevel").val();
					if(CategoryLevel==1)
					{
						$("#ParentId_f").empty();
						$("#ParentId_s").empty();
					}
					else if(CategoryLevel==2)
					{
						initSystemData(1);
						initSelectInfo(11);
						$("#ParentId_s").enabled=false;
					}
					else if(CategoryLevel==3)
					{
						initSystemData(1);
						initSelectInfo(11);
					}
				}
			);
			
			$("#ParentId_f").change(
				function(){
					var ParentId_f=$("#ParentId_f").val();
					initSystemData(ParentId_f);
					initSelectInfo(12);
				}
			);
			
			//防止表单提交重复
			function initForm()
			{
				$('#materialCategoryFM').form({
				    onSubmit: function(){
				        return false;
				    }
				});
			}
			
			//初始化表格数据
			function initTableData()
			{
				$('#tableData').datagrid({
					//title:'商品类别列表',
					//iconCls:'icon-save',
					//width:700,
					height:heightInfo,
					nowrap: false,
					rownumbers: false,
					//动画效果
					animate:false,
					//选中单行
					singleSelect : true,
					collapsible:false,
					selectOnCheck:false,
					//fitColumns:true,
					//单击行是否选中
					//checkOnSelect : false,
					url:'<%=path %>/materialCategory/findBy.action?pageSize=' + initPageSize+'&ParentId=1',
					pagination: true,
					//交替出现背景
					striped : true,
					//loadFilter: pagerFilter,
					pageSize: initPageSize,
					pageList: initPageNum,
					columns:[[
					  { field: 'Id',width:35,align:"center",checkbox:true},
			          { title: '名称',field: 'Name',width:250},
			          { title: '操作',field: 'op',align:"center",width:130,formatter:function(value,rec)
			         	{
							var str = '';
							var rowInfo = rec.Id + 'AaBb' + rec.ParentId+ 'AaBb' + rec.CategoryLevel+ 'AaBb' + rec.Name;
        					if(1 == value)
        					{
        						str += '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png" style="cursor: pointer;" onclick="editMaterialCategory(\'' + rowInfo + '\');"/>&nbsp;<a onclick="editMaterialCategory(\'' + rowInfo + '\');" style="text-decoration:none;color:black;" href="javascript:void(0)">编辑</a>&nbsp;&nbsp;';
        						str += '<img src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_remove.png" style="cursor: pointer;" onclick="deleteMaterialCategory('+ rec.Id +');"/>&nbsp;<a onclick="deleteMaterialCategory('+ rec.Id +');" style="text-decoration:none;color:black;" href="javascript:void(0)">删除</a>&nbsp;&nbsp;';
        					}
        					return str;
						}
			          }
					]],
					toolbar:[
						{
							id:'addMaterialCategory',
							text:'增加',
							iconCls:'icon-add',
							handler:function()
							{
								addMaterialCategory();
							}
						},
						{
							id:'deleteMaterialCategory',
							text:'删除',
							iconCls:'icon-remove',
							handler:function()
							{
								batDeleteMaterialCategory();	
							}
						}
					],
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
			    if(k == "13"&&(obj.id=="CategoryLevel"||obj.id=="Name"))
			    {  
			        $("#saveMaterialCategory").click();
			    }
			    //搜索按钮添加快捷键
			    if(k == "13"&&(obj.id=="searchParentId"))
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
							showMaterialCategoryDetails(pageNum,pageSize);
						}  
					}); 
				}
				catch (e) 
				{
					$.messager.alert('异常处理提示',"分页信息异常 :  " + e.name + ": " + e.message,'error');
				}
			}
			
			//删除商品类别信息
			function deleteMaterialCategory(materialCategoryID)
			{
				$.messager.confirm('删除确认','确定要删除此商品类别信息吗？',function(r)
			 	{
                    if (r)
                    {
						$.ajax({
							type:"post",
							url: "<%=path %>/materialCategory/delete.action",
							dataType: "json",
							data: ({
								materialCategoryID : materialCategoryID,
								clientIp:'<%=clientIp %>'
							}),
							success: function (tipInfo)
							{
								var msg = tipInfo.showModel.msgTip;
								if(msg == '成功')
								{
									//加载完以后重新初始化
									$("#searchBtn").click();
								}
								else
									$.messager.alert('删除提示','删除商品类别信息失败，请稍后再试！','error');
							},
							//此处添加错误处理
				    		error:function()
				    		{
				    			$.messager.alert('删除提示','删除商品类别信息异常，请稍后再试！','error');
								return;
							}
						});			
                    }
                });
			}
			
			//批量删除商品类别
			function batDeleteMaterialCategory()
			{
				var row = $('#tableData').datagrid('getChecked');	
				if(row.length == 0)
				{
					$.messager.alert('删除提示','没有记录被选中！','info');				
					return;	
				}
				if(row.length > 0)
				{
					$.messager.confirm('删除确认','确定要删除选中的' + row.length + '条商品类别信息吗？',function(r)
				 	{
	                    if (r)
	                    {
	                    	var ids = "";
	                        for(var i = 0;i < row.length; i ++)
	                        {
	                        	if(i == row.length-1)
	                        	{
	                        		ids += row[i].Id;
	                        		break;
	                        	}
	                        	//alert(row[i].id);
	                        	ids += row[i].Id + ",";
	                        }
	                        $.ajax({
								type:"post",
								url: "<%=path %>/materialCategory/batchDelete.action",
								dataType: "json",
								async :  false,
								data: ({
									materialCategoryIDs : ids,
									clientIp:'<%=clientIp %>'
								}),
								success: function (tipInfo)
								{
									var msg = tipInfo.showModel.msgTip;
									if(msg == '成功')
									{
										//加载完以后重新初始化
										$("#searchBtn").click();
										$(":checkbox").attr("checked",false);
									}
									else
										$.messager.alert('删除提示','删除商品类别信息失败，请稍后再试！','error');
								},
								//此处添加错误处理
					    		error:function()
					    		{
					    			$.messager.alert('删除提示','删除商品类别信息异常，请稍后再试！','error');
									return;
								}
							});	
	                    }
	                });
				 }
			}
			
			//增加
			var url;
			var materialCategoryID = 0;
			//保存编辑前的名称
			var orgMaterialCategory = "";
			
			function addMaterialCategory()
			{
				$("#clientIp").val('<%=clientIp %>');
				$('#materialCategoryFM').form('clear');
				$('#materialCategoryDlg').dialog('open').dialog('setTitle','<img src="<%=path%>/js/easyui-1.3.5/themes/icons/edit_add.png"/>&nbsp;增加商品类别信息');
				$(".window-mask").css({ width: webW ,height: webH});
	            $("#Name").val("").focus();
	            
	            orgMaterialCategory = "";
	            materialCategoryID = 0;
	            url = '<%=path %>/materialCategory/create.action';
			}
			
			//保存信息
			$("#saveMaterialCategory").unbind().bind({
				click:function()
				{
					if(!$('#materialCategoryFM').form('validate'))
						return;
					else 
					{
						var parent=1;
						if($("#ParentId_f").val()!=""&&$("#ParentId_f").val()!=null)
					    {
							parent=$("#ParentId_f").val();
					    }
					    if($("#ParentId_s").val()!=""&&$("#ParentId_s").val()!=null)
					    {
							parent=$("#ParentId_s").val();
					    }
						$.ajax({
							type:"post",
							url: url,
							dataType: "json",
							async :  false,
							data: ({
								ParentId : parent,
								CategoryLevel : $("#CategoryLevel").val(),
								Name : $.trim($("#Name").val()),
								clientIp:'<%=clientIp %>'
							}),
							success: function (tipInfo)
							{
								if(tipInfo)
								{
									$('#materialCategoryDlg').dialog('close');
			                        
									var opts = $("#tableData").datagrid('options'); 
									showMaterialCategoryDetails(opts.pageNumber,opts.pageSize); 
								}
								else
								{
									$.messager.show({
			                            title: '错误提示',
			                            msg: '保存商品类别信息失败，请稍后重试!'
			                        });
								}
							},
							//此处添加错误处理
				    		error:function()
				    		{
				    			$.messager.alert('提示','保存商品类别信息异常，请稍后再试！','error');
								return;
							}
						});	
					}
				}
			});
			
			//编辑信息
	        function editMaterialCategory(materialCategoryTotalInfo)
	        {
	        	var materialCategoryInfo = materialCategoryTotalInfo.split("AaBb");
	            
	            $("#clientIp").val('<%=clientIp %>');
	            $("#ParentId").focus().val(materialCategoryInfo[1]);
	            $("#CategoryLevel").val(materialCategoryInfo[2]);
	            $("#Name").val(materialCategoryInfo[3]);
	            
	            //orgMaterialCategory = materialCategoryInfo[1];
                $('#materialCategoryDlg').dialog('open').dialog('setTitle','<img src="<%=path%>/js/easyui-1.3.5/themes/icons/pencil.png"/>&nbsp;编辑商品类别信息');
                $(".window-mask").css({ width: webW ,height: webH});
                materialCategoryID = materialCategoryInfo[0];
                //焦点在名称输入框==定焦在输入文字后面 
                $("#Name").val("").focus().val(materialCategoryInfo[3]);
                url = '<%=path %>/materialCategory/update.action?materialCategoryID=' + materialCategoryInfo[0];
	        }
	        
			//搜索处理
			$("#searchBtn").unbind().bind({
				click:function()
				{
					showMaterialCategoryDetails(1,initPageSize);	
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
			
			
			function showMaterialCategoryDetails(pageNo,pageSize)
			{
				var parent=1;
				if($("#searchParentId_f").val()!=""&&$("#searchParentId_f").val()!=null)
			    {
					parent=$("#searchParentId_f").val();
			    }
			    if($("#searchParentId_s").val()!=""&&$("#searchParentId_s").val()!=null)
			    {
					parent=$("#searchParentId_s").val();
			    }
			    if($("#searchParentId_t").val()!=""&&$("#searchParentId_t").val()!=null)
			    {
					parent=$("#searchParentId_t").val();
					
			    }
				$.ajax({
					type:"post",
					url: "<%=path %>/materialCategory/findBy.action",
					dataType: "json",
					data: ({
						ParentId:parent,
						pageNo:pageNo,
						pageSize:pageSize
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
			}
			
			//重置按钮
			$("#searchResetBtn").unbind().bind({
				click:function(){
					$("#searchParentId_f").val("");
					$("#searchParentId_s").val("");
					$("#searchParentId_t").val("");
					//加载完以后重新初始化
					$("#searchBtn").click();
			    }	
			});
			
		</script>
	</body>
</html>