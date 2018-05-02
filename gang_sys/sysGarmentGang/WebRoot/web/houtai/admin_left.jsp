<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>物业管理系统
</title>
<link href="css/style.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js"></script>

<script type="text/javascript">
	$(function() {
		//导航切换
		$(".menuson li").click(function() {
			$(".menuson li.active").removeClass("active")
			$(this).addClass("active");
		});

		$('.title').click(function() {
			var $ul = $(this).next('ul');
			$('dd').find('ul').slideUp();
			if ($ul.is(':visible')) {
				$(this).next('ul').slideUp();
			} else {
				$(this).next('ul').slideDown();
			}
		});
	})
</script>

</head>
<body style="background:#f0f9fd;">
	<div class="lefttop">
		<span></span>信息管理系统
	</div>
	<dl class="leftmenu">
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>用户管理
			</div>
			<ul class="menuson">
				<li><cite></cite>
					<a href="<%=basePath %>manage/userManage.action" target="rightFrame">用户管理</a>
				<i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>款式管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="<%=basePath %>manage/styleTypeManage.action" target="rightFrame">服装款式类型信息管理</a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>服装管理
			</div>
			<ul class="menuson">

				<li><cite></cite><a href="<%=basePath %>manage/clothingInfoManage.action?rows=1"
					target="rightFrame">服装信息管理</a><i></i></li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img alt="" src="images/leftico01.png"></span>预约管理
			</div>
			<ul class="menuson">
				<li>
					<cite></cite>
					<a href="<%=basePath %>manage/appointManage.action" target="rightFrame">上门预约信息管理</a>
					<i></i>
				</li>
			</ul>
		</dd>
		<dd>
			<div class="title">
				<span><img src="images/leftico01.png" /></span>日志管理
			</div>
			<ul class="menuson">
				<li><cite></cite><a href="<%=basePath %>manage/sysLogManage.action"
					target="rightFrame">系统日志管理</a><i></i></li>
				<li><cite></cite><a href="<%=basePath %>manage/accessLogManage.action"
					target="rightFrame">访问日志管理</a><i></i></li>
			</ul>
		</dd>
	</dl>
</body>
</html>
