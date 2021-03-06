/**
 * 系统用户
 */

$(function() {
	initVars();
	initCmts();
	initDataGrid();
});

var urls = {
	form : "user/userForm",
	list : "user/userList",
	add : "user/addUser",
	edit : "user/editUser",
	rmv : "user/rmvUser",
	reset : "user/editPwd"
};

var modes = {
	add : "add",
	edit : "edit",
	rmv : "rmv"
};

var dg, tb, form;
function initVars() {
	tb = $("#tb");
	dg = $("#dg");
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
			title : "账号名称",
			field : "loginname",
			width : 140
		}, {
			title : "用户名称",
			field : "username",
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

function resetPwd() {
	var row = dg.datagrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	showPrompt("重置密码", "请输入用户【" + row.username + "】的重置密码", function(pwd) {
		if (pwd) {
			showProgress("正在请求");
			$.post(urls.reset, {
				id : row.id,
				password : pwd
			}, function(rsMsg) {
				closeProgress();

				if (rsMsg.success) {
					dg.datagrid("reload");
					showInfo(rsMsg.msg);
				} else {
					showAlert(rsMsg.msg);
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
