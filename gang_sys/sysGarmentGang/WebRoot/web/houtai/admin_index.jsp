<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    

<title>物业管理系统</title>
<script type="text/javascript" src="js/jquery-1.11.1.js"></script>
<script type="text/javascript"  src="js/jquery.loadmask.js"></script>
<script type="text/javascript"  src="js/base.js"></script>
<script type="text/javascript"  src="js/estatemanagelist.js"></script>
<script type="text/javascript"  src="js/userlist.js"></script>
<script type="text/javascript" src="js/wdatepicker.js"></script>
<link type="text/css" rel="stylesheet" href="css/search.css" />


<style type="text/css">
.mainindex{padding:20px; overflow:hidden;}
.welinfo{height:32px; line-height:32px; padding-bottom:8px;}
.welinfo span{float:left;}
.welinfo b{padding-left:8px;}
.welinfo a{padding-left:15px;color:#3186c8;}
.welinfo a:hover{color:#F60;}
.welinfo i{font-style:normal; padding-left:8px;}
.xline{border-bottom:solid 1px #dfe9ee; height:5px;}
.iconlist{padding-left:40px; overflow:hidden;}
.iconlist li{text-align:center; float:left; margin-right:25px; margin-top:25px;}
.iconlist li p{line-height:25px;}
.ibox{clear:both; padding-left:40px; padding-top:18px; overflow:hidden; padding-bottom:18px;}
.ibtn{background:url(bg/ibtnbg.png) repeat-x;border:solid 1px #bfcfe1; height:23px; line-height:23px; display:block; float:left; padding:0 15px; cursor:pointer;}
.ibtn img{margin-top:5px; float:left; padding-right:7px;}
.box{height:15px;}
.infolist{padding-left:40px; padding-bottom:15px;}
.infolist li{ line-height:23px; height:23px; margin-bottom:8px;}
.infolist li span{float:left; display:block; margin-right:10px;}
.info{padding-left:40px; background:url(bg/search.png) no-repeat 10px 15px; padding-top:15px; padding-bottom:20px;}
.umlist{padding-left:40px;}
.umlist li{float:left; background:url(bg/ulist.png) no-repeat 0 5px; padding-left:10px; margin-right:15px;}
</style>

<script type="text/javascript">
</script>

</head>


<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    </ul>
    </div>
    <div class="mainindex">
    <div class="welinfo">
    <span><img src="images/sun.png" alt="天气" /></span>
    <b><font style="color: red;">${wscSession.userName }</font>  欢迎您，欢迎使用物业管理系统</b>
    <a onclick="open_manager_info('1','editmanagerinfo','querymanagerinfo')">编辑信息</a>
    </div>
    
    <div class="welinfo">
    <span><img src="images/time.png" alt="时间" /></span>
    <i>您上次登录的时间：${wscSession.lastTime }</i> （不是您登录的？<a onclick="open_resetpass(1,'1','resetpassowrd')">请点这里</a>）
    </div>
    <div class="xline"></div>
    <div class="box"></div>
    <div class="welinfo">
	    <span><img src="images/ico03.png" alt="提醒" /></span>
	    <b>系统数据统计</b>
    </div>
	    <ul class="infolist">  
	        <li><span>预约上门定制订单总数：</span><font style="color: red;">0</font>&nbsp;&nbsp;单</li>
		    <li><span>管理员登陆次数：</span><font style="color: red;">0</font>&nbsp;&nbsp;次</li>
		    <li><span>公众号小程序访问总次数：</span><font style="color: red;">2</font>&nbsp;&nbsp;次</li>
	    </ul>
    
    <div class="xline"></div>
    <div class="box"></div>
    <div class="welinfo">
	    <span><img src="images/dp.png" alt="提醒" /></span>
	    <b>系统使用指南</b>
    </div>
	    <ul class="infolist">
	    	<li><span>您可以快速注册管理员账号</span><a class="ibtn" onclick="open_add_manager('addManager')">注册管理员</a></li>
	    	<li><span>您可以使用c#管理程序</span><a class="ibtn" onclick="USER_OBJ.open_import_data('1','importuserdata')">点击下载</a></li>
	    </ul>
    </div>
</body>


<script type="text/javascript"  src="js/wdatepicker.js"></script>
<script type="text/javascript"  src="js/jquery.form.js"></script>
<script type="text/javascript"  src="js/comm.js"></script>
<style type="text/css">
/**弹出层**/
.addManager{height: auto;width: 320px;position:absolute;display:none;z-index:999;background-color: #fff; border: 1px solid #afbeca;-moz-border-radius: 3px;-webkit-border-radius: 3px;border-radius:3px;}
.addManager .title{background-color:#3D96C9;padding:8px;padding-left:5px; padding-right:5px;color:#fff;font-weight:bold;height: 15px;border-bottom:1px solid #afbeca;}
.addManager .title .img{float:right;cursor: pointer;height: 20px;width: 20px;background-image: url("bg/close_icon1e9c5d.png");}
.addManager .title .img:HOVER{background-position: -32px 0;}
.addManager .title span{float: left;}
.addManager .content{width: 310px;padding: 5px;height: auto;}
.addManager td{line-height: 20px;text-align: center;}
.addManager .tablelistc tr{background-color: #ffffff !important;}
.addManager .mmsselect{width: 100px;height: 27px;border: 1px solid #AFBECA; text-align: center;}
.addManager .tablelist{border:solid 1px #afbeca; width:100%; clear:both;table-layout:fixed;background-color: #ffffff !important;z-index: 1;}
.addManager .tablelist td{border: solid 1px #afbeca;cursor: default;word-wrap: break-word;padding:4px;} 
.addManager .tablelink{color:#056dae;} 
.addManager .checkbox_no{background: url("bg/checkbox_no.png") no-repeat; width: 14px;heght: 14px;display: inline-block;line-height: 14px;}
.addManager .checkbox_yes{background: url("bg/checkbox_yes.png") no-repeat;width: 14px;height: 14px;display: inline-block;line-height: 14px;}
.addManager .textarea_sty {background-color: #f0f3f8;height: 60px;overflow: hidden;padding-left: 7px;width: 96%;border: 1px solid #afbeca;}

.detail_table{border:solid 1px #cbcbcb;clear:both;table-layout:fixed;}
.detail_table .detail_title{width:120px;text-align:right;}
.detail_table .detail_content{width:225px;text-align:left;}
.detail_table td{line-height:20px;height:35px; border: solid 1px #c7c7c7;cursor: default;word-wrap: break-word;text-align: center;word-break:break-all;padding:5px;} 
.detail_input{width: 210px;}
.detail_select{width: 212px;}
.detail_table .right_content span{float:left;}
</style>
<script type="text/javascript">
     $(function () {
           $("#btnsubmit").click(function ()
           {
	           var img=$("#previewImg").val(); 
	           if(img==null || img=="")
	           {
		           	alert("请上传图标");
		           	$("#previewImg").focus();
		           	return false;
	           }
	           $("#form2").ajaxSubmit({
		           	url: 'http://saveimg.yobangyo.com/img-server/uploadImgs?token=1522809067997', /*设置post提交到的页面*/
		            type: "post", /*设置表单以post方法提交*/
		            dataType:'json',
		           
		        	success: function (data) 
		            {
		         		var imglist=data.data.imglist;
		         		var backimgname="";
		         		if(imglist!=null && imglist.length>0)
		         		{
		         			backimgname=imglist[0];
		         		}
		         		$("#imghideid").val(backimgname);
		         		alert("上传成功!");
		            },
		            error: function (error)
		            { 
		             	alert("服务器正忙,请稍后再试!");
		     			top.location ="logout";
		            }
		       });
       });
    });
</script>
<div class="mask" id="mask"></div>
<div class="dialog addManager" id="addManager">
	<div class="title"><span>位置：添加物业管理员</span><span class="img"  onclick="close_window('addManager');"></span></div>
	<input type="hidden" value="" id="ordernumid"/>
	<input type="hidden" value="" id="imghideid"/>
	
	<div class="content">
	<table style="table-layout: fixed;" class="tablelist tablelistc">
		<tr>
			<td style="width: 25%;">账&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="managerid" value="" placeholder='请输入账号' class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">初始密码：</td>
			<td colspan="2" style="text-align: left;">
				<input type="password" id="passwordid" value="" placeholder='请输入初始密码' class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">确认密码：</td>
			<td colspan="2" style="text-align: left;">
				<input type="password" id="confirpassid" value="" placeholder='请输入确认密码' class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">省&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;份：</td>
			<td colspan="2" style="text-align: left;">
				<select id="provid" onchange="prov_to_city('provid')" class="share-input-style" style="width: 180px;text-align:center;">  
					  
					     <option value ="1">中国</option>  
					  
					     <option value ="110000">北京市</option>  
					  
					     <option value ="120000">天津市</option>  
					  
					     <option value ="130000">河北省</option>  
					  
					     <option value ="140000">山西省</option>  
					  
					     <option value ="150000">内蒙古自治区</option>  
					  
					     <option value ="210000">辽宁省</option>  
					  
					     <option value ="220000">吉林省</option>  
					  
					     <option value ="230000">黑龙江省</option>  
					  
					     <option value ="310000">上海市</option>  
					  
					     <option value ="320000">江苏省</option>  
					  
					     <option value ="330000">浙江省</option>  
					  
					     <option value ="340000">安徽省</option>  
					  
					     <option value ="350000">福建省</option>  
					  
					     <option value ="360000">江西省</option>  
					  
					     <option value ="370000">山东省</option>  
					  
					     <option value ="410000">河南省</option>  
					  
					     <option value ="420000">湖北省</option>  
					  
					     <option value ="430000">湖南省</option>  
					  
					     <option value ="440000">广东省</option>  
					  
					     <option value ="450000">广西壮族自治区</option>  
					  
					     <option value ="460000">海南省</option>  
					  
					     <option value ="500000">重庆市</option>  
					  
					     <option value ="510000">四川省</option>  
					  
					     <option value ="520000">贵州省</option>  
					  
					     <option value ="530000">云南省</option>  
					  
					     <option value ="540000">西藏自治区</option>  
					  
					     <option value ="610000">陕西省</option>  
					  
					     <option value ="620000">甘肃省</option>  
					  
					     <option value ="630000">青海省</option>  
					  
					     <option value ="640000">宁夏回族自治区</option>  
					  
					     <option value ="650000">新疆维吾尔自治区</option>  
					  
					     <option value ="990000">新疆建设兵团</option>  
					  
				</select>
			</td>
		</tr>
		<tr id="cittrid">
		</tr>
		<tr id=cunttrid>
		</tr>
		<tr>
			<td style="width: 25%;">物业公司：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="estatecompany" value="" placeholder='请输入物业公司' class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">物业封面：</td>
			<td colspan="2" style="text-align: center;">
			    <form enctype="multipart/form-data" id="form2">
				    <div id="preview">
					  <img id="imghead" border="0" src="images/photo_icon.png" width="90" height="90" onclick="$('#previewImg').click();">
				    </div>         
				    <input type="file"  id="previewImg" name="previewImg" onchange="previewImage(this)" style="display: none;"><br>
			        <input type="button" id="btnsubmit" name="button" value="上  传" class="share-button-style widths">
			    </form>
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">权&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;限：</td>
			<td colspan="2" style="text-align:center;">
			   
					
			   
					
						<span data-text="业主管理"><input class="right_checkbox" name="rightid" value="2" type="checkbox" />业主管理&nbsp;</span>
					
			   
					
						<span data-text="缴费管理"><input class="right_checkbox" name="rightid" value="3" type="checkbox" />缴费管理&nbsp;</span>
					
			   
					
			   
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" value="确认填加" onclick="confir_add_manager('addmanagerinfo');" class="share-button-style widths" id="button" name="button">
			</td>
		</tr>
	</table>
	</div>
</div>


<script type="text/javascript"  src="js/wdatepicker.js"></script>
<style type="text/css">
/**弹出层**/
.editmanagerinfo{height: auto;width: 320px;position:absolute;display:none;z-index:999;background-color: #fff; border: 1px solid #afbeca;-moz-border-radius: 3px;-webkit-border-radius: 3px;border-radius:3px;}
.editmanagerinfo .title{background-color:#3D96C9;padding:8px;padding-left:5px; padding-right:5px;color:#fff;font-weight:bold;height: 15px;border-bottom:1px solid #afbeca;}
.editmanagerinfo .title .img{float:right;cursor: pointer;height: 20px;width: 20px;background-image: url("bg/close_icon1e9c5d.png");}
.editmanagerinfo .title .img:HOVER{background-position: -32px 0;}
.editmanagerinfo .title span{float: left;}
.editmanagerinfo .content{width: 310px;padding: 5px;height: auto;}
.editmanagerinfo td{line-height: 20px;text-align: center;}
.editmanagerinfo .tablelistc tr{background-color: #ffffff !important;}
.editmanagerinfo .mmsselect{width: 100px;height: 27px;border: 1px solid #AFBECA; text-align: center;}
.editmanagerinfo .tablelist{border:solid 1px #afbeca; width:100%; clear:both;table-layout:fixed;background-color: #ffffff !important;z-index: 1;}
.editmanagerinfo .tablelist td{border: solid 1px #afbeca;cursor: default;word-wrap: break-word;padding:4px;} 
.editmanagerinfo .tablelink{color:#056dae;} 
.editmanagerinfo .checkbox_no{background: url("bg/checkbox_no.png") no-repeat; width: 14px;heght: 14px;display: inline-block;line-height: 14px;}
.editmanagerinfo .checkbox_yes{background: url("bg/checkbox_yes.png") no-repeat;width: 14px;height: 14px;display: inline-block;line-height: 14px;}
.editmanagerinfo .textarea_sty {background-color: #f0f3f8;height: 60px;overflow: hidden;padding-left: 7px;width: 96%;border: 1px solid #afbeca;}
</style>
<div class="mask" id="mask"></div>
<div class="dialog editmanagerinfo" id="editmanagerinfo">
	<div class="title"><span>位置：信息编辑</span><span class="img"  onclick="close_window('editmanagerinfo');"></span></div>
	<input type="hidden" value="" id="editmanagerid"/>
	
	<div class="content">
	<table style="table-layout: fixed;" class="tablelist tablelistc">
		<tr>
			<td style="width: 25%;">真实姓名：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="editrealname" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">联系方式：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="editphone" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">小区名称：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="editestatearea" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">物业公司：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="editestatecompany" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
			<td style="width: 25%;">居住地址：</td>
			<td colspan="2" style="text-align: left;">
				<input type="text" id="editliveaddress" value="" class="share-input-style" style="width:180px;">
			</td>
		</tr>
		<tr>
		</tr>
		<tr>
			<td colspan="3">
				<input type="button" value="确   认" onclick="submit_manager_info('editbasicmanageinfo');" class="share-button-style widths" id="button" name="button">
			</td>
		</tr>
	</table>
	</div>
</div>


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


<html>
<head>
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>  
    <script type="text/javascript" src="js/jquery.form.js"></script>   
    <title>导入</title>  
    <style type="text/css">
	/**弹出层**/
	.importuserdata{height: auto;width: 320px;position:absolute;display:none;z-index:999;background-color: #fff; border: 1px solid #afbeca;-moz-border-radius: 3px;-webkit-border-radius: 3px;border-radius:3px;}
	.importuserdata .title{background-color:#3D96C9;padding:8px;padding-left:5px; padding-right:5px;color:#fff;font-weight:bold;height: 15px;border-bottom:1px solid #afbeca;}
	.importuserdata .title .img{float:right;cursor: pointer;height: 20px;width: 20px;background-image: url("bg/close_icon1e9c5d.png");}
	.importuserdata .title .img:HOVER{background-position: -32px 0;}
	.importuserdata .title span{float: left;}
	.importuserdata .content{width: 310px;padding: 5px;height: auto;}
	.importuserdata td{line-height: 20px;text-align: center;}
	.importuserdata .tablelistc tr{background-color: #ffffff !important;}
	.importuserdata .mmsselect{width: 100px;height: 27px;border: 1px solid #AFBECA; text-align: center;}
	.importuserdata .tablelist{border:solid 1px #afbeca; width:100%; clear:both;table-layout:fixed;background-color: #ffffff !important;z-index: 1;}
	.importuserdata .tablelist td{border: solid 1px #afbeca;cursor: default;word-wrap: break-word;padding:4px;} 
	.importuserdata .tablelink{color:#056dae;} 
	.importuserdata .checkbox_no{background: url("bg/checkbox_no.png") no-repeat; width: 14px;heght: 14px;display: inline-block;line-height: 14px;}
	.importuserdata .checkbox_yes{background: url("bg/checkbox_yes.png") no-repeat;width: 14px;height: 14px;display: inline-block;line-height: 14px;}
	.importuserdata .textarea_sty {background-color: #f0f3f8;height: 60px;overflow: hidden;padding-left: 7px;width: 96%;border: 1px solid #afbeca;}
	</style>
</head>
<body>
 <div class="mask" id="mask"></div>
  <div class="dialog importuserdata" id="importuserdata">
	<div class="title"><span>位置：导入业主</span><span class="img"  onclick="USER_OBJ.close_window('importuserdata');"></span></div>
	<input type="hidden" value="" id="excelmanagerid"/>
	<input type="hidden" value="0" id="markid"/>
	
	<div class="content">
	  <form action="/admin/uploadexcel" method="post" enctype="multipart/form-data" id="form2" onsubmit="return check();">
		<table style="table-layout: fixed;" class="tablelist tablelistc">
			<tr>
				<td colspan="3" style="text-align: center;">
				    <input id="upfile" type="file" name="upfile">
				</td>
			</tr>
			<tr>
				<td colspan="3" style="text-align: center;">
				    <a href="downloadexcel">下载Excel导入模版</a>
				</td>
			</tr>
			<tr>
				<td colspan="3">
					<input type="button" name="btn" id="btn" class="share-button-style" value="导入Excel" />
				</td>
			</tr>
		</table>
	   </form>
	</div>
	
	<script type="text/javascript">
	
		//ajax 方式上传文件操作  
	    $(document).ready(function(){  
	       $('#btn').click(function(){ 
	    	   var managerid=$("#excelmanagerid").val();  
	    	   //alert("管理员："+managerid);
	           if(checkData()){  
	               $('#form2').ajaxSubmit({    
	                   url:'ajaxUploadexcel?managerid='+managerid,  
	                   dataType: 'text',  
	                   success: resutlMsg,  
	                   error: errorMsg  
	               });   
	               function resutlMsg(msg){  
	                   alert(msg);     
	                   $("#upfile").val("");  
	                   USER_OBJ.close_window('importuserdata');
	               }  
	               function errorMsg(){   
	                   alert("服务正忙！");      
	               }  
	           }  
	       });  
	    });  
	
		//JS校验form表单信息  
	    function checkData(){  
	       var fileDir = $("#upfile").val();  
	       var suffix = fileDir.substr(fileDir.lastIndexOf("."));  
	       if("" == fileDir){  
	           alert("选择需要导入的Excel文件！");  
	           return false;  
	       }  
	       if(".xls" != suffix && ".xlsx" != suffix ){  
	           alert("选择Excel格式的文件导入！");  
	           return false;  
	       }  
	       return true;  
	    }  
	</script>
 </div>
</body>
</html>
</html>
