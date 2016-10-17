<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/jsp/common.jsp"%>
<title>菜单管理</title>
<script type="text/javascript" src="views/system/js/menuList.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" title="系统菜单列表（拖动操作上下排序）">
		<div id="tb" class="tb-css">
			<div class="tb-css-btn">
				<a class="easyui-linkbutton" iconCls="icon-collapse" plain="true" onclick="toggleTb(this)"></a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewWin()">详情</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWin()">新增</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editWin()">修改</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvRecord()">刪除</a>
			</div>
			<div class="tb-css-qry">
				&nbsp;
				菜单名称：<input class="easyui-textbox" id="name" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="false" onClick="doQuery()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="false" onClick="doClear()">清空</a>
			</div>
		</div>
		<table id="dg"></table>
		<div id="mm" class="easyui-menu" style="width:120px;">
			<div onclick="appendSub()" data-options="iconCls:'icon-add'">添加下级节点</div>
			<div onclick="appendNode()" data-options="iconCls:'icon-add'">添加同级节点</div>
			<div onclick="removeNode()" data-options="iconCls:'icon-remove'">删除节点</div>
		</div>
	</div>
	<!--  div region="east" title="系统操作列表" style="width: 50%;" >
		<div id="tb2" class="tb-css">
			<div class="tb-css-btn">
				<a class="easyui-linkbutton" iconCls="icon-collapse" plain="true" onclick="toggleTb(this)"></a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewWin()">详情</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWin()">新增</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editWin()">修改</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvRecord()">刪除</a>
			</div>
			<div class="tb-css-qry">
				&nbsp;
				菜单名称：<input class="easyui-textbox" id="name" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="false" onClick="doQuery()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="false" onClick="doClear()">清空</a>
			</div>
		</div>
		<table id="dg2"></table>
	</div -->
</body>
</html>