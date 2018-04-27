<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  	<head>
    	<base href="<%=basePath%>">
    	<title>信息管理系统</title>
  	</head>
	<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
	  <frame src="<%=basePath%>web/houtai/admin_top.jsp" name="topFrame" scrolling="No" noresize="noresize" id="topFrame" title="topFrame" />
	  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
	    <frame src="<%=basePath%>web/houtai/admin_left.jsp" name="leftFrame" scrolling="No" noresize="noresize" id="leftFrame" title="leftFrame" />
	    <frame src="<%=basePath%>web/houtai/admin_index.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
	  </frameset>
	</frameset>
	<noframes>
	<body>
	</body>
	</noframes>
</html>
