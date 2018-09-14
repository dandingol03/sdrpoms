<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});    // ajax不缓存
		jQuery(function($){
			$(document).ready(function() {
				//页面准备完毕
				var jqueryObj=$("#orgIdPartCulvert");
		 		makeOrgTree(jqueryObj);
			});
			
			//加载datagride整个表格
			$('#baseInfoPartCulvertTable').datagrid({
				title:'涵洞基本信息列表', 						// 标题
				method:'post',
				iconCls:'icon-tip', 							// 图标
				singleSelect:false, 							// 多选
				height:366, 									// 高度
				fit:true,
				fitColumns: false, 								// 自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									// 奇偶行颜色不同                        
				collapsible:false,								// 可折叠
				url:"baseInfo/baseInfoPartCulvertQueryList",            // 数据来源
				sortName: 'id',									// 排序的列
				sortOrder: 'asc', 								// 倒序
				remoteSort: true, 								// 服务器端排序
				idField:'id', 									// 主键字段 
				queryParams:{}, 								// 查询条件
				pagination:true, 								// 显示分页
				rownumbers:true, 								// 显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2},     // 显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'涵洞名称',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'number',title:'涵洞编号',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.number;}                                
						},
						{field:'classifyName',title:'涵洞分类',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.classifyName;}                                
						},
						{field:'culvertFunction',title:'涵洞功能',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.culvertFunction;}                                
						},
						{field:'railName',title:'参考铁路线',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.railName;}                                
						},
						{field:'middle',title:'中心里程',sortable:true, hidden:true,width:100,      // 隐藏项                       
							formatter:function(value,row,index){return row.middle;}                                
						},
						{field:'middleStr',title:'中心里程',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.middleStr;}                                
						},
						{field:'length',title:'长度（单位：米）',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.length;}                                
						},
						{field:'width',title:'宽度（单位：米）',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.width;}                                
						},
						{field:'height',title:'高度（单位：米）',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.height;}                                
						},
						{field:'inradium',title:'内径（单位：米）',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.inradium;}                                
						},
						
						{field:'isSeeper',title:'是否积水',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.isSeeper == '0'?'否':'是';}                                
						},
						{field:'streamName',title:'行别',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.streamName;}                                
						},
						{field:'guardStatusName',title:'守护情况',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.guardStatusName;}                                
						},
						{field:'profileName',title:'涵洞照片',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
						},
						{field:'orgId',title:'所属机构 ',sortable:true, hidden:true,width:100,        //隐藏列表（机构id）                     
							formatter:function(value,row,index){return row.orgId;}                                
						},
						{field:'crossSpanName',title:'穿跨形式',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.crossSpanName;}                                
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
						{field:'lng',title:'经度 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度 ',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lat;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoPartCulvertPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoPartCulvertPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoPartCulvertPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoPartCulvertPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoPartCulvert">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoPartCulvert();}
						},'-',
						</sec:authorize>
						],
				// 一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoPartCulvertTable').datagrid('clearSelections');}
			});
			
		});
				
		// 新增
		function addBaseInfoPartCulvertPopWin(){
			showWindow({
				title:'增加涵洞基本信息',
				href:'baseInfo/addBaseInfoPartCulvertPopWin',
				width:800,
				height:480,
				onLoad: function(){
					$('#baseInfoPartCulvertAddForm').form('reset');
				}
			});
		}
				
		// 更新    
		function updateBaseInfoPartCulvertPopWin(){
			var rows = $('#baseInfoPartCulvertTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的涵洞",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条涵洞进行更新",'info');
				return;
			}
			showWindow({
				title:'更新涵洞基本信息',
				href:'baseInfo/updateBaseInfoPartCulvertPopWin?photosName='+rows[0].photosName,
				width:800,
				height:480,
				onLoad: function(){
					$("#baseInfoPartCulvertUpdateForm").form('load',rows[0]);
					$("#baseInfoPartCulvertUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		// 删除
		function deleteBaseInfoPartCulvert(){
			var rows = $('#baseInfoPartCulvertTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的涵洞",'info');
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
					$.post('baseInfo/deleteBaseInfoPartCulvert'+ps,function(data){
						$('#baseInfoPartCulvertTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		// 表格查询
		function searchBaseInfoPartCulvert(){
			var params = $('#baseInfoPartCulvertTable').datagrid('options').queryParams;
			// 先取得 datagrid 的查询参数
			var fields =$('#baseInfoPartCulvertQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoPartCulvertTable').datagrid('reload');
		}
				
		// 清空查询条件
		function clearBaseInfoPartCulvert(){
			$('#baseInfoPartCulvertQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoPartCulvertQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoPartCulvertQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="10%" align="right">涵洞名称：</td>
							<td width="25%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="10%" align="right">涵洞分类：</td>
							<td width="25%" align="left">
								<select class="easyui-combobox" name="classify" style="width:90%;" data-options="editable:false">							
								    <option value="">请选择</option>
								    <c:forEach items="${classifyList}" var="classifyListTemp">
										<option value="${classifyListTemp.dictData}">${classifyListTemp.dictName}</option>
								    </c:forEach>
								</select>
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoPartCulvert();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdPartCulvert" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
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
								<a onclick="clearBaseInfoPartCulvert();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoPartCulvertTable"></table>
			</div>
		</div>
	</div>
</body>
