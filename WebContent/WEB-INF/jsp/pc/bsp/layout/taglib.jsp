<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/css/main.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/easyui/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/easyui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${ctx}/resources/ztree/css/zTreeStyle/zTreeStyle.css">
<link rel="shortcut icon" href="${ctx}/resources/image/project.ico" type="image/x-icon" />
<script type="text/javascript" src="${ctx}/resources/easyui/jquery.min.js"></script>
<!-- 解决新版jquery老浏览器兼容问题  也可以通过引用jquery-migrate-1.4.1.js-->
<!--<script type="text/javascript">
jQuery.browser={};(function(){jQuery.browser.msie=false; jQuery.browser.version=0;if(navigator.userAgent.match(/MSIE ([0-9]+)./)){ jQuery.browser.msie=true;jQuery.browser.version=RegExp.$1;}})();
</script>
-->
<script type="text/javascript" src="${ctx}/resources/easyui/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/easyui/locale/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${ctx}/resources/ztree/js/jquery.ztree.core-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/ztree/js/jquery.ztree.excheck-3.5.min.js"></script>
<script type="text/javascript" src="${ctx}/resources/js/orgTree.js"></script> 
<script type="text/javascript" src="${ctx}/resources/js/checkvalid.js"></script> 
<script type="text/javascript" src="${ctx}/resources/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/resources/ckeditor/ckeditor.js"></script>
<script type="text/javascript" src="${ctx}/resources/ckeditor/config.js"></script>


<script type="text/javascript" src="${ctx}/resources/easyui/datagrid-detailview.js"></script>