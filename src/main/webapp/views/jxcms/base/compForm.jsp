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
			<th>公司编码</th>
			<td>
				<input name="code" class="easyui-textbox" required="true" missingMessage="请输入公司编码" prompt="请输入公司编码" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>公司名称</th>
			<td>
				<input name="name" class="easyui-textbox" required="true" missingMessage="请输入公司名称" prompt="请输入公司名称" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>公司地址</th>
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
			<th>开户账号</th>
			<td>
				<input name="bankno" class="easyui-textbox" prompt="请输入开户账号" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>税号</th>
			<td>
				<input name="taxno" class="easyui-textbox" prompt="请输入税号" style="width:160px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>