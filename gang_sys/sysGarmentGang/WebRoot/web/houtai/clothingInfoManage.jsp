<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<title>服装信息管理</title>
<!-- css 资源 -->
<link type="text/css" rel="stylesheet" href="css/page.css">
<link type="text/css" rel="stylesheet" href="css/search.css" />
<link type="text/css" rel="stylesheet" href="css/qikoo.css" />

<!-- js 脚本 -->
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript" src="js/page.js"></script>
<script type="text/javascript" src="js/qikoo.js"></script>
<script type="text/javascript" src="js/payfeelist.js"></script>
</head>

<body class="bodyid">
	<!-- 位置 start  -->
	<div class="place">
		<span>位置：</span>
		<ul class="placeul">
			<li><a href="user/admin_index.jsp" style="color:red;">首页</a></li>
			<li>服装信息管理</li>
		</ul>
	</div>
	<!-- 位置 end  -->

	<!-- 查询条件 start -->
	<form action="<%=basePath%>manage/clothingInfoManage.action"
		id="formid" method="post">
		<div class="tools" style="height: auto;">
			<input type="hidden" name="rows" id="rows" value="1" />
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
				<%-- <input
				type="button"
				onclick="javascript:PAYFEES.add_style_html(this,${pageInfo.pageNum },${pageInfo.pageSize },'${wscSession.userName }');"
				class="cz_button add" value="添加" /> --%>
		</div>
	</form>
	<!-- 查询条件 end -->

	<!-- 数据   start -->
	<div class="rightinfo">
		<c:forEach items="${clothingInfoList }" var="st">
			<form action="manage/clothingInfoManage.action" method="post"
				id="form_update${st.id }">
				<h1 style="width:100%;height:50px;text-align: center;line-height: 50px;font-size: 26px;">
					${st.clothingName }
				</h1>
				<input type="hidden" name="pageIndex" value="${pageInfo.pageNum }">
				<input type="hidden" name="pageNum" value="${pageInfo.pageSize }">
				<input type="hidden" name="param" id="param${st.id }" value="">
				<input type="hidden" name="creator" value="${st.creator }">
				<input type="hidden" name="modify" value="${wscSession.userName }">
				<textarea hidden="hidden" name="imgArray">${st.imgArray }</textarea>
				<textarea hidden="hidden" name="infoImgArray">${st.infoImgArray }</textarea>
				<input type="hidden" name="version" value="${st.version }">
				<input type="hidden" name="cdate" value="${st.cdate }"> <input
					type="hidden" name="mdate" value="${st.mdate }">
				<table class="tablelist">
					<tr>
						<th style="width: 5%;" nowrap="nowrap">编号</th>
						<td style="width:10%;""><input type="text" class="input_text"
							name="id" readonly="readonly" value="${st.id }" /></td>
						<th style="width: 5%;" nowrap="nowrap">款式名称</th>
						<td style="width:5%;"><input type="hidden"
							name="styleTypeInfoId" value="${st.styleTypeInfoId }"> <input
							type="text" class="input_text" name="styleName"
							value="${st.styleName }" /></td>
						<th style="width: 5%;" nowrap="nowrap">服装名称</th>
						<td style="width: 10%;"><input type="text" class="input_text"
							name="clothingName" value="${st.clothingName }" /></td>
					</tr>
					<tr>
						<th nowrap="nowrap">标签</th>
						<td><input type="text" name="lable" class="input_text"
							value="${st.lable }" /></td>
						<th nowrap="nowrap">封面图</th>
						<td onclick="tietuku_upload('${st.id }')"><input
							type="hidden" name="imgCover" id="imgCover${st.id }"
							value="${st.imgCover }" /> <img height="50" width="50" alt=""
							id="img${st.id }" src="${st.imgCover }"></td>
						<th nowrap="nowrap">材质说明</th>
						<td style="width: 10%;"><input type="text" name="material"
							class="input_text" value="${st.material }" /></td>
					</tr>
					<tr>
						<th nowrap="nowrap">销售价</th>
						<td style="width: 5%;"><input type="text" name="salesPrice"
							class="input_text" value="${st.salesPrice }" /></td>
						<th nowrap="nowrap">吊牌价</th>
						<td style="width: 5%;"><input type="text" name="tagPrice"
							class="input_text" value="${st.tagPrice }" /></td>
						<th nowrap="nowrap">是否显示</th>
						<td style="width: 10%;"><input type="hidden" name="isDisplay"
							id="isDisplay${st.id }" value="${st.isDisplay}"> <c:if
								test="${st.isDisplay=='否' }">
								<input
									onclick="javascript:PAYFEES.from_isDisplay(this,'${st.id }');"
									type="button" class="cz_button is_none" value="隐藏中" />
							</c:if> <c:if test="${st.isDisplay=='是' }">
								<input
									onclick="javascript:PAYFEES.from_isDisplay(this,'${st.id }');"
									type="button" class="cz_button is_block" value="显示中" />
							</c:if></td>
					</tr>
					<tr>
						<th nowrap="nowrap">创建时间</th>
						<td>${st.cdate}</td>
						<th nowrap="nowrap">修改时间</th>
						<td>${st.mdate }</td>
						<th nowrap="nowrap">状态</th>
						<td><input type="number" class="input_text" name="state"
							value="${st.state }" /></td>
					</tr>

					<tr>
						<th style="width: 3%;" nowrap="nowrap">轮播图</th>
						<td colspan="5" style="text-align: left;"><c:set
								value="${ fn:split(st.imgArray, ',') }" var="arr" /> <c:forEach
								items="${ arr }" var="img">
								<img height="100" width="100" src="${img }">
							</c:forEach></td>
					</tr>
					<tr>
						<th style="width: 3%;" nowrap="nowrap">详细图</th>
						<td colspan="5" style="text-align: left;"><c:set
								value="${ fn:split(st.infoImgArray, ',') }" var="arr" /> <c:forEach
								items="${ arr }" var="img">
								<img height="100" width="100" src="${img }">
							</c:forEach></td>
					</tr>
					<tr>
						<th style="width: 3%;" nowrap="nowrap">版本</th>
						<td><input type="text" class="input_text"
							readonly="readonly" value="${st.version }" /></td>
						<th nowrap="nowrap">操作</th>
						<td colspan="3"><input type="button"
							onclick="javascript:PAYFEES.from_update('${st.id }');"
							class="cz_button update" value="保存" /> <input type="button"
							onclick="javascript:PAYFEES.from_delete('${st.id }');"
							class="cz_button delete" value="删除" /></td>
					</tr>
				</table>
			</form>
		</c:forEach>
	</div>
	<!-- 数据 end -->

	<!-- 分页插件   start  -->
	<div class="pageInfo"></div>
	<script type="text/javascript">
		// 调用分页扩展插件
		$(".pageInfo").customPage(${pageInfo.pageNum}, ${pageInfo.pageSize}, ${pageInfo.total});
	</script>
	<!-- 分页插件   end  -->

	<!-- 图片上传插件 start -->
	<script language="javascript" type="text/javascript"
		src="http://static.tietuku.com/static/open/tietuku.jquery.min.js"></script>
	<script language="javascript" type="text/javascript"
		src="http://static.tietuku.com/static/open/tietuku.dialog.js?1"></script>
	<script type="text/javascript">
	
	
		var jq = jQuery.noConflict();
		function tietuku_upload(id) {
			jq.dialog.showIframeDialog(850, '',
				'<iframe frameborder="0" width="824" height="460" marginheight="0" marginwidth="0"  src="http://static.tietuku.com/upByPlugn?token=e7302f9f8984eb702b361256f4a7cc0146a7cc08:N8pIOAidldXsk9PhhkQCDWcMZXk=:eyJkZWFkbGluZSI6MTUyNDkwNDA2MiwiYWN0aW9uIjoiZ2V0IiwidWlkIjoiNjQyODgzIiwiYWlkIjoiMTQzNTg5NyIsImZyb20iOiJmaWxlIn0=&ifr=1&"></iframe>');
	
			qikoo.dialog.gettext('确定要删除此吗？', function(txt) {
				$("#imgCover" + id).val(txt);
				$("#img" + id).attr("src", txt);
			}, function() {});
		}
		var $ = jQuery.noConflict();
	</script>
	<!-- 图片上传插件 end -->
</body>
</html>
