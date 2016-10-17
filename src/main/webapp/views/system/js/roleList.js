/**
 * 系统用户
 */

$(function() {
	initVars();
	initCmts();
	
	initRUDataGrid();
	initRMTreeGrid();

	initDataGrid();
});

var urls = {
	form : "role/roleForm",
	list : "role/roleList",
	add : "role/addRole",
	edit : "role/editRole",
	rmv : "role/rmvRole",
	// 授权用户URL
	ruList : "role/ruList",
	addRU : "role/addRU",
	rmvRU : "role/rmvRU",
	// 授权菜单URL
	rmTree : "role/rmTree",
	addRM : "role/addRM",
	rmvRM : "role/rmvRM"
};

var modes = {
	add : "add",
	edit : "edit",
	rmv : "rmv"
};

var dg, tb, form;
var ruTb, ruDg, rmTb, rmTg;
function initVars() {
	tb = $("#tb");
	dg = $("#dg");

	ruTb = $("#tb_ru");
	ruDg = $("#dg_ru");
	rmTb = $("#tb_rm");
	rmTg = $("#dg_rm");
}

function initCmts() {

}

function initRUDataGrid(){
	ruDg.datagrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar: ruTb,
		url : urls.ruList,
		columns : [ [ {
			field : "ru_id",
			checkbox : true
		},{
			title : "账号名称",
			field : "loginname",
			width : 140
		}, {
			title : "用户名称",
			field : "username",
			width : 140
		}, {
			title: "是否授权",
			field:"id",
			width: 120,
			formatter:function(value, row, index){
				return row.ru_id ? "是" : "否";
			}
		} ] ],
		onBeforeSelect: function(index, row){
			var slcRow = dg.datagrid("getSelected");
			if(!slcRow){
				showAlert("请选择一条角色数据记录");
				return false;
			}
			
			if(row.ru_id){
				$("#btn_add_ru").linkbutton("disable");
				$("#btn_rmv_ru").linkbutton("enable");
			}else{
				$("#btn_add_ru").linkbutton("enable");
				$("#btn_rmv_ru").linkbutton("disable");
			}
		}
	});
}

function addAuthUser(){
	var slcRow = dg.datagrid("getSelected");
	if(!slcRow){
		showAlert("请选择一条角色数据记录");
		return false;
	}
	
	var ruRow = ruDg.datagrid("getSelected");
	if(!ruRow){
		showAlert("请选择一条用户数据记录");
		return false;
	}
	
	if(ruRow.ru_id){
		showAlert("此用户已经授权，无法添加授权")
		return;
	}
	
	$.post(urls.addRU, {
		roleId: slcRow.id, 
		userId: ruRow.user_id
	}, function(rsMsg){
		if(rsMsg.success){
			ruDg.datagrid("reload");
		}else{
			showAlert(rsMsg.msg)
		}
	}, "json");
}

function rmvAuthUser(){
	var slcRow = dg.datagrid("getSelected");
	if(!slcRow){
		showAlert("请选择一条角色数据记录");
		return false;
	}
	
	var ruRow = ruDg.datagrid("getSelected");
	if(!ruRow){
		showAlert("请选择一条用户数据记录");
		return false;
	}
	
	if(!ruRow.ru_id){
		showAlert("此用户尚未授权，无法取消授权")
		return;
	}
	
	$.post(urls.rmvRU, {
		id: ruRow.ru_id
	}, function(rsMsg){
		if(rsMsg.success){
			ruDg.datagrid("reload");
		}else{
			showAlert(rsMsg.msg)
		}
	}, "json");
}

function initRMTreeGrid(){
	rmTg.treegrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar: rmTb,
		idField: "id",
		treeField: "text",
		url : urls.rmTree,
		columns : [ [ {
			field : "id",
			checkbox : true
		}, {
			title : "菜单名称",
			field : "text",
			width : 240
		}, {
			title : "菜单url",
			field : "url",
			width : 240
		}, {
			title: "是否授权",
			field:"xxx",
			width: 120,
			formatter:function(value, row, index){
				return row.rmId ? "是" : "否";
			}
		} ] ],
		onBeforeSelect: function(row){
			var slcRow = dg.datagrid("getSelected");
			if(!slcRow){
				showAlert("请选择一条角色数据记录");
				return false;
			}
			
			if(row.rmId){
				$("#btn_add_rm").linkbutton("disable");
				$("#btn_rmv_rm").linkbutton("enable");
			}else{
				$("#btn_add_rm").linkbutton("enable");
				$("#btn_rmv_rm").linkbutton("disable");
			}
		}
	});
}

function addAuthMenu(){
	var slcRow = dg.datagrid("getSelected");
	if(!slcRow){
		showAlert("请选择一条角色数据记录");
		return false;
	}
	
	var rmRow = rmTg.datagrid("getSelected");
	if(!rmRow){
		showAlert("请选择一条菜单数据记录");
		return false;
	}
	
	if(rmRow.rmId){
		showAlert("此菜单已经授权，无法添加授权")
		return;
	}
	
	$.post(urls.addRM, {
		roleId: slcRow.id, 
		menuId: rmRow.id
	}, function(rsMsg){
		if(rsMsg.success){
			rmTg.treegrid("load", {roleId: slcRow.id}).treegrid("unselectAll");
		}else{
			showAlert(rsMsg.msg)
		}
	}, "json");
}

function rmvAuthMenu(){
	var slcRow = dg.datagrid("getSelected");
	if(!slcRow){
		showAlert("请选择一条角色数据记录");
		return false;
	}
	
	var rmRow = rmTg.datagrid("getSelected");
	if(!rmRow){
		showAlert("请选择一条菜单数据记录");
		return false;
	}
	
	if(!rmRow.rmId){
		showAlert("此菜单尚未授权，无法取消授权")
		return;
	}
	
	$.post(urls.rmvRM, {
		id: rmRow.rmId
	}, function(rsMsg){
		if(rsMsg.success){
			rmTg.treegrid("load", {roleId: slcRow.id}).treegrid("unselectAll");
		}else{
			showAlert(rsMsg.msg)
		}
	}, "json");
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
			title : "角色名称",
			field : "name",
			width : 140
		} ] ],
		onSelect: function(index, row){
			ruDg.datagrid("load", {
				id: row.id
			});
			rmTg.treegrid("load", {
				roleId: row.id
			}).treegrid("unselectAll");;
		}
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
		height : 80,
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
		height : 110,
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

	showConfirm("删除提示", "是否删除用户【" + row.username + "】的数据记录？", function(r) {
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
//				showInfo(rsMsg.msg);
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
