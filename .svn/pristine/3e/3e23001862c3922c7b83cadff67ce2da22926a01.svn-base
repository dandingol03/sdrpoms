<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
			//加载机构树
			$(document).ready(function() {
			});
			 /* 这里需要解释一下：此处的selectedTeamUser是为了将在baseInfoRailInit.jsp中选中的铁路信息传递过来  */
			 var selectedTeamUser = {};
			//加载datagride整个表格
			$('#partolTeamUserRelationTable').datagrid({
				title:'队伍人员信息', 							//标题
				method:'post',
				iconCls:'icon-tip', 							//图标
				singleSelect:false, 							//多选
				height:366, 									//高度
				fit:true,
				fitColumns: false, 								//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 									//奇偶行颜色不同                        
				collapsible:false,								//可折叠
				url:"",	//数据来源
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
						{field:'orgId',title:'机构',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.orgId;}                                
						},
						{field:'userId',title:'巡防人',sortable:true, hidden:true,width:100,                             
							formatter:function(value,row,index){return row.userId;}                                
						},
						{field:'userName',title:'巡防人',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.userName;}                                
						},
						{field:'teamInfoName',title:'巡防队伍',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.teamInfoName;}                                
						},
						{field:'genderName',title:'性别',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.genderName;}                                
						},
						{field:'userBirthday',title:'出生日期',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.userBirthday;}                                
						},
						{field:'userDuty',title:'职务',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.userDuty;}                                
						},
						{field:'educationName',title:'文化程度',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.educationName;}                                
						},
						{field:'registrationName',title:'户口性质',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.registrationName;}                                
						},
						{field:'domicile',title:'户籍地',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.domicile;}                                
						},
						{field:'residence',title:'居住地',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.residence;}                                
						},
						{field:'userTelephone',title:'联系电话',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.userTelephone;}                                
						},
						{field:'idNumber',title:'身份证号',sortable:true, hidden:false,width:150,                             
							formatter:function(value,row,index){return row.idNumber;}                                
						},
						{field:'managementUnit',title:'管理单位',sortable:true, hidden:false,width:100,                             
							formatter:function(value,row,index){return row.managementUnit;}                                
						},
						{field:'remark',title:'备注',sortable:true, hidden:false,width:300,                             
							formatter:function(value,row,index){return row.remark;}                                
						},
						]],
				toolbar:[
						<sec:authorize url="/patrol/addPatrolTeamUserRelationPopWin">
						{  text:'新增', iconCls:'icon-add',
							handler:function(){addPartolTeamUserRelationPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/updatePatrolTeamUserRelationPopWin">
						{  text:'修改', iconCls:'icon-edit',
							handler:function(){updatePartolTeamUserRelationPopWin();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/deletePatrolTeamUserRelation">
						{  text:'删除', iconCls:'icon-remove',
							handler:function(){deletePartolTeamUserRelation();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/patroltrackInfoInit">
						{  text:'查看巡线轨迹', iconCls:'icon-tip',
							handler:function(){showPatrolTrackInfo();}
						},'-',
						</sec:authorize>
						{text:'导出', iconCls:'icon-excel',
							handler:function(){exportSafeExcel();}
						},'-',
						<sec:authorize url="/baseInfo/responsibilityLineQueryList">
						{text:'责任区段', iconCls:'icon-add',
							handler:function(){responsibilityLine();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/patrol/updatePatrolUserBlongTeamPopWin">
						{text:'更换队伍', iconCls:'icon-edit',
							handler:function(){updatePatrolUserBlongTeamPopWin();}
						},'-',
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#partolTeamUserRelationTable').datagrid('clearSelections');}
			});
			
		});
	
		//导出
		function exportSafeExcel(){
			var fileName="巡防队伍人员";
			var params={};
			var fields =$('#partolTeamUserRelationTankQuForm').serializeArray();
			$.each(fields, function(i, field){
				params[field.name] = field.value;
			});
			params["fileName"]=fileName;
			params["teamInfoId"]=selectedTeamUser.id;
			fileName = encodeURI(fileName);
			fileName = encodeURI(fileName);
			exportExcelFile('${ctx}/news/exportSafeCheckExcel',params,fileName);
		}
		//导出人员信息
		function exportExcelFile(url,params,fileName){
				$.ajax({
		    		url:url,
		    		type:"POST",
		    		dataType:'json',
		    		data:params,
		    		scriptCharset:'utf-8',
		    		cache:false,
		    		error: function(XMLHttpRequest, textStatus, errorThrown) {
		    			console.log("响应状态:["+XMLHttpRequest.status+"]-");
		    			console.log("完成状态:["+XMLHttpRequest.readyState+"]-");
		    			console.log("异常情况:["+textStatus+"]");
		    			closeWaitingWindow();
		    		},
		    		beforeSend:function(){
		    			showWaitingWindow();
		    		},
		    		success:function(data){
		    			closeWaitingWindow();
		    			if(data.fileId == null || data.fileId == "-1"){
							$.messager.alert('提示','导出'+fileName+'excel失败','info');
						}else{
							//注意中文编码 
							window.location.href="${ctx}/excelfile/excelFileDownload?fileName="+fileName+"&fileId=" + data.fileId;
						}
		    		}
		    	});
			}
		// 展示巡防队伍关系
		function showPatrolTrackInfo() {
			var rows = $('#partolTeamUserRelationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要查看的人员",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个人员进行查看",'info');
				return;
			}
			showSecondWindow({
				title:'巡线轨迹列表',
				href:'patrol/patroltrackInfoInit',
				width:800,
				height:400
				,onLoad: function(){
				    var opts = $("#patrolTrackInfoTable").datagrid("options");
				    console.log("optsopts",$("#patrolTrackInfoTable"));
				    opts.url = "baseInfo/PatrolTrackInfoQueryList?userId=" + rows[0].userId;
				    $("#patrolTrackInfoTable").datagrid("load");
				    selectedUser = rows[0];
				}
			});
		}
		//责任区段  名字
		function responsibilityLine(){
				var rows = $('#partolTeamUserRelationTable').datagrid('getSelections');
				if(rows.length==0){
					$.messager.alert('提示',"请选择你要查看的人员",'info');
					return;
				}
				if(rows.length > 1){
					$.messager.alert('提示',"只能选择一个人员进行查看",'info');
					return;
				}
				showSecondWindow({
					title:'责任区段列表',
					href:'baseInfo/responsibilityLineInit',
					width:800,
					height:400
					,onLoad: function(){
						var opts = $("#responsibilityLineTable").datagrid("options");
					    opts.url = "baseInfo/responsibilityLineQueryList?userId=" + rows[0].userId;
					    $("#responsibilityLineTable").datagrid("load");
					    selectedUser = rows[0]; 
					   
					}
				});
		}
		//新增
		function addPartolTeamUserRelationPopWin(){
			showSecondWindow({
				title:'增加队伍人员',
				href:'patrol/addPatrolTeamUserRelationPopWin',
				width:600,
				height:420,
				onLoad: function(){
					var f = $('#partolTeamUserRelationAddForm');
					f.form('reset');
					f.form('load', {
						teamInfoId: selectedTeamUser.id,
						userOrg: selectedTeamUser.orgId
					});
				}
			});
		}
				
		//更新    
		function updatePartolTeamUserRelationPopWin(){
			var rows = $('#partolTeamUserRelationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的队伍人员",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个队伍人员进行更新",'info');
				return;
			}
			showSecondWindow({
				title:'更新队伍人员基本信息',
				href:'patrol/updatePatrolTeamUserRelationPopWin',
				width:600,
				height:420,
				onLoad: function(){
					var f = $('#partolTeamUserRelationUpdateForm');
					f.form('load', rows[0]);
				}
			});
		}
		
		//更换的队伍
		function updatePatrolUserBlongTeamPopWin(){
			var rows = $('#partolTeamUserRelationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更换队伍的人员",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个队伍人员进行修改",'info');
				return;
			}
			showSecondWindow({
				title:'更新队伍信息',
				href:'patrol/updatePatrolUserBlongTeamPopWin',
				width:700,
				height:160,
				onLoad: function(){
					var f = $('#partolUserBlongTeamUpdateForm');
					f.form('load', rows[0]);
					f.form('load', {
						teamInfoName: selectedTeamUser.name, 
						teamInfoId: selectedTeamUser.id
					});
				}
			});
		}
				
		//删除
		function deletePartolTeamUserRelation(){
			var rows = $('#partolTeamUserRelationTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的队伍人员",'info');
				return;
			}
			if(rows.length>1){
				$.messager.alert('提示',"请逐条删除队伍人员",'info');
				return;
			}
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var ps = "";
					var ups = "";
					$.each(rows,function(i,n){
						if(i==0){
							ps += "?idList="+n.id;
							ups += " &userList="+n.userId;
						} else {
							ps += "&idList="+n.id;
							ups += " &userList="+n.userId;
						}	
					});
					$.post('patrol/deletePatrolTeamUserRelation'+ps+ups,function(data){
						$('#partolTeamUserRelationTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
	
		//表格查询
		function searchPartolTeamUserRelation(){
			var params = $('#partolTeamUserRelationTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#partolTeamUserRelationTankQuForm').serializeArray();
			$.each( fields, function(i, field){
				params[field.name] = field.value;
			});
			$('#partolTeamUserRelationTable').datagrid('reload');
		}
				
		//清空查询条件
		function clearPartolTeamUserRelation(){
			$('#partolTeamUserRelationTankQuForm .easyui-combobox').combobox('clear');
			$('#partolTeamUserRelationTankQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="partolTeamUserRelationTankQuForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="5%" align="right">姓名：</td>
							<td width="10%" align="left">
								<input name="userName" class="easyui-textbox" style="width:90%;">
							</td>
							<td width="30%" align="left" >
								<a onclick="searchPartolTeamUserRelation();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
								&nbsp;&nbsp;&nbsp;&nbsp;
								<a onclick="clearPartolTeamUserRelation();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="partolTeamUserRelationTable"></table>
			</div>
		</div>
	</div>
</body>
