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
			<th>客户编码</th>
			<td>
				<input name="code" class="easyui-textbox" required="true" missingMessage="请输入客户编码" prompt="请输入公司编码" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>客户名称</th>
			<td>
				<input name="name" class="easyui-textbox" required="true" missingMessage="请输入客户名称" prompt="请输入公司名称" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>客户地址</th>
			<td>
				<input name="addr" class="easyui-textbox" required="true" missingMessage="请输入公司地址" prompt="请输入公司地址" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>联系人</th>
			<td>
				<input name="linkman" class="easyui-textbox" prompt="请输入联系人" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>联系电话</th>
			<td>
				<input name="telno" class="easyui-textbox" prompt="请输入联系电话" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>手机号码</th>
			<td>
				<input name="phone" class="easyui-textbox" prompt="请输入手机号码" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>QQ</th>
			<td>
				<input name="qq" class="easyui-textbox" prompt="请输入QQ" style="width:160px;"/>
			</td>
		<tr>
			<th>邮箱</th>
			<td>
				<input name="email" class="easyui-textbox" prompt="请输入邮箱" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>许可证号</th>
			<td>
				<input name="xkzh" class="easyui-textbox" prompt="请输入许可证号" style="width:160px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>