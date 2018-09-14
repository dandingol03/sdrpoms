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
			var jqueryObj=$("#orgIdKeyperson");
	 		makeOrgTree(jqueryObj);
		});
		//加载datagride整个表格
			$('#baseInfoKeypersonTable').datagrid({
				title:'重点人基本信息列表', 					//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 							//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 								//奇偶行颜色不同                        
				collapsible:false,							//可折叠
				url:"baseInfo/baseInfoKeypersonQueryList",	//数据来源
				sortName: 'id',								//排序的列
				sortOrder: 'asc', 							//倒序
				remoteSort: true, 							//服务器端排序
				idField:'id', 								//主键字段 
				queryParams:{}, 								//查询条件
				pagination:true, 							//显示分页
				rownumbers:true, 							//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:2}, //显示复选框 
						{field:'id',title:'ID',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.id;}                                
						},
						{field:'idNumber',title:'身份证号',sortable:true, hidden:false,width:130,                             
							formatter:function(value,row,index){return row.idNumber;}                                
						},
						{field:'name',title:'姓名',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.name;}                                
						},
						{field:'gender',title:'性别',width:20,sortable:false,hidden:false,width:100,
							formatter:function(value,row,index){return row.gender == '1'?'男':'女';}
						},
						{field:'age',title:'年龄',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.age;}                                
						},
						{field:'height',title:'身高(cm)',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.height;}                                
						},
						{field:'weight',title:'体重(kg)',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.weight;}                                
						},
						{field:'job',title:'职业',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.job;}                                
						},
						{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.telephone;}                                
						},
						{field:'presentAddress',title:'现居地址',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.presentAddress;}                                
						},
						{field:'familyNum',title:'家庭人数',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.familyNum;}                                
						},
						{field:'member1',title:'家庭成员1',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.member1;}                                
						},
						{field:'member2',title:'家庭成员2',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.member2;}                                
						},
						{field:'familyAdress',title:'家庭地址',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.familyAdress;}                                
						},
						{field:'familyTelephone',title:'家庭联系电话',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.familyTelephone;}                                
						},
						{field:'isSigned',title:'是否签订安全协议',sortable:true, hidden:true,width:200,                             
							formatter:function(value,row,index){return row.isSigned;}                                
						},
						{field:'isSignedName',title:'是否签订安全协议',sortable:true, hidden:false,width:200,                             
							formatter:function(value,row,index){return row.isSignedName;}                                
						},
						{field:'lng',title:'经度',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lng;}                                
						},
						{field:'lat',title:'纬度',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.lat;}                                
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
						{field:'profileName',title:'重点人照片',sortable:true, hidden:false,width:120,                             
							formatter:function(value,row,index){return row.profileName;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/baseInfo/addBaseInfoKeypersonPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addBaseInfoKeypersonPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/updateBaseInfoKeypersonPopWin">
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateBaseInfoKeypersonPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/baseInfo/deleteBaseInfoKeyperson">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteBaseInfoKeyperson();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#baseInfoKeypersonTable').datagrid('clearSelections');}
			});
	});	
		//新增
		function addBaseInfoKeypersonPopWin(){
			showWindow({
				title:'增加重点人基本信息',
				href:'baseInfo/addBaseInfoKeypersonPopWin',
				width:800,
				height:470,
				onLoad: function(){
					$('#baseInfoKeypersonAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateBaseInfoKeypersonPopWin(){
			var rows = $('#baseInfoKeypersonTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新重点人",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个重点人进行更新",'info');
				return;
			}
			showWindow({
				title:'更新重点人基本信息',
				href:'baseInfo/updateBaseInfoKeypersonPopWin?photosName='+rows[0].photosName,
				width:800,
				height:470,
				onLoad: function(){
					$("#baseInfoKeypersonUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteBaseInfoKeyperson(){
			var rows = $('#baseInfoKeypersonTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的重点人信息",'info');
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
					$.post('baseInfo/deleteBaseInfoKeyperson'+ps,function(data){
						$('#baseInfoKeypersonTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
		
		
		//表格查询
		function searchBaseInfoKeyperson(){
			var params = $('#baseInfoKeypersonTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#baseInfoKeypersonQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#baseInfoKeypersonTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearBaseInfoKeyperson(){
			$('#baseInfoKeypersonQuForm .easyui-combobox').combobox('clear');
			$('#baseInfoKeypersonQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="baseInfoKeypersonQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="10%" align="right">姓名：</td>
							<td width="25%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="10%" align="right">年龄：</td>
							<td width="25%" align="left">
								<input name="age" class="easyui-textbox" style="width:80%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchBaseInfoKeyperson();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right">所属机构： </td>
							<td width="25%" align="left">
								<input id="orgIdKeyperson" name="orgId" class="easyui-textbox" data-options="" style="width:90%;">
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
								<a onclick="clearBaseInfoKeyperson();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="baseInfoKeypersonTable"></table>
			</div>
		</div>
	</div>
</body>
