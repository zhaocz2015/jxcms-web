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
			<td colspan="4" style="background-color:#EFF5FF;font-weight:bold; text-align:center;">基本信息</td>
		<tr>
		<tr>
			<th>账号名称</th>
			<td>
				<input name="loginname" class="easyui-textbox" required="true" missingMessage="请输入用户名" prompt="请输入用户名" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>用户名称</th>
			<td>
				<input name="username" class="easyui-textbox" required="true" missingMessage="请输入用户名" prompt="请输入用户名" style="width:160px;"/>
			</td>
		</tr>
		<tr class="pwd-box">
			<th>输入密码</th>
			<td>
				<input id="PWD" name="password" class="easyui-passwordbox" required="true" validType="eqPwd['#R_PWD']" missingMessage="请输入密码" prompt="请输入密码" style="width:160px;"/>
			</td>
		</tr>
		<tr class="pwd-box">
			<th>确认密码</th>
			<td>
				<input id="R_PWD" name="confirm_password" class="easyui-passwordbox" required="true" validType="eqPwd['#PWD']" missingMessage="请确认密码" prompt="请确认密码" style="width:160px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>