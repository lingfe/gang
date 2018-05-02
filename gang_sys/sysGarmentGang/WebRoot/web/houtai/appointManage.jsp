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

<title>预约管理</title>
<!-- css 资源 -->
<link type="text/css" rel="stylesheet" href="css/page.css">
<link type="text/css" rel="stylesheet" href="css/search.css" />
<link type="text/css" rel="stylesheet" href="css/qikoo.css" />

<!-- js 脚本 -->
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/page.js" ></script>
<script type="text/javascript" src="js/qikoo.js" ></script>
<script type="text/javascript" src="js/payfeelist.js"></script>

</head>

<body class="bodyid">
	<!-- 位置 start  -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="user/admin_index.jsp" style="color:red;">首页</a></li>
			<li>上门预约信息管理</li>
		</ul>
	</div>
	<!-- 位置 end  -->

	<!-- 查询条件 start -->
	<form action="<%=basePath%>manage/appointManage.action" id="formid"
		method="post">
		<div class="tools" style="height: auto;">
			<input type="hidden" name="rows" id="rows" value="100" />
			<!-- 关键字 -->
			<div class="search">请输入：</div>
			<input type="text" id="searchvalue" name="searchKey" class="share-input-style search_input" placeholder='请输入关键字 ' value=""/>
			
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
		<table class="tablelist" id="tab_list">
			<thead>
				<tr>
					<th style="width: 10%;" nowrap="nowrap">编号</th>
					<th style="width: 6%;" nowrap="nowrap">姓名</th>
					<th style="width: 6%;" nowrap="nowrap">电话号码</th>
					<th style="width: 10%;" nowrap="nowrap">地区</th>
					<th style="width: 10%;" nowrap="nowrap">信息地址</th>
					<th style="width: 10%;" nowrap="nowrap">创建时间</th>
					<th style="width: 10%;" nowrap="nowrap">修改时间</th>
					<th style="width: 3%;" nowrap="nowrap">状态</th>
					<th style="width: 3%;" nowrap="nowrap">版本</th>
					<th style="width: 10%;" nowrap="nowrap">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${appointList==null||appointList == '[]' }">
					<tr>
						<td colspan="7" align="center" style="color:#5AB8EF;">没有查询到数据！</td>
					</tr>
				</c:if>
				<c:forEach items="${appointList }" var="st">
				<form action="manage/appointManage.action" method="post" id="form_update${st.id }">
					<input type="hidden" name="pageIndex" value="${pageInfo.pageNum }">
					<input type="hidden" name="pageNum" value="${pageInfo.pageSize }">
					<input type="hidden" name="param" id="param${st.id }" value="">
					<input type="hidden" name="creator" value="${st.creator }">
					<input type="hidden" name="modify" value="${wscSession.userName }">
					<input type="hidden" name="version" value="${st.version }">
					<input type="hidden" name="cdate" value="${st.cdate }">
					<input type="hidden" name="mdate" value="${st.mdate }">
					<table class="tablelist" >
					<tr>
						<td style="width: 10%;"><input type="text" class="input_text" name="id" readonly="readonly" value="${st.id }"/></td>
						<td style="width: 6%;"><input type="text" class="input_text" name="fullName" value="${st.fullName }"/></td>
						<td style="width: 6%;"><input type="text" class="input_text" name="phone" value="${st.phone }"/></td>
						<td style="width: 10%;"><input type="text" class="input_text" name="region" value="${st.region }"/></td>
						<td style="width: 10%;"><input type="text" class="input_text" name="address" value="${st.address }"/></td>
						<td style="width: 10%;"><input type="text" class="input_text" readonly="readonly" value="${st.cdate }"/></td>
						<td style="width: 10%;"><input type="text" class="input_text" readonly="readonly" value="${st.mdate }"/></td>
						<td style="width: 3%;"><input type="number" name="state" class="input_text" value="${st.state }"/></td>
						<td style="width: 3%;"><input type="text" class="input_text" readonly="readonly" value="${st.version }"/></td>
						<td style="width: 10%;">
							<input type="button" onclick="javascript:PAYFEES.from_update('${st.id }');" class="cz_button update" value="保存" />
							<input type="button" onclick="javascript:PAYFEES.from_delete('${st.id }');" class="cz_button delete" value="删除" />
						</td>
					</tr>
					</table>
				</form>
				</c:forEach>
			</tbody>
		</table>
	</div>
	<!-- 数据  end -->

	<!-- 分页插件   start  -->
	<div class="pageInfo"></div>
	<script type="text/javascript">
		// 调用分页扩展插件
		$(".pageInfo").customPage(${pageInfo.pageNum}, ${pageInfo.pageSize}, ${pageInfo.total});
	</script>
	<!-- 分页插件   end  -->
</body>
</html>
