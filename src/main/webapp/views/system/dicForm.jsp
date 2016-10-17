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
			<th>字典编码</th>
			<td>
				<input name="code" class="easyui-textbox" required="true" missingMessage="请输入字典编码" prompt="请输入字典编码" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>字典名称</th>
			<td>
				<input name="name" class="easyui-textbox" required="true" missingMessage="请输入字典名称" prompt="请输入字典名称" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>字典类型</th>
			<td>
				<input name="type" class="easyui-combobox" url="dic/dicCmblist?code=DIC_TYPE" required="true" missingMessage="请选择字典类型" prompt="请选择字典类型" style="width:160px;" panelHeight="auto"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>