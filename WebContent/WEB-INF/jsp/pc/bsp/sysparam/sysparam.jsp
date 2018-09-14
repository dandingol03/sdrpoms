<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			//加载datagride
			$('#sysParamTable').datagrid({
				title:'密钥参数列表', 			//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:false,				//可折叠
				url:"sysparam/queryList", 		//数据来源
				sortName: 'paramId',			//排序的列
				sortOrder: 'desc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'paramId', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'paramCode',title:'参数编码',width:20,sortable:false,
							formatter:function(value,row,index){return row.paramCode;}
						},
						{field:'paramName',title:'参数名称',width:20,sortable:false,
							formatter:function(value,row,index){return row.paramName;}
						},
						{field:'paramValue',title:'参数值',width:20,sortable:false,
							formatter:function(value,row,index){return row.paramValue;}
						},
						{field:'paramStatus',title:'参数状态',width:20,sortable:false,
							formatter:function(value,row,index){return row.paramStatus == '0'?'禁用':'使用';}                          
						}
						]],
				toolbar:[
						{text:'新增', iconCls:'icon-add', 
							handler:function(){addSysParamRow();}
						},'-',
						{text:'更新', iconCls:'icon-edit',
							handler:function(){updateSysParamRow();}
						},'-',
						{text:'删除', iconCls:'icon-remove',
							handler:function(){deleteSysParamRow();}
						},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#sysParamTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addSysParamRow(){
			showWindow({
				title:'增加系统参数信息',
				href:'sysparam/addPopWin',
				width:350,
				height:260,
				onLoad: function(){$('#sysParamAddForm').form('reset');}
			});
		}
				
		//更新    
		function updateSysParamRow(){
			var rows = $('#sysParamTable').datagrid('getSelections');
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的系统参数",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一项系统参数进行更新",'info');
				return;
			}
			showWindow({
				title:'更新系统参数信息',
				href:'sysparam/updatePopWin',
				width:350,
				height:260,
				onLoad: function(){
					$("#sysParamUpForm").form('load', rows[0]);
				}
			});
		}
	
		//删除
		function deleteSysParamRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#sysParamTable').datagrid('getSelections');
					var ps = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?paramId="+n.uid;
						} else {
							ps += "&paramId="+n.uid;
						}	
					});
					$.post('sysparam/delSysParams'+ps,function(data){
						$('#sysParamTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
				
		//表格查询
		function searchSysParam(){
			var params = $('#sysParamTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#sysParamQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#sysParamTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearSysParamForm(){
			$('#sysParamQuForm').form('clear');
			//searchUser();
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="sysParamQuForm" class="fw fh">
					<table class="fw fh">
						<tr>
							<td width="8%" align="right">参数编码：</td>
							<td width="17%" align="left"><input name="paramCode" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="right">参数名称：</td>
							<td width="17%" align="left"><input name="paramName" class="easyui-textbox" style="width:90%;"></td>
							<td width="8%" align="left">
								<a onclick="searchSysParam();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
							<td width="42%" align="left">
								<a onclick="clearSysParamForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>  
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:85%;">
			<div class="fh pl5 pr10">
				<table id="sysParamTable"></table>
			</div>
		</div>
	</div>
</body>
