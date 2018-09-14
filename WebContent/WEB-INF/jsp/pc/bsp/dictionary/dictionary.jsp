<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<body>
	<script type="text/javascript">
		jQuery.ajaxSetup({cache:false});//ajax不缓存
		jQuery(function($){
		    $(document).ready(function(){
			    var jqueryObj=$("#dictId");
			    makeDictTree(jqueryObj);
			});
			//加载datagride
			$('#dataDictionaryTable').datagrid({
				title:'数据字典列表', 			//标题
				method:'post',
				iconCls:'icon-tip', 			//图标
				singleSelect:false, 			//多选
				height:366, 					//高度
				fit:true,
				fitColumns: true, 				//自动调整各列，用了这个属性，下面各列的宽度值就只是一个比例。
				striped: true, 					//奇偶行颜色不同                        
				collapsible:false,				//可折叠
				url:"datadictionary/queryList", //数据来源
				sortName: 'dictData',			//排序的列
				sortOrder: 'asc', 				//顺序
				remoteSort: true, 				//服务器端排序
				idField:'id', 					//主键字段 
				queryParams:{}, 				//查询条件
				pagination:true, 				//显示分页
				rownumbers:true, 				//显示行号
				columns:[[
							{field:'ck',checkbox:true,width:2}, //显示复选框 
							{field:'dictCode',title:'字典编码',width:20,sortable:false,
								formatter:function(value,row,index){return row.dictCode;}                          
							},
							{field:'dictName',title:'字典名称',width:20,sortable:false,
								formatter:function(value,row,index){return row.dictName;}                                
							},
							{field:'dictData',title:'字典值',width:30,sortable:false,
								formatter:function(value,row,index){return row.dictData;}
							},
							{field:'remark',title:'备注',width:30,sortable:false,
								formatter:function(value,row,index){return row.remark;}
							},
						]],
				toolbar:[
							{text:'新增', iconCls:'icon-add', 
								handler:function(){addDictRow();}
							},'-',
							{text:'更新', iconCls:'icon-edit', 
								handler:function(){updateDictRow();}
							},'-',
							{text:'删除', iconCls:'icon-remove',
								handler:function(){deleteDictRow();}
							},'-',
							{text:'查看数据字典', iconCls:'icon-search',
								handler:function(){showDictTree();}
							},'-'
						],
				//一定要加上这一句，要不然datagrid会记住之前的选择状态，删除时会出问题
				onLoadSuccess:function(){$('#dataDictionaryTable').datagrid('clearSelections');}
			});
			
		});
		
		//新增
		function addDictRow(){
			showWindow({
				title:'增加数据字典',
				href:'datadictionary/addPopWin',
				width:350,
				height:350,
				onLoad: function(){
					$('#dictAddForm').form('reset');
				}
			});
		}
				
		//更新    
		function updateDictRow(){
			var rows = $('#dataDictionaryTable').datagrid('getSelections');
			if(rows.length==0){
				$.messager.alert('提示',"请选择你要更新的字典",'info');
				return;
			}
			if(rows.length > 1){
				$.messager.alert('提示',"只能选择一个字典进行更新",'info');
				return;
			}
			showWindow({
				title:'更新数据字典',
				href:"datadictionary/updatePopWin?",
				width:350,
				height:350,
				onLoad: function(){
					$("#dictUpdateForm").form('load', rows[0]);
				}
			});
		}
				
		//删除
		function deleteDictRow(){
			$.messager.confirm('提示','确定要删除吗?',function(result){
				if (result){
					var rows = $('#dataDictionaryTable').datagrid('getSelections');
					var ps = "";
					var isRoot = 0;
					$.each(rows,function(i,n){
						if(n.pid == '0'){
							$.messager.alert('提示', '数据字典根节点不能删除', 'info');
							isRoot = 1;
						}else{
							if(i==0){
								ps += "?dictId="+n.id;
							} else {
								ps += "&dictId="+n.id;
							}
						}
					});
					if(isRoot == 0){
						$.post('datadictionary/delDictionary'+ps,function(data){
							$('#dataDictionaryTable').datagrid('reload');
							$.messager.alert('提示',data.mes,'info');
						});
					}
				}
			});
		}
	
		//查看字典树
		function showDictTree(){
			showWindow({
				title:'查看数据字典树',
				href:'datadictionary/treePopWin',
				width:350,
				height:480,
				onLoad: function(){}
			});
		}
				
		//表格查询
		function searchDict(){
			var params = $('#dataDictionaryTable').datagrid('options').queryParams;
			//先取得 datagrid 的查询参数
			var fields =$('#dictQuForm').serializeArray(); //自动序列化表单元素为JSON对象
			$.each( fields, function(i, field){
				//alert("["+field.name+":"+field.value+"]");
				params[field.name] = field.value; //设置查询参数
			});
			$('#dataDictionaryTable').datagrid('reload'); //设置好查询参数 reload 一下就可以了
		}
				
		//清空查询条件
		function clearDictForm(){
			$('#dictQuForm').form('clear');
		}
		
	</script>
	<div class="fw fh">
		<div class="fw" style="height:10%;">
			<div class="fh pl10 pr10">
				<form id="dictQuForm" class="fw fh">
					<table class="fw fh">
						<tr>
							<td width="8%" align="right">上级字典编码：</td>
							<td width="37%" align="left">
								<input id="dictId" type="text" name="pid" class="easyui-textbox" 
								style="width:90%;">
							</td>
							<td width="8%" align="right">字典名称：</td>
							<td width="17%" align="left"><input  name="dictName" type="text" class="easyui-textbox" 
								style="width:90%;"></td>
							<td width="10%" align="right">
								<a onclick="searchDict();" class="easyui-linkbutton" iconCls="icon-search">查询</a>
							</td>
							<td width="20%" align="left">
								<a onclick="clearDictForm();" class="easyui-linkbutton" iconCls="icon-undo">清空</a>
							</td>  
						</tr>
					</table>
				</form>
			</div>
		</div>
		<div class="fw" style="height:85%;">
			<div class="fh pl5 pr10">
				<table id="dataDictionaryTable"></table>
			</div>
		</div>
	</div>
	
</body>
