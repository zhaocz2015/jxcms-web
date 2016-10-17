/**
 * 系统菜单
 */

$(function() {
	initVars();
	initCmts();
	inittreegrid();
//	inittreegrid2();
});

var urls = {
	form : "menu/menuForm",
	list : "menu/menuTree",
	add : "menu/addMenu",
	edit : "menu/editMenu",
	rmv : "menu/rmvMenu",
	sort: "menu/sortMenu"
};

var modes = {
	add : "add",
	edit : "edit",
	rmv : "rmv"
};

var dg, tb, form;
var dg2, tb2, form2;
function initVars() {
	tb = $("#tb");
	dg = $("#dg");
	
	tb2 = $("#tb2");
	dg2 = $("#dg2");
}

function initCmts() {

}

function inittreegrid() {
	dg.treegrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar : tb,
		idField: "id",
		treeField: "text",
		url : urls.list,
		loadMsg : "正在加载，请稍候",
		columns : [ [ {
			field : "id",
			checkbox : true
		}, {
			title : "菜单名称",
			field : "text",
			width : 240
		}, {
			title : "菜单URL",
			field : "url",
			width : 160
		} ] ],
		onContextMenu: function(e, row){
			e.preventDefault();
			// select the node
			$(this).treegrid('select', row.id);
			// display context menu
			$('#mm').menu('show', {
				left: e.pageX,
				top: e.pageY
			});
		},
		onDrop: function(targetRow, sourceRow, point){
			// 同一层级
			if(targetRow.pid == sourceRow.pid){
				if(point == "append"){
					// 同级叠加
					sourceRow.pid = targetRow.id;
					var children = dg.treegrid("getChildren", targetRow.id);
					sourceRow.orderno = children.length + 1;
					
					// 刷新排序
					sortMenu([sourceRow]);
				}else{
					// 同级排序
					var children = dg.treegrid("getChildren", sourceRow.pid);
					sortMenu(children);
				}
			}else{
				// 不同级次
				if(point == "append"){
					// 叠加
					sourceRow.pid = targetRow.id;
					var children = dg.treegrid("getChildren", targetRow.id);
					sourceRow.orderno = children.length + 1;
					
					// 刷新排序
					sortMenu([sourceRow]);
				}else{
					var chidlren;
					sourceRow.pid = targetRow.pid;
					if(sourceRow.pid){
						chidlren = dg.treegrid("getChildren", targetRow.pid);
					}else{
						chidlren = dg.treegrid("getRoots");
					}
					
					sortMenu(chidlren);
				}
			}
		},
		onLoadSuccess: function(row, data){
			$(this).treegrid("enableDnd", row ? row.id:null);
		}
	});
}

function sortMenu(rows){
	showProgress("正在提交");
	$.post(urls.sort, {jsonStr: encodeURIComponent(encodeURIComponent(JSON.stringify(rows)))}, function(rsMsg){
		dg.treegrid("reload");
		closeProgress();
		if(rsMsg.success){
//			showInfo(rsMsg.msg);
		}else{
			showAlert(rsMsg.msg);
		}
	}, "JSON");
}

/**
 * 添加子级菜单
 */
function appendSub(){
	var row = dg.treegrid("getSelected");
	addWin(row.id);
}

/**
 * 添加同级菜单
 */
function appendNode(){
	var row = dg.treegrid("getSelected");
	addWin(row.pid)
}

function removeNode(){
	rmvRecord();
}

function inittreegrid2() {
	dg2.treegrid({
		fit : true,
		striped : true,
		singleSelect : true,
		rownumbers : true,
		toolbar : tb2,
		idField: "id",
		treeField: "text",
		url : urls.list,
		loadMsg : "正在加载，请稍候",
		columns : [ [ {
			field : "id",
			checkbox : true
		}, {
			title : "菜单名称",
			field : "text",
			width : 140
		}, {
			title : "菜单URL",
			field : "url",
			width : 160
		} ] ]
	});
}

function viewWin() {
	var row = dg.treegrid("getSelected");
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

function addWin(pid) {
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
			form.find("input[name='orderno']").val(dg.treegrid("getRoots").length + 1);
			form.find("input[textboxname='pid']").combotree({
				onChange: function(nv, ov){
					var children = dg.treegrid("getChildren", nv);
					form.find("input[name='orderno']").val(children.length + 1);
				},
				onLoadSuccess: function(){
					if(pid){
						form.find("input[name='orderno']").val(dg.treegrid("getChildren", pid).length + 1);
						form.find("input[textboxname='pid']").combotree("setValue", pid);
					}else{
						form.find("input[name='orderno']").val(dg.treegrid("getRoots").length + 1);
					}
				}
			});
		}
	});
}

function editWin() {

	var row = dg.treegrid("getSelected");
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
			form.form("load", row);
		}
	});
}

function rmvRecord() {
	var row = dg.treegrid("getSelected");
	if (!row) {
		showAlert("请选择一条记录");
		return;
	}

	var children = dg.treegrid("getChildren", row.id);
	if(children.length > 0){
		showConfirm("删除提示", "是否删除菜单【" + row.name + "】以及下级菜单的数据记录？", function(r) {
			if (r) {
				showProgress("正在请求");
				$.post(urls.rmv, {
					id : row.id
				}, function(rsMsg) {
					closeProgress();
	
					if (rsMsg.success) {
						dg.treegrid("reload");
//						parent.location.reload(true);
						// showInfo(rsMsg.info);
					} else {
						showAlert(rsMsg.info);
					}
				}, "JSON");
			}
		});
	}else{
		showConfirm("删除提示", "是否删除菜单【" + row.name + "】的数据记录？", function(r) {
			if (r) {
				showProgress("正在请求");
				$.post(urls.rmv, {
					id : row.id
				}, function(rsMsg) {
					closeProgress();
	
					if (rsMsg.success) {
						dg.treegrid("reload");
//						parent.location.reload(true);
						// showInfo(rsMsg.info);
					} else {
						showAlert(rsMsg.info);
					}
				}, "JSON");
			}
		});
	}
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
				dg.treegrid("reload");// 刷新列表
//				showInfo(rsMsg.msg + ", 请刷新浏览器窗口");
			} else {
				showAlert(rsMsg.msg);
			}
		}
	});
}

function doQuery() {
	dg.treegrid("load", {
		name : $("#name").textbox("getValue"),
	});
}

function doClear() {
	$("#name").textbox("clear");
	dg.treegrid("load", {});
}
