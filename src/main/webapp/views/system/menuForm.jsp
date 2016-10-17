<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户弹窗</title>
</head>
<body>

<form id="userForm" method="post">
	<table class="list">
		<tr>
			<th>菜单名称</th>
			<td>
				<input name="name" class="easyui-textbox" required="true" missingMessage="请输入菜单名称" prompt="请输入菜单名称" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>上级菜单</th>
			<td>
				<input name="pid" class="easyui-combotree"  url="menu/menuTree.do" panelHeight="auto" prompt="请选择上级菜单" style="width:160px;"/>
				<input name="orderno" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>菜单URL</th>
			<td>
				<input name="url" class="easyui-textbox" rompt="请输入菜单Url" style="width:160px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>