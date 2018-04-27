<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>欢迎登录后台管理系统</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />
<link rel="stylesheet" type="text/css" href="css/qikoo.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/cloud.js"></script>
<script src="<%=basePath %>js/qikoo.js" type="text/javascript"></script>
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
		
			var msg="${msg}";
			if(msg!=""){
				qikoo.dialog.alert('${msg}');
			}
	});

	
</script>
</head>
<body
	style='background-color:#1c77ac; background-image:url("bg/light.png");
                background-repeat:no-repeat; background-position:center top; overflow:hidden;'>
	<div id="mainBody">
		<div id="cloud1" class="cloud"></div>
		<div id="cloud2" class="cloud"></div>
	</div>
	<div class="logintop">
		<span>欢迎登录后台管理界面平台</span>
		<ul>
			<li><a href="#">回首页</a></li>
			<li><a href="#">帮助</a></li>
			<li><a href="#">关于</a></li>
		</ul>
	</div>
	<div class="loginbody">
		<form action="<%=basePath %>user/login.action" method="post">
			<input name="redirectUrl" type="hidden" value="" /> <span
				class="systemlogo"></span>
			<div class="loginbox">
				<ul>
					<li class="loginuser">
					<input name="userName" type="text"
						style="height: 30px;width:290px;margin:9px auto;
                background-color: #EBF5FA;border:none;outline:none;-webkit-box-shadow: 0 0 0px 1000px #EBF5FA inset;color:#90a2bc;font-size:14px;"
						placeholder="用户名" onclick="JavaScript:$(this).val('')" /></li>
					<li class="loginpwd">
					<input name="pwd" type="password"
						placeholder="密码" style="height: 30px;width:290px;margin: 9px auto;
                background-color: #EBF5FA;border:none;outline:none;-webkit-box-shadow: 0 0 0px 1000px #EBF5FA inset;color:#90a2bc;font-size:14px;" /></li>
					<li><input name="" type="submit" class="loginbtn" value="登录" />
						<label><input name="" type="checkbox" value=""
							checked="checked" />记住密码</label> <label><a href="#">忘记密码？</a></label></li>
				</ul>
			</div>
		</form>
		<%-- <img alt="" src="<%=path%>/vcode"> --%>
	</div>
</body>
</html>
