<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%
	String path = request.getContextPath(); 
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="static/image/favicon.png" type="image/x-icon">
<link rel="stylesheet" href="static/css/common.css" />
<link rel="stylesheet" href="static/css/login.css" />
<script type="text/javascript" src="static/easyui/1.5/jquery.min.js"></script>
<script type="text/javascript" src="static/js/cloud.js"></script>
<title>欢迎登录后台管理系统</title>

<script language="javascript">
	$(function() {
		$('.loginbox').css({
			'position' : 'absolute',
			'left' : ($(window).width() - 692) / 2
		});
		$(window).resize(function() {
			$('.loginbox').css({
				'position' : 'absolute',
				'left' : ($(window).width() - 692) / 2
			});
		})
	});
</script>

</head>

<body style="background-color: #1c77ac; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">

	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>

	<div class="logintop">
		<span>欢迎登录进销存管理系统平台</span>
		<ul>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>

	<div class="loginbody">
		<span class="systemlogo"></span>
		<div class="loginbox">
			<ul>
				<li><input id="loginname" type="text" class="loginuser" value="admin" /></li>
				<li><input id="password" type="password" class="loginpwd" value="1" /></li>
				<li><input type="button" class="loginbtn" value="登录" onclick="login()" />
					<label><input name="" type="checkbox" value="" checked="checked" />记住密码</label>
				</li>
			</ul>
		</div>
	</div>

	<div class="loginbm">
		版权所有 2016 <a href="#">进销存管理系统</a> 
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

			/* if (!$("#captcha").val()) {
				$("#error").text("验证码不可为空").show();
				return false;
			} */

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
