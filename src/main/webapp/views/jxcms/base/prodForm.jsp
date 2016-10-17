<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品弹窗</title>
</head>
<body>

<form id="userForm" method="post">
	<table class="list">
		<tr>
			<th>产品编码</th>
			<td>
				<input name="code" class="easyui-textbox" required="true" missingMessage="请输入客户编码" prompt="请输入公司编码" style="width:160px;"/>
				<input name="id" type="hidden"/>
			</td>
		</tr>
		<tr>
			<th>产品名称</th>
			<td>
				<input name="name" class="easyui-textbox" required="true" missingMessage="请输入客户名称" prompt="请输入公司名称" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>型号规格</th>
			<td>
				<input name="xhgg" class="easyui-textbox" required="true" missingMessage="请输入公司地址" prompt="请输入公司地址" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>生产企业</th>
			<td>
				<input name="scqy" class="easyui-textbox" prompt="请输入联系人" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>批准文号</th>
			<td>
				<input name="pzwh" class="easyui-textbox" prompt="请输入联系电话" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>有效期</th>
			<td>
				<input name="yxq" class="easyui-textbox" prompt="请输入手机号码" style="width:160px;"/>
			</td>
		</tr>
		<tr>
			<th>单位</th>
			<td>
				<input name="dw" class="easyui-textbox" prompt="请输入QQ" style="width:160px;"/>
			</td>
		<tr>
			<th>价格</th>
			<td>
				<input name="price" class="easyui-textbox" prompt="请输入邮箱" style="width:160px;"/>
			</td>
		</tr>
	</table>
</form>

</body>
</html>