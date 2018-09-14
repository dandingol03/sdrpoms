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
				var jqueryObj=$("#orgIdTrajection");
		 		makeOrgTree(jqueryObj);
			});
			//加载datagride整个表格
			$('#baseInfoHiddenTrajectionTable').datagrid({
				title:'行人易穿行部位基本信息表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"baseInfo/BaseInfoPartTrajectionQueryList",	 	//数据来源
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
				{field:'name',title:'易穿行部位名称',sortable:true, hidden:false,width:150,                             
					formatter:function(value,row,index){return row.name;}                                
				},
				{field:'status',title:'易穿行部位状态',sortable:true, hidden:false,width:150,                             
					formatter:function(value,row,index){return row.status == '0'?'禁用':'正常';}                                
				},
				{field:'address',title:'地址',sortable:true, hidden:false,width:60,                             
					formatter:function(value,row,index){return row.address;}                                
				},
				{field:'conditionName',title:'护栏情况',sortable:true, hidden:false,width:100,                             
					formatter:function(value,row,index){return row.conditionName;}          
				},
				{field:'profileName',title:'部位照片',sortable:true, hidden:false,width:100,                             
					formatter:function(value,row,index){return row.profileName;}                                
				},
				{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
					formatter:function(value,row,index){return row.telephone;}                                
				},
				{field:'orgId',title:'所属机构ID',sortable:true, hidden:true,width:100,                             
					formatter:function(value,row,index){return row.orgId;}                                
				},
				{field:'orgName',title:'所属机构',sortable:true, hidden:false,width:150,  
					formatter:function(value,row,index){
						if(row.descOrgName==row.orgName){
							return row.orgName
						}else{
							return row.descOrgName+","+row.orgName;
						}
					}
				},
				{field:'lng',title:'经度',sortable:true, hidden:false,width:100,                             
					formatter:function(value,row,index){return row.lng;}                                
				},
				{field:'lat',title:'纬度',sortable:true, hidden:false,width:100,                             
					formatter:function(value,row,index){return row.lat;}                                
				},
				{field:'remark',title:'备注',sortable:true, hidden:false,width:100,                             
					formatter:function(value,row,index){return row.remark;}                                
				},
				]],
		toolbar:[
				<sec:authorize url="/baseInfo/addBaseInfoPartTrajectionPop">
				{  text:'新增', iconCls:'icon-add',
					handler:function(){addBaseInfoHiddenTrajectionPopWin();}
				},'-',
				</sec:authorize>
				<sec:authorize url="/baseInfo/updateBaseInfoPartTrajectionPopWin">
				{  text:'修改', iconCls:'icon-remove',
					handler:function(){updateBaseInfoHiddenTrajectionPopWin();}
				},'-',
				</sec:authorize>
				<sec:authorize url="/baseInfo/deleteHiddenTrajection">
				{  text:'删除', iconCls:'icon-remove',
					handler:function(){deleteBaseInfoHiddenTrajection();}
				},'-',
				</sec:authorize>
				],
		//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
		onLoadSuccess:function(){$('#baseInfoHiddenTrajectionTable').datagrid('clearSelections');}
			});
	
		});
				
		//新增
		function addBaseInfoHiddenTrajectionPopWin(){
			showWindow({
				title:'增加行人易穿行部位基本信息',
				href:'baseInfo/addBaseInfoPartTrajectionPop',
				width:700,
				height:300,
				onLoad: function(){
					$('#baseInfoHiddenTrajectionAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateBaseInfoHiddenTrajectionPopWin(){
			var rows = $('#baseInfoHiddenTrajectionTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的行人易穿行部位",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个行人易穿行部位进行更新",'info');
				return;
			}
			showWindow({
				title:'更新行人易穿行部位基本信息',
				href:'baseInfo/updateBaseInfoPartTrajectionPopWin?photosName='+rows[0].photosName,
				width:700,
				height:300,
				onLoad: function(){
					$("#baseInfoHiddenTrajectionUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteBaseInfoHiddenTrajection(){
			var rows = $('#baseInfoHiddenTrajectionTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的行人易穿行部位",'info');
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
					$.post('baseInfo/deleteBaseInfoPartTrajection'+ps,function(data){
						$('#baseInfoHiddenTrajectionTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchBaseInfoHiddenTrajection(){
			var params = $('#baseInfoHiddenTrajectionTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoHiddenTrajectionQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoHiddenTrajectionTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoHiddenTrajection(){
			$('#baseInfoHiddenTrajectionQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoHiddenTrajectionQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoHiddenTrajectionQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="10%" align="right">易穿行部位名称：</td>
							<td width="25%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="10%" align="right">易穿行部位状态：</td>
							<td width="25%" align="left">
								<input name="status" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoHiddenTrajection();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdTrajection" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
							</td>
							<td width="10%" align="right">铁路周边：</td>
							<td width="25%" align="left">
								<select id="railId" class="easyui-combobox" name="railId" style="width:90%;"
								 data-options="editable:true,required:false">							
							        <option value="请选择" disabled selected></option>		
							        <c:forEach items="${rails}" var="railInfoTmp">
								    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
							        </c:forEach>
							   	</select>
							</td>
							<td width="30%" align="left" >
								<a onclick="clearBaseInfoHiddenTrajection();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoHiddenTrajectionTable"></table>
			</div>
		</div>
	</div>
</body>
