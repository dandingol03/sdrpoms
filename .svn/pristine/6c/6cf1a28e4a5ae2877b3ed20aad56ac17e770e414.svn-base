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
				var jqueryObj=$("#orgIdStation");
		 		makeOrgTree(jqueryObj);
			});
			
			//加载datagride整个表格(datagride以数据表格的形式进行展示)
			$('#baseInfoPartStationTable').datagrid({
				title:'车站基本信息列表', 						//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"baseInfo/baseInfoPartStationQueryList",	//数据来源
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
						{field:'name',title:'车站名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'number',title:'车站编号',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.number;}                                
						},
						{field:'levelName',title:'车站等级',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.levelName;}                                
						},
						{field:'natureName',title:'车站性质',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.natureName;}                                
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
						{field:'isHighspeed',title:'是否高铁站',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.isHighspeed == '0'?'否':'是';}                                
						},
						{field:'stateName',title:'运营状态',sortable:true, hidden:false,width:114,                             
							formatter:function(value,row,index){return row.stateName;}                                
						},
						{field:'railBureauName',title:'隶属铁路局',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railBureauName;}                                
						},
						{field:'profileName',title:'车站图片',sortable:true, hidden:false,width:100,                             
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
						]],//添加显示行
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoPartStationPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoPartStationPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoPartStationPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoPartStationPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoPartStation">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoPartStation();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/baseInfoPartStationYardRelInit">
						{  text:'站场关联', iconCls:'icon-tip',
							handler:function(){showBaseInfoPartStationYardRel();}
						},'-',
						</sec:authorize>
						],//添加按钮
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoPartStationTable').datagrid('clearSelections');}
			});
			
		});
		//站场关联
		function showBaseInfoPartStationYardRel(){
			var rows = $('#baseInfoPartStationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的车站",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个车站进行查看",'info');
				return;
			}
			showWindow({
				title:'站场信息',
				href:'baseInfo/baseInfoPartStationYardRelInit',
				width:1000,
				height:600
				,onLoad: function(){
				    var opts = $("#baseInfoStationYardTable").datagrid("options");
				    opts.url = "baseInfo/baseInfoPartStationYardRelQueryList?stationId=" + rows[0].id;
				    $("#baseInfoStationYardTable").datagrid("load");
				    selectedStation = rows[0];
				}
			});
		}
		//新增(弹出窗口)
		function addBaseInfoPartStationPopWin(){
			showWindow({
				title:'增加车站基本信息',
				href:'baseInfo/addBaseInfoPartStationPopWin',
				width:1000,
				height:450,
				onLoad: function(){
					$('#baseInfoPartStationAddForm').form('reset');
				}
			});
		}
				
		//更新(弹出窗口)    
		function updateBaseInfoPartStationPopWin(){
			var rows = $('#baseInfoPartStationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的车站",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条车站进行更新",'info');
				return;
			}
			showWindow({
				title:'更新车站基本信息',
				href:'baseInfo/updateBaseInfoPartStationPopWin?photosName='+rows[0].photosName,
				width:1000,
				height:450,
				onLoad: function(){
					$("#baseInfoPartStationUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除(弹出窗口)
		function deleteBaseInfoPartStation(){
			var rows = $('#baseInfoPartStationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的车站",'info');
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
					$.post('baseInfo/deleteBaseInfoPartStation'+ps,function(data){
						$('#baseInfoPartStationTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		
	
		//表格查询(弹出窗口)
		function searchBaseInfoPartStation(){
			var params = $('#baseInfoPartStationTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoPartStationQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoPartStationTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoPartStation(){
			$('#baseInfoPartStationQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoPartStationQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoPartStationQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="10%" align="right">车站名称：</td>
							<td width="25%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="10%" align="right">车站等级：</td>
							<td width="25%" align="left">
								<select class="easyui-combobox"
									name="level" style="width: 90%;" data-options="editable:false">
										<option value="">请选择</option>
										<c:forEach items="${levelList}" var="levelListTemp">
											<option value="${levelListTemp.dictData}">${levelListTemp.dictName}</option>
										</c:forEach>
								</select>
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoPartStation();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdStation" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
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
								<a onclick="clearBaseInfoPartStation();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoPartStationTable"></table>
			</div>
		</div>
	</div>
</body>
