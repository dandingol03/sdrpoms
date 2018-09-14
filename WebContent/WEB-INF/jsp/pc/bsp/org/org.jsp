<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			//----------------------------------------加载机构树开始----------------------------------------//
		    $(document).ready(function(){
			    var jqueryObj=$("#myOrgId");
		 		makeOrgTree(jqueryObj);
			});
		  	//----------------------------------------加载机构树结束----------------------------------------//
			//加载datagride
			$('#orgTable').datagrid({
				title:'机构列表', 				//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:false,				//可折叠
				url:"org/queryList", 			//数据来源
				sortName: 'orgId',				//排序的列
				sortOrder: 'asc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'orgId', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'orgName',title:'机构名称',width:20,sortable:false,
							formatter:function(value,row,index){return row.orgName;}                          
						},
						{field:'orgCode',title:'机构代码',width:20,sortable:true,hidden:false,
							formatter:function(value,row,index){return row.orgCode;} //需要formatter一下才能显示正确的数据                                
						},
						{field:'enable',title:'是否可用',width:20,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.enable;}                          
						},
						{field:'orgAddress',title:'机构地址',width:20,sortable:false,                              
							formatter:function(value,row,index){return row.orgAddress;}
						},
						{field:'orgDesc',title:'机构描述',width:30,sortable:true,
							formatter:function(value,row,index){return row.orgDesc;}
						},
						{field:'orgReserve1',title:'预留属性1',width:30,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.orgReserve1;}
						},
						{field:'orgReserve2',title:'预留属性2',width:30,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.orgReserve2;}
						},
						{field:'id',title:'机构描述ID',width:30,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.id;}
						},
						{field:'orgLevel',title:'机构级别',width:30,sortable:true,hidden:true,
							formatter:function(value,row,index){return row.orgLevel;}
						},
						{field:'pId',title:'机构描述PID',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.pId;}
						},
						{field:'isParent',title:'是否父节点',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.isParent;}
						},
						{field:'open',title:'是否展开',width:20,sortable:false,hidden:true,
							formatter:function(value,row,index){return row.open;}
						},
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addOrgRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateOrgRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteOrgRow();}
						},'-',
						{text:'查看机构树', iconCls:'icon-search',
							handler:function(){showOrgTree();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#orgTable').datagrid('clearSelections');}
			});
			
		});
		
		//新增
		function addOrgRow(){
			showWindow({
				title:'增加机构信息',
				href:'org/addPopWin',
				width:350,
				height:420,
				onLoad: function(){
					$('#orgAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateOrgRow(){
			var rows = $('#orgTable').datagrid('getSelections');
			//alert(rows[0].userId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的机构",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个机构进行更新",'info');
				return;
			}
			showWindow({
				title:'更新机构信息',
				href:"org/updatePopWin?orgDescPId="+rows[0].pId+"",
				width:350,
				height:420,
				onLoad: function(){
					$("#orgUpForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteOrgRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#orgTable').datagrid('getSelections');
					var ps = "";
					var isRoot = 0;
					$.each(rows,function(i,n){
						if(n.pId == '0'){
							$.messager.alert('提示', '组织机构根节点不能删除', 'info');
							isRoot = 1;
						}else{
							if(i==0){
								ps += "?orgId="+n.uid;
							} else {
								ps += "&orgId="+n.uid;
							}
						}
					});
					//不包含组织机构根节点时进行删除
					if(isRoot == 0){
						$.post('org/delOrgs'+ps,function(data){
							$('#orgTable').datagrid('reload');
							//机构删除后重新加载查询条件中的机构树
							$.messager.alert('提示',data.mes,'info');
						});
					}
				}
			});
		}
	
		//查看机构树
		function showOrgTree(){
			showWindow({
				title:'查看机构机树',
				href:'org/treePopWin',
				width:350,
				height:480,
				onLoad: function(){}
			});
		}
				
		//表格查询
		function searchOrg(){
			var params = $('#orgTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#orgQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#orgTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearOrgForm(){
			$('#orgQuForm').form('clear');
			//searchUser();
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="orgQuForm" class="fw fh">
					<table class="fw fh">
						<tr>
							<td width="8%" align="right">上级机构：</td>
							<td width="17%" align="left">
								<input id="myOrgId" type="text" name="orgId" class="easyui-textbox" 
								style="width:90%;">
							</td>
							<td width="8%" align="right">机构名称：</td>
							<td width="17%" align="left"><input  name="orgName" type="text" class="easyui-textbox" 
								style="width:90%;"></td>
							<td width="10%" align="right">
								<a onclick="searchOrg();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
							<td width="40%" align="left">
								<a onclick="clearOrgForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>  
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:85%;">
			<div class="fh pl5 pr10">
				<table id="orgTable"></table>
			</div>
		</div>
	</div>
	
</body>
