var makeOrgTree = function makeOTree(jqueryObj,orgId){
	jqueryObj.combotree({
		url:"org/comTree?pid=0",
	    valueField: 'id',
	    textField: 'text',
	    required: false,
	    editable: false,
	    value:orgId,
	    onBeforeExpand:function(node){
	        jqueryObj.combotree("tree").tree("options").url="org/comTree?pid="+node.id;
	    },
	    onLoadSuccess: function (node, data) {
	        jqueryObj.combotree('tree').tree("collapseAll");
	    }
	});
};

var makeDictTree = function makeDTree(jqueryObj,orgId){
	jqueryObj.combotree({
		url:"datadictionary/comTree?pid=0",
	    valueField: 'id',
	    textField: 'text',
	    required: false,
	    editable: false,
	    value:orgId,
	    onBeforeExpand:function(node){
	        jqueryObj.combotree("tree").tree("options").url="datadictionary/comTree?pid="+node.id;
	    },
	    onLoadSuccess: function (node, data) {
	        jqueryObj.combotree('tree').tree("collapseAll");
	    }
	});
};

/*var makeDictTree =function makeDTree(jqueryObj){
	jqueryObj.combotree({
		url:"datadictionary/comTree?pid=0",
	    valueField: 'id',
	    textField: 'text',
	    required: false,
	    editable: false,
	    value:testId,
	    onBeforeExpand:function(node){
	        jqueryObj.combotree("tree").tree("options").url="datadictionary/comTree?pid="+node.id;
	    },
	    onLoadSuccess: function (node, data) {
	        jqueryObj.combotree('tree').tree("collapseAll");
	    }
	});
};*/