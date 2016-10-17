<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/jsp/common.jsp"%>
<title>用户管理</title>
<script type="text/javascript" src="views/system/js/dicList.js"></script>
</head>
<body id="dicLayout" class="easyui-layout">
	<div region="center" title="系统字典列表">
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
				字典项编码：<input class="easyui-textbox" id="code" />
				&nbsp;
				字典项名称：<input class="easyui-textbox" id="name" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="false" onClick="doQuery()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="false" onClick="doClear()">清空</a>
			</div>
		</div>
		<table id="dg"></table>
	</div>
	
	<div region="east" title="字典值集列表" style="width: 50%;">
		<!--
		 <div id="tb_item" class="tb-css-btn">
			<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addItemWin()">新增</a>
			<span class="xian"></span>
			<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editItemWin()">修改</a>
			<span class="xian"></span>
			<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvItem()">删除</a>
		</div>
		
		<table id="dg_item"></table> -->
	</div>
</body>
</html>