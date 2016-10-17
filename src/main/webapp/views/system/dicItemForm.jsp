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
			<th>字典项名称</th>
			<td>
				<input name="name" class="easyui-textbox" required="true" missingMessage="请输入字典项名称" prompt="请输入字典项名称" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>字典项值集</th>
			<td>
				<input name="val" class="easyui-textbox" required="true" missingMessage="请输入字典项值集" prompt="请输入字典项值集" style="width:160px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>