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
				var jqueryObj=$("#orgId");
		 		makeOrgTree(jqueryObj);
			});
			//加载datagride
			$('#videoMeetingInfoTable').datagrid({
				title:'会议设备管理', 				//标题
				method:'post',
				fit:true,
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				fitColumns: false, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:true,				//可折叠
				url:"videoMeeting/videoMeetingQueryList", 			//数据来源
				sortName: 'id',				//排序的列
				sortOrder: 'desc', 				//倒序
				remoteSort: true, 				//服务器端排序
				idField:'id', 				//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
						{field:'ck',checkbox:true,width:5}, //显示复选框
						{field:'id',title:'id',width:100,sortable:true,hidden:true,                              
							formatter:function(value,row,index){return row.id;} //                    
						},
						{field:'orgId',title:'所属机构',width:200,sortable:true,hidden:true,                              
							formatter:function(value,row,index){return row.orgId;} //                    
						},
						{field:'orgName',title:'所属机构',width:200,sortable:true,                              
							formatter:function(value,row,index){return row.orgName;}            
						},
						{field:'videoIp',title:'摄像头IP地址',width:150,sortable:true,                              
							formatter:function(value,row,index){
								return row.videoIp;
							} //                    
						},
						{field:'videoUser',title:'摄像头用户名',width:100,sortable:true,                              
							formatter:function(value,row,index){
								return row.videoUser;
							} //                    
						},
						{field:'videoPass',title:'摄像头密码',width:100,sortable:false,                              
							formatter:function(value,row,index){
								return row.videoPass;
							} //                    
						},
						{field:'videoPort',title:'摄像头端口号',width:100,sortable:false,
							formatter:function(value,row,index){return row.videoPort;}
						},
						{field:'videoChannel',title:'摄像头通道号',width:100,sortable:false,
							formatter:function(value,row,index){return row.videoChannel;}
						},
						{field:'videoStream',title:'码流类型',width:100,sortable:false,
							formatter:function(value,row,index){
								
								if(row.videoStream=="0"){
									return "主码流";
								}else if(row.videoStream=="1"){
									return "辅码流1";
								}else if(row.videoStream=="2"){
									return "辅码流2";
								}else if(row.videoStream=="3"){
									return "辅码流3";
								}

							}
						},		
						{field:'decodeIp',title:'解码器IP地址',width:100,sortable:false, //
							formatter:function(value,row,index){return row.decodeIp;}
						},
						{field:'decodeUser',title:'解码器用户名',width:100,sortable:false,
							formatter:function(value,row,index){return row.decodeUser;}
						},
						{field:'decodePass',title:'解码器密码',width:100,sortable:false, //
							formatter:function(value,row,index){return row.decodePass;}
						},
						{field:'decodePort',title:'解码器端口号',width:100,sortable:false,
							formatter:function(value,row,index){return row.decodePort;}
						},
						{field:'decodeChannel',title:'解码器通道号',width:100,sortable:false, //
							formatter:function(value,row,index){return row.decodeChannel;}
						},
						{field:'ismain',title:'是否主会场',width:100,sortable:false,hidden:true, 
							formatter:function(value,row,index){
								if(row.ismain=="1"){
									return "是";
								}else{
									return "否";
								}
								return row.ismain;
							}
						},
						{field:'remarks',title:'备注',width:300,sortable:false,
							formatter:function(value,row,index){return row.remarks;}
						}
						]],
				toolbar:[
						<sec:authorize url="/videoMeeting/videoMeetingAdd">
						{text:'新增', iconCls:'icon-add',
							handler:function(){addVideoMeetingInfoRow();}
						},'-',
						</sec:authorize>
						<sec:authorize url="/videoMeeting/videoMeetingUpdate">
						{text:'更新', iconCls:'icon-edit',
							handler:function(){
								updateVideoMeetingInfoRow();
							}
						},'-',
						</sec:authorize>
						<sec:authorize url="/videoMeeting/videoMeetingDelete">
						{text:'删除', iconCls:'icon-remove',
							handler:function(){
								deleteVideoMeetingInfoRow();
							}
						},'-'
						</sec:authorize>
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#videoMeetingInfoTable').datagrid('clearSelections');}
			});
			
		});
				
		//新增
		function addVideoMeetingInfoRow(){
			showWindow({
				title:'增加会议设备信息',
				href:'videoMeeting/videoMeetingAddPopWin',
				width:710,
				height:460,
				onLoad: function(){
					$('#videoMeetingInfoAddForm').form('reset');
				}
			});
		}
		//更新    
		function updateVideoMeetingInfoRow(){
			var rows = $('#videoMeetingInfoTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的信息",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一条信息进行更新",'info');
				return;
			}
			showWindow({
				title:'更新会议设备信息',
				href:'videoMeeting/videoMeetingUpdatePopWin',
				width:710,
				height:460,
				onLoad: function(){
					$("#videoMeetingInfoUpdateForm").form('load', rows[0]);
					$("#videoMeetingInfoUpdateForm").form('load', {orgId:[{"id":rows[0].orgId,"text":rows[0].orgName}]});
				}
			});
		}
				
		//删除
		function deleteVideoMeetingInfoRow(){
			var rows = $('#videoMeetingInfoTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要删除的信息",'info');
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
					$.post('videoMeeting/delVideoMeetingInfo'+ps,function(data){
						$('#videoMeetingInfoTable').datagrid('reload');
						$.messager.alert('提示',data.mes,'info');
					});
				}
			});
		}
				
		//表格查询
		function searchVideoMeetingInfo(){
			var params = $('#videoMeetingInfoTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#VideoMeetingInfoForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#videoMeetingInfoTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}

		//清空查询条件
		function clearVideoMeetingInfoForm(){
			$('#VideoMeetingInfoForm').form('clear');
		}
	</script>	
	
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="VideoMeetingInfoForm" class="fw fh">
					<table  class="fw fh">
						<tr>
							<td width="7%" align="right">所属机构：</td>
							<td width="10%" align="left">
								<input id="orgId" name="orgId" class="easyui-textbox" data-options="" style="height:20px;font-size:12px;width:90%;">
							</td>
							<td width="10%" align="center" >
								<a onclick="searchVideoMeetingInfo();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							<td width="40%" align="left">
								<a onclick="clearVideoMeetingInfoForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:90%;">
			<div class="fh pl5 pr10">
				<table id="videoMeetingInfoTable"></table>
			</div>
		</div>
	</div>	
</body>
</html>