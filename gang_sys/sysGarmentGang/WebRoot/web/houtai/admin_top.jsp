<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>头</title>
<link href="css/style.css"rel="stylesheet" type="text/css" />
<script language="JavaScript"src="js/jquery.js"></script>
<script type="text/javascript">
$(function(){	
	//顶部导航切换
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	

/**退出系统**/
function exit(){
	if(confirm("你是否要退出系统？")){
		top.location ="/admin/logout";
	} 
}

</script>

<style type="text/css">
	.body_top{background-image: url("bg/bg_banner.gif");display: table;width: 100%;height: 90px;}
	.body_top ul li{padding: 0;margin: 0;list-style-type: none;display: inline-block;display: inline;float: left;}
	.body_top ul li img{display: block;}
	.body_top ul li .span1{display:block;float: left;height: 112px;width: auto;position: absolute;bottom:0px;color: #4A4A4A;}
	.body_top ul li .span1 dl{position: fixed;bottom: 0px;}
	.body_top ul li .span1 dl dd{display: inline-block;display: inline;float: left;list-style-type: none;}
	.body_top ul li .span1 dl dd img{cursor: pointer;}
	.body_top ul li .span2{display:table;float: left;height: 67px;position: fixed;right: 0px;top:0px;color: #4A4A4A;}
	.body_top ul li .span2 ul li{margin-top: 60px;color:#FFFFFF;}
	.body_top ul li .span2 ul li a{color:#FFFFFF;}
</style>
</head>

<body style="background: url(bg/topbg.gif) repeat-x;">

	<div class="topleft">
		<a href="<%= basePath  %>web/houtai/admin_index.jsp" target="rightFrame"><img src="bg/loginlogo.png" title="系统首页" /></a>
	</div>

<!-- 	<ul class="nav">
		<li><a href="default.html" target="rightFrame" class="selected"><img src="images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="imgtable.html" target="rightFrame"><img src="images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="imglist.html"  target="rightFrame"><img src="images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="tools.html"  target="rightFrame"><img src="images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="computer.html" target="rightFrame"><img src="images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="tab.html"  target="rightFrame"><img src="images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
	</ul> -->

	<div class="body_top">
		<ul>
			<li><img src="images/002.png" style="height: 70px;margin-top: 7px;"/></li><li></li>
			<li>
			<span class="span2">
				<ul>
					<li>管理员：<c:if test="true">
					<font style="color: red;">
						${wscSession.userName }
					</font></c:if></li>
					<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					
					<li>登录地：<c:if test="true"><font style="color: red;">贵州省</font></c:if></li>
					<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					
					<li>身&nbsp;&nbsp;份：<c:if test="true"><font style="color: red;">超管</font></c:if></li>
					<li>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
					
					<li><a href="javascript:exit();">安全退出</a></li>
					<li>&nbsp;&nbsp;</li>
					<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
				</ul>
			</span>
			</li>
		</ul>	
	</div>
</body>


<script type="text/javascript"  src="js/wdatepicker.js"></script>
<style type="text/css">
/**弹出层**/
.resetpassowrd{height: auto;width: 320px;position:absolute;display:none;z-index:999;background-color: #fff; border: 1px solid #afbeca;-moz-border-radius: 3px;-webkit-border-radius: 3px;border-radius:3px;}
.resetpassowrd .title{background-color:#3D96C9;padding:8px;padding-left:5px; padding-right:5px;color:#fff;font-weight:bold;height: 15px;border-bottom:1px solid #afbeca;}
.resetpassowrd .title .img{float:right;cursor: pointer;height: 20px;width: 20px;background-image: url("bg/close_icon1e9c5d.png");}
.resetpassowrd .title .img:HOVER{background-position: -32px 0;}
.resetpassowrd .title span{float: left;}
.resetpassowrd .content{width: 310px;padding: 5px;height: auto;}
.resetpassowrd td{line-height: 20px;text-align: center;}
.resetpassowrd .tablelistc tr{background-color: #ffffff !important;}
.resetpassowrd .mmsselect{width: 100px;height: 27px;border: 1px solid #AFBECA; text-align: center;}
.resetpassowrd .tablelist{border:solid 1px #afbeca; width:100%; clear:both;table-layout:fixed;background-color: #ffffff !important;z-index: 1;}
.resetpassowrd .tablelist td{border: solid 1px #afbeca;cursor: default;word-wrap: break-word;padding:4px;} 
.resetpassowrd .tablelink{color:#056dae;} 
.resetpassowrd .checkbox_no{background: url("bg/checkbox_no.png") no-repeat; width: 14px;heght: 14px;display: inline-block;line-height: 14px;}
.resetpassowrd .checkbox_yes{background: url("bg/checkbox_yes.png") no-repeat;width: 14px;height: 14px;display: inline-block;line-height: 14px;}
.resetpassowrd .textarea_sty {background-color: #f0f3f8;height: 60px;overflow: hidden;padding-left: 7px;width: 96%;border: 1px solid #afbeca;}
</style>
<div class="mask" id="mask"></div>
<div class="dialog resetpassowrd" id="resetpassowrd">
	<div class="title"><span>位置：重置密码</span><span class="img"  onclick="close_window('resetpassowrd');"></span></div>
	<input type="hidden" value="" id="remanagerid"/>
	<input type="hidden" value="0" id="markid"/>
	
	<div class="content">
	<table style="table-layout: fixed;" class="tablelist tablelistc">
		<tr>
			<td style="width: 25%;">初始密码：</td>
			<td colspan="2" style="text-align: left;">
				<input type="password" id="repasswordid" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">确认密码：</td>
			<td colspan="2" style="text-align: left;">
				<input type="password" id="reconfirpassid" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" value="确   认" onclick="confire_resetpass('reserpassword');" class="share-button-style widths" id="button" name="button">
			</td>
		</tr>
	</table>
	</div>
</div>
</html>
