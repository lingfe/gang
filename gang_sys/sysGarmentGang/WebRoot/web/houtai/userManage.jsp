<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>用户管理</title>
<!-- css 资源 -->
<link type="text/css" rel="stylesheet" href="css/page.css">
<link type="text/css" rel="stylesheet" href="css/search.css" />

<!-- js 脚本 -->
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/jquery.loadmask.js"></script>
<script type="text/javascript" src="js/base.js"></script>
<script type="text/javascript" src="js/payfeelist.js"></script>
<script type="text/javascript" src="js/wdatepicker.js"></script>
<script src="js/page.js" type="text/javascript"></script>
</head>

<body class="bodyid">
	<!-- 位置 start  -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="user/admin_index.jsp" style="color:red;">首页</a></li>
			<li>用户信息管理</li>
		</ul>
	</div>
	<!-- 位置 end  -->

	<!-- 查询条件 start -->
	<form action="<%=basePath%>manage/userManage.action"
		id="formid" method="post">
		<div class="tools" style="height: auto;">
			<input type="hidden" name="rows" id="rows" value="100" />
			<!-- 关键字 -->
			<div class="search">请输入：</div>
			<input type="text" id="searchvalue" name="searchKey"
				class="share-input-style search_input" placeholder='请输入关键字 '
				value="" />

			<!-- 时间条件 -->
			<div class="start_date">开始时间：</div>
			<input type="date" name="startTime"
				class="Wdate ipt share-input-style start_date_input">
			<div class="end_date">结束时间：</div>
			<input type="date" name="endTime"
				class="Wdate ipt share-input-style end_date_input" /> <input
				type="submit" class="share-button-style" value="查 询" />
		</div>
	</form>
	<!-- 查询条件 end -->

	<!-- 数据  start -->
	<div class="rightinfo">
		<table class="tablelist">
			<thead>
				<tr>
					<th style="width: 10%;" nowrap="nowrap">编号</th>
					<th style="width: 10%;" nowrap="nowrap">用户名</th>
					<th style="width: 10%;" nowrap="nowrap">最后一次登陆时间</th>
					<th style="width: 10%;" nowrap="nowrap">注册时间</th>
					<th style="width: 10%;" nowrap="nowrap">修改时间</th>
					<th style="width: 10%;" nowrap="nowrap">状态</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${userInfoList==null||userInfoList == '[]' }">
					<tr>
						<td colspan="6" align="center" style="color:#5AB8EF;">没有查询到数据！</td>
					</tr>
				</c:if>
				<c:forEach items="${userInfoList }" var="st">
					<tr>
						<td>${st.id }</td>
						<td>${st.userName }</td>
						<td>${st.lastTime }</td>
						<td>${st.cdate }</td>
						<td>${st.mdate }</td>
						<td>${st.state }</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 分页插件  end -->

	<!-- 分页插件   start  -->
	<div class="pageInfo"></div>
	<script type="text/javascript">
		// 调用分页扩展插件
		$(".pageInfo").customPage(${pageInfo.pageNum}, ${pageInfo.pageSize}, ${pageInfo.total});
	</script>
	<!-- 分页插件   end  -->
</body>
</html>
