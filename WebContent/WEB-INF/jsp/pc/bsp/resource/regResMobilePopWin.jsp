<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
    
<body>
    <script type="text/javascript">
    var rmMobileTree;	//菜单树变量
    var rmSetting = {
  		async : {
  			enable : true, 			//设置 zTree是否开启异步加载模式
  			url : "mobile/menu/getMenu", 	//Ajax 获取数据的 URL 地址
  			autoParam : [ "id" ]	//异步加载时自动提交父节点属性的参数,假设父节点 node = {id:1, name:"test"}，异步加载时，提交参数 zId=1
  		},
  		data:{ //必须使用data
  			simpleData : {
  				enable : true,
  				idKey : "id", 	//id编号命名 默认
  				pIdKey : "pId", //父id编号命名 默认
  				rootPId : 0 	//用于修正根节点父节点数据，即 pIdKey 指定的属性值
  			}
  		},
  		//回调函数
  		callback : {
  			onClick : function(event, treeId, treeNode, clickFlag) {
  			},
  			//捕获异步加载出现异常错误的事件回调函数 和 成功的回调函数
  			onAsyncError : function(event, treeId, treeNode, XMLHttpRequest, textStatus, errorThrown) {  
  				$.messager.alert("提示","加载错误：" + XMLHttpRequest,"info");   
  			},
  			onAsyncSuccess : function(event, treeId, treeNode, msg){
  				rmMobileTree.expandAll(true);//展开全部菜单
  			}
  		}
  	};
    
  	jQuery(function($){
  		$(document).ready(function() {
  		    //加载菜单树
  			rmMobileTree = $.fn.zTree.init($("#resMobileMenuTree"), rmSetting);
  		});
	});

  	//保存[资源-菜单]对应关系
  	function addResMobileReg(){
  		var sNode = rmMobileTree.getSelectedNodes()[0];
  		//更新
		$.post("mobile/resource/saveResMobileMenu?menuId="+sNode.id, $("#regResMobileForm").serializeArray(), function(data){
			closeWindow();
			$('#resourceTable').datagrid('reload');
			$.messager.alert('提示',data.mes,'info');
		});
  	}
	
	</script>
	
	<form id="regResMobileForm" method="post" class="operationPage" style="margin-top:0;padding-top:0;">
		<table style="border-collapse:collapse;">
			<tr style="display:none;">
				<td style="width:50%"><input id="resourceId" name="resourceId" type="hidden"></td>
			</tr>
			<tr>
				<td valign="top" style="margin-top:0;padding-top:0;"><ul id="resMobileMenuTree" class="ztree"></ul></td>
			<tr>
			<tr>
				<td>
					<a onclick="closeWindow();" class="easyui-linkbutton" iconCls="icon-cancel">返回</a>
					&nbsp;&nbsp;&nbsp;&nbsp;
					<a onclick="addResMobileReg();" class="easyui-linkbutton" iconCls="icon-save">保存</a>
				</td>
			</tr>
		</table>
	</form>
</body>
