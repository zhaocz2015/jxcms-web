<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>进销存管理系统-用户登录</title>
<%@ include file="/static/jsp/common.jsp"%>
<link rel="stylesheet" type="text/css" href="static/css/login2.css">
</head>
<body>

	<div class="account-flex-container">
		<div class="account-container">
			<h2>进销存管理系统</h2>

			<div class="account-input">
				<input id="loginname" placeholder="用户名" name="loginname" value="admin"/>
			</div>
			<div class="account-input">
				<input id="password" type="password" placeholder="密码" name="password" value="1"/>
			</div>
			<div class="account-input captcha">
				<input id="captcha" placeholder="验证码" name="captcha" /> <img src="static/images/kaptcha.jpg" onclick="refresh(this)" />
			</div>
			<div style="position: relative;">
				<button onclick="login()">登录</button>
			</div>
			<span id="error" style="display: block; margin-top: 15px; text-align: center; color: red;"></span>

		</div>
	</div>

	<script type="text/javascript">
		function refresh(target) {
			target.src = "static/images/kaptcha.jpg?t=" + Math.random();
		}

		function check() {
			if (!$("#loginname").val()) {
				$("#error").text("用户名不可为空").show();
				return false;
			}

			if (!$("#password").val()) {
				$("#error").text("密码不可为空").show();
				return false;
			}

			if (!$("#captcha").val()) {
				$("#error").text("验证码不可为空").show();
				return false;
			}

			return true;
		}

		function login() {
			if (check()) {
				$("#error").hide();

				$.ajax({
					type : "POST",
					url : 'login_login',
					data : {
						loginname : $("#loginname").val(),
						password : $("#password").val(),
						captcha : $("#captcha").val()
					},
					dataType : 'json',
					cache : false,
					success : function(rsMsg) {
						if (rsMsg.success) {
							window.location.href = "main";
						} else {
							$("#error").text(rsMsg.msg).show();
						}
					}
				});
			}
		}
	</script>

</body>
</html>