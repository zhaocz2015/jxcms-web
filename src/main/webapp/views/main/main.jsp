<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE html>
<html>
<head>
<%@ include file="/static/jsp/common.jsp"%>
<link rel="stylesheet" href="static/css/main.css" />
<script type="text/javascript" src="views/main/js/main.js"></script>
<title>进销存管理系统</title>
</head>
<body class="easyui-layout">
	<!-- 顶部LOGO菜单 -->
	<div region="north" style="background: url(static/images/topbg.gif) repeat-x;">
		<div class="topleft">
			<a href="main" target="_parent"><img src="static/images/logo.png" title="系统首页" /></a>
		</div>

		<ul class="nav">
			<c:forEach var="menu" items="${menus}" varStatus="vs">
				<li>
					<c:choose>
						<c:when test="${vs.index==0}">
							<a href="${menu.url}" class="selected"><img src="static/images/icon01.png" title="${menu.text}" />
							<h2>${menu.text}</h2></a>
						</c:when>
						<c:otherwise>
							<a href="${menu.url}"><img src="static/images/icon0${vs.index+1}.png" title="${menu.text}" />
							<h2>${menu.text}</h2></a>
						</c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
		</ul>

		<div class="topright">
			<ul>
				<li class="title"><span><img src="static/images/help.png" title="帮助" class="helpimg" /></span><a >帮助</a></li>
				<li class="title"><a >关于</a></li>
				<li class="title"><a onclick="logout()">退出</a></li>
			</ul>

			<div class="user">
				<span>admin</span> <i>消息</i> <b>5</b>
			</div>
		</div>

	</div>

	<!-- 左侧导航菜单 -->
	<div region="west" border="0" style="width:187px;background: #f0f9fd;">

		<div class="lefttop">
			<span></span>导航菜单
		</div>

		<dl class="leftmenu">
			<c:forEach items="${menus }" var="menu" varStatus="vs">
				<dd>
					<div class="title">
						<span><img src="static/images/leftico0${vs.index+1}.png" /></span>
						${menu.text }
					</div>
					<ul class="menuson">
						<c:forEach items="${menu.children }" var="item">
							<li><cite></cite><a onclick="openMenuTab('${item.id}', '${item.text}', '${item.url}')">${item.text }</a><i></i></li>
						</c:forEach>
					</ul>
				</dd>
			</c:forEach>
		</dl>
	</div>

	<!-- 中间区域页面 -->
	<div region="center" border="0">
		<div id="tabs" class="easyui-tabs" fit="true" border="false" >
			<div title="首页" border="false">首页</div>
			<div title="用户管理" closable="true" style="overflow:hidden;" border="false">
				<iframe src='user/userEntry' border="0" width="100%" height="100%"></iframe>
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

</body>
</html>