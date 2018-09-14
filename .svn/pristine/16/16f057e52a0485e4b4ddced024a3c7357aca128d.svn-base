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
				var jqueryObj=$("#orgIdJunction");
		 		makeOrgTree(jqueryObj);
			});
			
			//加载datagride整个表格
			$('#baseInfoPartJunctionTable').datagrid({
				title:'道口基本信息列表', 							//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"baseInfo/baseInfoPartJunctionQueryList",	//数据来源
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
						{field:'name',title:'道口名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'number',title:'道口编号',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.number;}
						},
						{field:'railName',title:'参考铁路线',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railName;}                                
						},
						{field:'middle',title:'中心里程',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.middle;}                                
						},
						{field:'middleStr',title:'中心里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.middleStr;}                                
						},
						{field:'length',title:'长度（单位：米）',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.length;}                                
						},
						{field:'streamName',title:'行别',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.streamName;}                                
						},
						{field:'railBureauName',title:'隶属铁路局(公司)',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.railBureauName;}
						},
						{field:'stateName',title:'运营状态',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.stateName;}
						},
						{field:'roadClassifyName',title:'道路类别',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.roadClassifyName;}
						},
						{field:'width',title:'铺面宽度',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.width;}
						},
						{field:'materialName',title:'铺面材料',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.materialName;}
						},
						{field:'trainNum',title:'昼夜列车数',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.trainNum;}
						},
						{field:'levelName',title:'道口等级',sortable:true, hidden:false,width:100,  
							formatter:function(value,row,index){return row.levelName;}
						},
						{field:'guardStatusName',title:'守护情况',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.guardStatusName;}                                
						},
						{field:'isHeightLimit',title:'是否有无限高',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.isHeightLimit == '0'?'否':'是';}                                
						},
						{field:'profileName',title:'道口照片',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
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
						<sec:authorize url="/baseInfo/addBaseInfoPartJunctionPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoPartJunctionPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoPartJunctionPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoPartJunctionPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoPartJunction">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoPartJunction();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoPartJunctionTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addBaseInfoPartJunctionPopWin(){
			showWindow({
				title:'增加道口基本信息',
				href:'baseInfo/addBaseInfoPartJunctionPopWin',
				width:930,
				height:520,
				onLoad: function(){
					$('#baseInfoPartJunctionAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateBaseInfoPartJunctionPopWin(){
			var rows = $('#baseInfoPartJunctionTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的道口",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条道口进行更新",'info');
				return;
			}
			showWindow({
				title:'更新道口基本信息',
				href:'baseInfo/updateBaseInfoPartJunctionPopWin?photosName='+rows[0].photosName,
				width:1000,
				height:540,
				onLoad: function(){
					$("#baseInfoPartJunctionUpdateForm").form('load', rows[0]);
					$("#baseInfoPartJunctionUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		//删除
		function deleteBaseInfoPartJunction(){
			var rows = $('#baseInfoPartJunctionTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的道口",'info');
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
					$.post('baseInfo/deleteBaseInfoPartJunction'+ps,function(data){
						$('#baseInfoPartJunctionTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		
		
		//编辑铁路分配
		function updateBaseInfoPartJunctionAuthRow(){
			var rows = $('#baseInfoPartJunctionTable').datagrid('getSelections');
			//alert(rows[0].userId);
			//这里有一个jquery easyui datagrid的一个小bug，必须把主键单独列出来，要不然不能多选 
			if(rows.length==0){
				$.messager.alert('提示',"请选择您要编辑权限的道口",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个道口进行编辑",'info');
				return;
			}
			showWindow({
				title:'编辑道口',
				href:'baseInfo/updateAuthJunctionPopWin?baseInfoPartJunctionId='+rows[0].id,
				width:1000,
				height:480,
				onLoad: function(){
					$("#roleAuthForm").form('load', rows[0]);
				}
			});
		}
		
		
		
	
		//表格查询
		function searchBaseInfoPartJunction(){
			var params = $('#baseInfoPartJunctionTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoPartJunctionQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoPartJunctionTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoPartJunction(){
			$('#baseInfoPartJunctionQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoPartJunctionQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoPartJunctionQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="10%" align="right">道口名称：</td>
							<td width="25%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="10%" align="right">所属机构：  </td>
							<td width="25%" align="left">
							<input id="orgIdJunction" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoPartJunction();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">铁路周边： </td>
							<td width="25%" align="left">
								<select id="railId" class="easyui-combobox" name="railId" style="width:90%;"
								 data-options="editable:true,required:false">							
							        <option value="请选择" disabled selected></option>		
							        <c:forEach items="${rails}" var="railInfoTmp">
								    		<option value="${railInfoTmp.id}">${ railInfoTmp.name }</option>
							        </c:forEach>
							   	</select>
							</td>
							<td width="10%" align="right"> </td>
							<td width="25%" align="left">
								
							</td>
							<td width="30%" align="left" >
								<a onclick="clearBaseInfoPartJunction();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoPartJunctionTable"></table>
			</div>
		</div>
	</div>
</body>
