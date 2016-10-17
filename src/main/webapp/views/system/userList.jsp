<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/jsp/common.jsp"%>
<title>用户管理</title>
<script type="text/javascript" src="views/system/js/userList.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" border="false">
		<div id="tb" class="tb-css">
			<div class="tb-css-btn" >
				<a class="easyui-linkbutton" iconCls="icon-collapse" plain="true" onclick="toggleTb(this)"></a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-view" plain="true" onclick="viewWin()">详情</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addWin()">新增</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-edit" plain="true" onclick="editWin()">修改</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvRecord()">刪除</a>
				<span class="xian"></span>
				<a class="easyui-linkbutton" iconCls="icon-lock" plain="true" onclick="resetPwd()">重置密码</a>
			</div>
			<div class="tb-css-qry">
				&nbsp;
				账号名称：<input class="easyui-textbox" id="loginname" />
				&nbsp;
				用户名称：<input class="easyui-textbox" id="username" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="false" onClick="doQuery()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="false" onClick="doClear()">清空</a>
			</div>
		</div>
		<table id="dg"></table>
	</div>
</body>
</html>