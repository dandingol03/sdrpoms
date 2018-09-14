<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			$(document).ready(function() {
				//页面准备完毕
			});
			
			//加载datagride整个表格
			$('#baseInfoRailTable').datagrid({
				title:'铁路基本信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"baseInfo/baseInfoRailQueryList",	 		//数据来源
				sortName: 'id',									//排序的列
				sortOrder: 'asc', 								//倒序
				remoteSort: true, 								//服务器端排序
				idField:'id', 									//主键字段 
				queryParams:{}, 								//查询条件
				pagination:true, 								//显示分页
				rownumbers:true, 								//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'铁路名称',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'classifyName',title:'铁路分类',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.classifyName;}                                
						},
						{field:'railLevelName',title:'线别',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railLevelName;}                                
						},
						{field:'stateName',title:'状态',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.stateName;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
				/* 		<sec:authorize url="/baseInfo/addBaseInfoRailDataPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoRailPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoRailPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoRailPopWin();}
						},'-',
						</sec:authorize> */
						/* <sec:authorize url="/baseInfo/deleteBaseInfoRail">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoRail();}
						},'-',
						</sec:authorize>  */
						<sec:authorize url="/baseInfo/baseInfoRailStreamInit">
						{  text:'线路明细', iconCls:'icon-tip',
							handler:function(){showBaseInfoRailStream();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoRailTable').datagrid('clearSelections');}
			});
			
		});
		// 展示铁路明细
		function showBaseInfoRailStream() {
			var rows = $('#baseInfoRailTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的铁路",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条铁路进行查看",'info');
				return;
			}
			showWindow({
				title:'铁路明细',
				href:'baseInfo/baseInfoRailStreamInit',
				width:1000,
				height:600
				,onLoad: function(){
					console.log('=====>1=====');
					/* 根据选中baseInfoRailId 加载数据 */
				    var opts = $("#baseInfoRailStreamTable").datagrid("options");
				    opts.url = "baseInfo/baseInfoRailStreamQueryList?id=" + rows[0].id;
				    $("#baseInfoRailStreamTable").datagrid("load");
				    /* 这里需要解释一下：此处的selectedRail是在 pubMapRailDataInit.jsp中声明的，为了将选中的铁路信息传递过去  */
				}
			});
		}
		//新增
		function addBaseInfoRailPopWin(){
			showWindow({
				title:'增加铁路基本信息',
				href:'baseInfo/addBaseInfoRailPopWin',
				width:600,
				height:360,
				onLoad: function(){
					$('#baseInfoRailAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateBaseInfoRailPopWin(){
			var rows = $('#baseInfoRailTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的铁路",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条铁路进行更新",'info');
				return;
			}
			showWindow({
				title:'更新铁路基本信息',
				href:'baseInfo/updateBaseInfoRailPopWin',
				width:600,
				height:360,
				onLoad: function(){
					$("#baseInfoRailUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteBaseInfoRail(){
			var rows = $('#baseInfoRailTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的铁路",'info');
				return;
			}
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?idList="+n.id;
						} else {
							ps += "&idList="+n.id;
						}	
					});
					$.post('baseInfo/deleteBaseInfoRail'+ps,function(data){
						$('#baseInfoRailTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}

		//表格查询
		function searchBaseInfoRail(){
			var params = $('#baseInfoRailTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoRailQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoRailTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoRail(){
			$('#baseInfoRailQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoRailQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoRailQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">铁路名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="5%" align="right">铁路分类：</td>
							<td width="10%" align="left">
								<!-- <input name="classify" class="easyui-textbox" style="width:90%;"> -->
								<select class="easyui-combobox"
									name="classify" style="width: 90%;" data-options="editable:false">
										<option value="">请选择</option>
										<c:forEach items="${classifyList}" var="classifyListTemp">
											<option value="${classifyListTemp.dictData}">${classifyListTemp.dictName}</option>
										</c:forEach>
								</select>
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoRail();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearBaseInfoRail();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoRailTable"></table>
			</div>
		</div>
	</div>
</body>
