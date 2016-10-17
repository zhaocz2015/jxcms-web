/**
 * 系统用户
 */

$(function() {
	initVars();
	initCmts();
	initDataGrid();
	initItemGrid();
});

var urls = {
	form : "dic/dicForm",
	list : "dic/dicList",
	add : "dic/addDic",
	edit : "dic/editDic",
	rmv : "dic/rmvDic",

	itemForm : "dic/itemForm",
	itemList : "dic/itemList",
	itemTree : "dic/itemTree",
	addItem : "dic/addItem",
	editItem : "dic/editItem",
	rmvItem : "dic/rmvItem",
};

var modes = {
	add : "add",
	edit : "edit",
	rmv : "rmv",
	
	addItem: "addItem",
	editItem: "editItem",
	rmvItem: "rmvItem"
	
};

var dicLayout;
var dg, tb, form;
var itemDg, itemTb, itemForm;
function initVars() {
	dicLayout = $("#dicLayout");
	tb = $("#tb");
	dg = $("#dg");
	
	itemTb = $("#tb_item");
	itemDg = $("#dg_item");
}

function initCmts() {

}

function initDataGrid() {
	dg.datagrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar : tb,
		url : urls.list,
		loadMsg : "正在加载，请稍候",
		pagination : true,
		columns : [ [ {
			field : "id",
			checkbox : true
		}, {
			title : "编码",
			field : "code",
			width : 140
		}, {
			title : "名称",
			field : "name",
			width : 160
		}, {
			title : "类型",
			field : "type",
			width : 160
		} ] ],
		onSelect: function(index, row){
			initItemGrid(row);
//			itemDg.datagrid("load", {dicid: row.id});
		}
	});
}

function initItemGrid(row) {
	dicLayout.layout("panel", "east").panel("body").empty().append("<table id='dg_item'></table>");
	itemDg = $("#dg_item");
	if(!row || row.type==0){
		initItemList(row);
	}else{
		initItemTree(row);
	}
}

function initItemList(row){
	itemDg.datagrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar : itemTb,
		url : urls.itemList,
		loadMsg : "正在加载，请稍候",
		queryParams:{
			dicid: row ? row.id : ""
		},
		toolbar: [{
			text: "添加",
			iconCls: "icon-add",
			handler: function(){
				addItemWin();
			}
		},{
			text: "编辑",
			iconCls: "icon-edit",
			handler: function(){
				editItemWin()
			}
		},{
			text: "删除",
			iconCls: "icon-remove",
			handler: function(){
				rmvItem()
			}
		}],
		columns : [ [ {
			field : "id",
			checkbox : true
		}, {
			title : "名称",
			field : "name",
			width : 140
		}, {
			title : "值集",
			field : "val",
			width : 160
		} ] ]
	});
}

function initItemTree(row){
	itemDg.treegrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar : itemTb,
		idField: "id",
		treeField: "name",
		url : urls.itemTree,
		loadMsg : "正在加载，请稍候",
		queryParams:{
			dicid: row ? row.id : ""
		},
		toolbar: [{
			text: "添加",
			iconCls: "icon-add",
			handler: function(){
				addItemWin();
			}
		},{
			text: "编辑",
			iconCls: "icon-edit",
			handler: function(){
				editItemWin()
			}
		},{
			text: "删除",
			iconCls: "icon-remove",
			handler: function(){
				rmvItem()
			}
		}],
		columns : [ [ {
			field : "id",
			checkbox : true
		}, {
			title : "名称",
			field : "name",
			width : 140
		}, {
			title : "值集",
			field : "val",
			width : 160
		} ] ]
	});
}

function viewWin() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	showWin({
		title : "详情窗口",
		width : 300,
		height : 180,
		href : urls.form,
		buttons : [ {
			text : "关闭",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			form = $win.find("#userForm");
			form.find("tr.pwd-box").hide(); // 隐藏密码
			form.form("load", row);
			form.find("input[class^='easyui']").textbox("readonly");// 全部只读
		}
	});
}

function addWin() {
	showWin({
		title : "新增窗口",
		width : 300,
		height : 260,
		href : urls.form,
		buttons : [ {
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				submitForm(modes.add);
			}
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			form = $win.find("#userForm");
		}
	});
}

function editWin() {

	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	showWin({
		title : "编辑窗口",
		width : 300,
		height : 180,
		href : urls.form,
		buttons : [ {
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				submitForm(modes.edit)
			}
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			form = $win.find("#userForm");
			// 隐藏并取消密码框的校验
			form.find("input[textboxname*='password']").passwordbox("disableValidation");
			form.find("tr.pwd-box").hide();
			form.form("load", row);
		}
	});
}

function rmvRecord() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	showConfirm("删除提示", "是否删除数据字典【" + row.name + "】的数据记录？", function(r) {
		if (r) {
			showProgress("正在请求");
			$.post(urls.rmv, {
				id : row.id
			}, function(rsMsg) {
				closeProgress();

				if (rsMsg.success) {
					dg.datagrid("reload");
					// showInfo(rsMsg.info);
				} else {
					showAlert(rsMsg.info);
				}
			}, "JSON");
		}
	});
}


function addItemWin() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条数据字典记录");
		return;
	}
	
	showWin({
		title : "新增窗口",
		width : 300,
		height : 260,
		href : urls.itemForm,
		buttons : [ {
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				submitForm(modes.addItem);
			}
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			form = $win.find("#userForm");
			form.find("#dicid").val(row.id);
			
			if(row.type == 0){
				form.find("tr.pnode").hide();
			}else{
				form.find("tr.pnode").show();
				form.find("#pid").combotree({
					url: "dic/dicCmbtree?code=" + row.code
				});
			}
		}
	});
}

function editItemWin() {

	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条数据字典记录");
		return;
	}
	
	var irow = itemDg.datagrid("getSelected");
	if (!irow) {
		showAlert("请选择一条字典值集记录");
		return;
	}

	showWin({
		title : "编辑窗口",
		width : 300,
		height : 180,
		href : urls.itemForm,
		buttons : [ {
			text : "确定",
			iconCls : "icon-ok",
			handler : function() {
				submitForm(modes.editItem)
			}
		}, {
			text : "取消",
			iconCls : "icon-cancel",
			handler : function() {
				$win.dialog("close");
			}
		} ],
		onLoad : function() {
			form = $win.find("#userForm");
			
			if(row.type == 0){
				form.find("tr.pnode").hide();
				form.form("load", irow);
			}else{
				form.find("tr.pnode").show();
				form.find("#pid").combotree({
					url: "dic/dicCmbtree?code=" + row.code,
					onLoadSuccess: function(){
						form.form("load", irow);
					}
				});
			}
		}
	});
}

function rmvItem() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条数据字典记录");
		return;
	}

	var irow = itemDg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条字典值集记录");
		return;
	}

	showConfirm("删除提示", "是否删除字典值集【" + irow.name + "】的数据记录？", function(r) {
		if (r) {
			showProgress("正在请求");
			$.post(urls.rmvItem, {
				id : irow.id
			}, function(rsMsg) {
				closeProgress();

				if (rsMsg.success) {
					itemDg.datagrid("reload");
					// showInfo(rsMsg.info);
				} else {
					showAlert(rsMsg.info);
				}
			}, "JSON");
		}
	});
}

function submitForm(mode) {
	showProgress("正在提交");
	form.form("submit", {
		url : urls[mode],
		onSubmit : function() {
			var isValid = form.form("validate");
			if (!isValid) {
				closeProgress();
			}
			return isValid;
		},
		success : function(data) {
			closeProgress();

			var rsMsg = JSON.parse(data);
			if (rsMsg.success) {
				$win.dialog("close"); // 关闭窗口
				dg.datagrid("reload");// 刷新列表
				showInfo(rsMsg.msg);
			} else {
				showAlert(rsMsg.msg);
			}
		}
	});
}

function doQuery() {
	dg.datagrid("load", {
		loginname : $("#loginname").textbox("getValue"),
		username : $("#username").textbox("getValue")
	});
}

function doClear() {
	$("#loginname").textbox("clear");
	$("#username").textbox("clear");
	dg.datagrid("load", {});
}
