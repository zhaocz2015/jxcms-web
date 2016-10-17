/**
 * 系统用户
 */

$(function() {
	initVars();
	initCmts();
	initDataGrid();
});

var urls = {
	form : "cust/custForm",
	list : "cust/custList",
	add : "cust/addCust",
	edit : "cust/editCust",
	rmv : "cust/rmvCust"
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
			title : "供货商编码",
			field : "code",
			width : 140
		}, {
			title : "供货商名称",
			field : "name",
			width : 160
		}, {
			title : "供货商地址",
			field : "addr",
			width : 160
		}, {
			title : "联系电话",
			field : "telno",
			width : 160
		}, {
			title : "手机号码",
			field : "phone",
			width : 160
		}, {
			title : "QQ",
			field : "qq",
			width : 160
		}, {
			title : "Email",
			field : "email",
			width : 160
		}, {
			title : "联系人",
			field : "linkman",
			width : 160
		}, {
			title : "许可证号",
			field : "xkzh",
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
		height : 400,
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
			form.form("load", row);
			form.find("input[class^='easyui']").textbox("readonly");// 全部只读
		}
	});
}

function addWin() {
	showWin({
		title : "新增窗口",
		width : 300,
		height : 400,
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
		height : 400,
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

	showConfirm("删除提示", "是否删除这一条数据记录？", function(r) {
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
		code : $("#code").textbox("getValue"),
		name : $("#name").textbox("getValue")
	});
}

function doClear() {
	$("#code").textbox("clear");
	$("#name").textbox("clear");
	dg.datagrid("load", {});
}
