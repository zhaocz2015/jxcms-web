<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/jsp/common.jsp"%>
<title>进销存管理系统</title>
<link rel="stylesheet" type="text/css" href="static/css/main.css" />
<script type="text/javascript" src="views/main/js/main.js"></script>
</head>
<body class="easyui-layout">
	<div id="loading" style="position: fixed; top: -50%; left: -50%; width: 200%; height: 200%; background: #fff; z-index: 100; overflow: hidden;">
		<img src="static/image/loading.gif" style="position: absolute; top: 0; left: 0; right: 0; bottom: 0; margin: auto;" />
	</div>
	<div region="north" style="background-color: #E0ECFF; overflow: hidden;">
		<span style="color: #3F4752; font-size: 18px; font-weight: bold;">进销存管理系统</span>

		<span style="float: right; padding-right: 20px;"> 
			<a href="javascript:void(0)" id="user_select_menu" class="easyui-menubutton" data-options="menu:'#menu_select', iconCls:'icon-cologne-user'" style="color: #3F4752"><shiro:principal /></a>
			<div id="menu_select" style="width: 150px;" data-options="noline:true">
				<div onclick="javascript:void()" data-options="iconCls:'icon-standard-vcard'">个人信息</div>
				<div onclick="javascript:void()" class="my_menu_select" data-options="iconCls:'icon-standard-user-edit'">修改密码</div>
			</div>
			<a href="javascript:void(0)" onclick="logout()" class="easyui-linkbutton index_top_linkbutton" style="color: #3F4752" plain="true" icon="icon-cologne-login" >安全退出</a>
		</span>
	</div>

	<div title="系统导航" region="west" style="width: 160px;" split="true">
		<ul id="menuTree"></ul>
	</div>

	<!-- Tab页签 -->
	<div id="mainPanle" region="center" border="true" border="false">
		 <div id="tabs" class="easyui-tabs"  fit="true" border="false" >
            <div id="home" title="首页提醒">
				<div class="cs-home-remark">
					<h1>首页提醒</h1>
				</div>
			</div>
        </div>
	</div>
	
	<!-- Tab页签上的右键菜单 -->
	<div id="mm" class="easyui-menu cs-tab-menu">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseother">关闭其他</div>
		<div id="mm-tabcloseall">关闭全部</div> 
	</div>

	<div region="south" style="height: 30px;line-height:30px; overflow: hidden;text-align: center;background-color: #eee" >Copyright © 2016 power by zcz</div>

</body>
</html>