<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/jsp/common.jsp"%>
<title>用户管理</title>
<script type="text/javascript" src="views/system/js/roleList.js"></script>
</head>
<body class="easyui-layout">
	<div region="center" title="系统角色列表">
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
				角色名称：<input class="easyui-textbox" id="name" />
				<a class="easyui-linkbutton" iconCls="icon-search" plain="false" onClick="doQuery()">查询</a>
				<a class="easyui-linkbutton" iconCls="icon-reload" plain="false" onClick="doClear()">清空</a>
			</div>
		</div>
		<table id="dg"></table>
	</div>
	
	<div region="east" style="width: 50%;">
		<div class="easyui-layout" fit="true">
			<div region="north" title="授权用户列表" style="height: 50%;">
				<div id="tb_ru" class="tb-css-btn">
					<a id="btn_add_ru" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAuthUser()">添加授权</a>
					<span class="xian"></span>
					<a id="btn_rmv_ru" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvAuthUser()">取消授权</a>
				</div>
				<table id="dg_ru"></table>
			</div>		
			<div region="center" title="授权菜单列表">
				<div id="tb_rm" class="tb-css-btn">
					<a id="btn_add_rm" class="easyui-linkbutton" iconCls="icon-add" plain="true" onclick="addAuthMenu()">添加授权</a>
					<span class="xian"></span>
					<a id="btn_rmv_rm" class="easyui-linkbutton" iconCls="icon-remove" plain="true" onclick="rmvAuthMenu()">取消授权</a>
				</div>
				<table id="dg_rm"></table>
			</div>		
		</div>
	</div>
</body>
</html>