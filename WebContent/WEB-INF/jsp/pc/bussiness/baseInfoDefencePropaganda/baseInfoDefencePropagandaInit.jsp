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
				var jqueryObj=$("#orgIdPropaganda");
		 		makeOrgTree(jqueryObj);
				
			});
			
			//加载datagride整个表格
			$('#baseInfoDefencePropagandaTable').datagrid({
				title:'护路宣传点位基本信息表', 							//标题
				method:'post',
				iconCls:'icon-tip', 										//图标
				singleSelect:false, 										//多选
				height:366, 												//高度
				fit:true,
				fitColumns: false, 										//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 											//奇偶行颜色不同                        
				collapsible:false,										//可折叠
				url:"baseInfo/baseInfoDefencePropagandaQueryList",	//数据来源
				sortName: 'id',											//排序的列
				sortOrder: 'asc', 										//倒序
				remoteSort: true, 										//服务器端排序
				idField:'id', 											//主键字段 
				queryParams:{}, 											//查询条件
				pagination:true, 										//显示分页
				rownumbers:true, 										//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'name',title:'护路宣传点名称',sortable:true, hidden:false,width:130,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'number',title:'护路宣传点编号',sortable:true, hidden:false,width:130,                             
							formatter:function(value,row,index){return row.number;}                                
						},
						{field:'adress',title:'地址',sortable:true, hidden:false,width:250,                             
							formatter:function(value,row,index){return row.adress;}                                
						},
						{field:'constructionUnit',title:'建管单位',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.constructionUnit;}                                
						},
						{field:'proType',title:'布建形式',sortable:true, hidden:true,width:150,                             
							formatter:function(value,row,index){return row.proType;}                                
						},
						{field:'proTypeName',title:'布建形式',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.proTypeName;}                                
						},
						{field:'proTime',title:'布建时间',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.proTime;}                                
						},
						{field:'lng',title:'经度',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lat;}                                
						},
						{field:'profileName1',title:'图片',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.profileName1;}                                
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
						{field:'content',title:'内容',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.content;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoDefencePropagandaPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoDefencePropagandaPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoDefencePropagandaPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoDefencePropagandaPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoDefencePropaganda">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoDefencePropaganda();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoDefencePropagandaTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addBaseInfoDefencePropagandaPopWin(){
			showWindow({
				title:'增加护路宣传点位基本信息',
				href:'baseInfo/addBaseInfoDefencePropagandaPopWin',
				width:800,
				height:400,
				onLoad: function(){
					$('#baseInfoDefencePropagandaAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateBaseInfoDefencePropagandaPopWin(){
			var rows = $('#baseInfoDefencePropagandaTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的护路宣传点位",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条护路宣传点位进行更新",'info');
				return;
			}
			showWindow({
				title:'更新护路宣传点位基本信息',
				href:'baseInfo/updateBaseInfoDefencePropagandaPopWin?photosName='+rows[0].photosName,
				width:800,
				height:400,
				onLoad: function(){
					$("#baseInfoDefencePropagandaUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteBaseInfoDefencePropaganda(){
			var rows = $('#baseInfoDefencePropagandaTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的护路宣传点位",'info');
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
					$.post('baseInfo/deleteBaseInfoDefencePropaganda'+ps,function(data){
						$('#baseInfoDefencePropagandaTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		
		function fileUploadPropagandaPopWin(){
			showWindow({
				title:'增加护路宣传点位图片',
				href:'baseInfo/fileUploadPropagandaPopWin',
				width:600,
				height:470,
			});
		}
		
		//表格查询
		function searchBaseInfoDefencePropaganda(){
			var params = $('#baseInfoDefencePropagandaTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoDefencePropagandaQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoDefencePropagandaTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoDefencePropaganda(){
			$('#baseInfoDefencePropagandaQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoDefencePropagandaQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoDefencePropagandaQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td  width="2%" align="right">名称：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td  width="2%" align="right">地址：</td>
							<td width="10%" align="left">
								<input name="adress" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoDefencePropaganda();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdPropaganda" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
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
								<a onclick="clearBaseInfoDefencePropaganda();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoDefencePropagandaTable"></table>
			</div>
		</div>
	</div>
</body>