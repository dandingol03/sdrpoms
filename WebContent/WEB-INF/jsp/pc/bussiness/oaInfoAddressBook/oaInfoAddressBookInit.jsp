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
				var jqueryObj=$("#addressBookorgs");
		 		makeOrgTree(jqueryObj);
				
			});
			
			//加载datagride整个表格
			$('#oaInfoAddressBookTable').datagrid({
				title:'通讯录基本信息', 						    //标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"oaInfo/addressBookQueryList",	 		    //数据来源
				sortName: 'userId',								//排序的列
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
							{field:'name',title:'姓名',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.name;}                                
							},
							{field:'duty',title:'职务',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.duty;}                                
							},
							{field:'unitName',title:'单位名称',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.unitName;}                                
							},
							{field:'telephone',title:'联系电话',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.telephone;}                                
							},
							{field:'email',title:'邮箱',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.email;}                                
							},
							{field:'qq',title:'QQ或者微信',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.qq;}                                
							},
							{field:'adress',title:'地址',sortable:true, hidden:false,width:250,                             
								formatter:function(value,row,index){return row.adress;}                                
							},
							{field:'postalcode',title:'邮编',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.postalcode;}                                
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
							{field:'orgId',title:'机构ID',width:50,sortable:false,hidden:true, //机构描述id隐藏
								formatter:function(value,row,index){return row.orgId;}
							},
							{field:'remark',title:'备注',sortable:true, hidden:false,width:150,                             
								formatter:function(value,row,index){return row.remark;}                                
							}
						]],
				toolbar:[
						<sec:authorize url="/oaInfo/addAddressBookPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addOaInfoAddressBookPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/oaInfo/updateAddressBookPopWin">
			                            
						{  text:'修改', iconCls:'icon-remove',
							handler:function(){updateOaInfoAddressBookPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/oaInfo/deleteAddressBook">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deleteOaInfoAddressBook();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#oaInfoAddressBookTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addOaInfoAddressBookPopWin(){
			showWindow({
				title:'增加通讯录基本信息',
				href:'oaInfo/addAddressBookPopWin',
				width:600,
				height:365,
				onLoad: function(){
					$('#addAddressBookAddForm').form('reset');
				}
			});
		}	
		//更新    
		function updateOaInfoAddressBookPopWin(){
			var rows = $('#oaInfoAddressBookTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的通讯录",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条通讯录进行更新",'info');
				return;
			}
			showWindow({
				title:'更新通讯录基本信息',
				href:'oaInfo/updateAddressBookPopWin?userId='+rows[0].userId,
				width:600,
				height:350,
				onLoad: function(){
					console.log(rows[0],11)
					$("#oaInfoAddressBookUpdateForm").form('load', rows[0]);
					//userName
					//$('#userIdUpdate').combobox('setValue', rows[0].name);
				}
			});
		}
				
		//删除
		function deleteOaInfoAddressBook(){
			var rows = $('#oaInfoAddressBookTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的通讯录",'info');
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
					$.post('oaInfo/deleteAddressBook'+ps,function(data){
						$('#oaInfoAddressBookTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchOaInfoAddressBook(){
			var params = $('#oaInfoAddressBookTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#oaInfoAddressBookQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#oaInfoAddressBookTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearOaInfoAddressBook(){
			$('#oaInfoAddressBookQuForm .easyui-combobox').combobox('clear');
			$('#oaInfoAddressBookQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="oaInfoAddressBookQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">姓名：</td>
							<td width="10%" align="left">
								<input name="name" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="5%" align="right">所属机构：</td>
							<td width="10%" align="left">
								<input id="addressBookorgs" name="userOrg" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchOaInfoAddressBook();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearOaInfoAddressBook();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="oaInfoAddressBookTable"></table>
			</div>
		</div>
	</div>
</body>
