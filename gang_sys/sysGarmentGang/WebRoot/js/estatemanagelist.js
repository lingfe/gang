/**
 * 搜索管理员列表
 */
function manager_query(url){
     $("#page").val(0);  
     $("#startime").val($("#time1").val());
	 $("#endtime").val($("#time2").val());
	 // 执行页面加载提示
	 $(".bodyid").mask("正在加载中...");	
	 $("#formid").attr("action",url); 
	 $("#formid").submit();
}

//地区查询
function area_query(provinceid,url){
    $("#page").val(0);  
    $("#provinceid").val(provinceid);
    $("#startime").val($("#time1").val());
	$("#endtime").val($("#time2").val());
	// 执行页面加载提示
	$(".bodyid").mask("正在加载中...");	
	$("#formid").attr("action",url); 
	$("#formid").submit();
}

//市区查询
function city_query(cityid,url){
    $("#page").val(0);  
    $("#cityid").val(cityid);
    $("#startime").val($("#time1").val());
	$("#endtime").val($("#time2").val());
	// 执行页面加载提示
	$(".bodyid").mask("正在加载中...");	
	$("#formid").attr("action",url); 
	$("#formid").submit();
}

//根据管理员ID清空业主
function del_allowner_info(managerid,url){
	if(confirm("你确定删除所有业主？")){
	   	$.ajax({
		   	type :"post",
    	   	url :""+url+"?managerid="+managerid,
		   	sync: true,
		   	dataType : "json",
		   	contentType:"application/json;charset=UTF-8;",
		   	beforeSend: function (XMLHttpRequest) {
	          	XMLHttpRequest.setRequestHeader("Accept", "application/json");
	       	},
		   	error : function(err) {
		   		alert("服务器正忙,请稍后再试!");
				top.location ="logout";
		   	},
		   	success : function(result){
			  	var status=result.status;
			  	var msg=result.msg;
			  	var data=result.data;
			  	alert(msg);
				if(status==0){
					$(".bodyid").mask("正在加载中..."); 			 
	                $("#formid").submit(); 
			  	}
		   	}
		});
	}
}

//禁用、启用管理员
function closeOrstart(managerid,enabled,url)
{
	var value='';
	if(enabled=='1') value='启用';
	if(enabled=='0') value='禁用';
	if(confirm("你确定要"+value+"？")){
		$.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?managerid="+managerid+"&enabled="+enabled,// 发送请求的地址
			async : true, // 默认为true为异步,false为同步
			dataType : "json",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			beforeSend: function (XMLHttpRequest) {
	            XMLHttpRequest.setRequestHeader("Accept", "application/json");
	        },
			error : function(err) {// 如果确定能正确运行,可不写
				alert("服务器正忙,请稍后再试!");
				top.location ="logout";
			},
			success : function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg);
				$(".bodyid").mask("正在加载中..."); 			 
	            $("#formid").submit(); 
			}
		 });
	}
}

/**
 * 打开添加物业管理员界面
 */
function open_add_manager(param){
  	var GSCP = $("#"+param);
  	var mask = $("#"+param);
  	mask.css("display","block");
  	GSCP.css("display","block");
  	showDialog(param);
  	$("#managerid").focus();
}

/**
 * 打开重置密码界面
 */
function open_resetpass(mark,managerid,param){
	$("#remanagerid").val(managerid); 
	if(mark>0)
	{
		$("#markid").val(mark);
	}
	var GSCP = $("#"+param);
  	var mask = $("#"+param);
  	mask.css("display","block");
  	GSCP.css("display","block");
  	showDialog(param);
  	$("#repasswordid").focus();
}

//打开管理员信息编辑界面
function open_manager_info(managerid,param,url){
	$("#editmanagerid").val(managerid); 
	$.ajax({
		type : "post",// 指定是post还是get encodeURI()
		url : ""+url+"?managerid="+managerid,// 发送请求的地址
		async : true, // 默认为true为异步,false为同步
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("Accept", "application/json");
        },
		error : function(err) {// 如果确定能正确运行,可不写
			alert("服务器正忙,请稍后再试!");
			top.location ="logout";
		},
		success : function(result){
			var status=result.status;
			var msg=result.msg;
			var managerinfo=result.data.managerinfo;
		    
			$("#editrealname").val(managerinfo.realname); 
			$("#editphone").val(managerinfo.phone); 
			$("#editestatearea").val(managerinfo.estatearea); 
			$("#editestatecompany").val(managerinfo.estatecompany); 
			$("#editliveaddress").val(managerinfo.liveaddress); 
		}
	 });
	
	var GSCP = $("#"+param);
  	var mask = $("#"+param);
  	mask.css("display","block");
  	GSCP.css("display","block");
  	showDialog(param);
}

//修改管理员信息
function submit_manager_info(url){
	var pattern = /^1[34578]\d{9}$/;
	var managerid=$("#editmanagerid").val(); 
	var realname=$("#editrealname").val(); 
	var estatearea=$("#editestatearea").val(); 
	var phone=$("#editphone").val(); 
	var liveaddress=$("#editliveaddress").val(); 
	var estatecompany=$("#editestatecompany").val(); 
	
	//验证手机号是否合法
	if(!pattern.test(phone))
	{
		alert("请输入合法手机号");
		return false;
	}
	if(confirm("你确定要修改？")){
		$.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?managerid="+managerid
			+"&realname="+encodeURI(encodeURI(realname))
			+"&estatearea="+encodeURI(encodeURI(estatearea))
			+"&phone="+encodeURI(encodeURI(phone))
			+"&liveaddress="+encodeURI(encodeURI(liveaddress))
			+"&estatecompany="+encodeURI(encodeURI(estatecompany))
			,// 发送请求的地址
			async : true, // 默认为true为异步,false为同步
			dataType : "json",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			beforeSend: function (XMLHttpRequest) {
	            XMLHttpRequest.setRequestHeader("Accept", "application/json");
	        },
			error : function(err) {// 如果确定能正确运行,可不写
				alert("服务器正忙,请稍后再试!");
				top.location ="logout";
			},
			success : function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg);
				
				//关闭
				close_window('editmanagerinfo');
				//$(".bodyid").mask("正在加载中..."); 			 
	            //$("#formid").submit(); 
			}
	    });
	}
}

//确认重置
function confire_resetpass(url){
	var markid=$("#markid").val();
	var managerid=$("#remanagerid").val();
	var repassword=$("#repasswordid").val();
	var reconfirpass=$("#reconfirpassid").val();
	if(repassword==null || repassword=="")
    {
    	alert("请输入密码");
    	$("#repasswordid").focus();
    	return false;
    }
    if(repassword.length<8)
    {
    	alert("密码至少8位");
    	$("#repasswordid").focus();
    	return false;
    }
    if(repassword.length>12)
    {
    	alert("密码最多12位");
    	$("#repasswordid").focus();
    	return false;
    }
    if(reconfirpass==null || reconfirpass=="")
    {
    	alert("请输入确认密码");
    	$("#reconfirpassid").focus();
    	return false;
    }
    if(repassword!=reconfirpass)
	{
    	alert("确认密码错误");
    	$("#reconfirpassid").focus();
    	return false;
	}
    if(confirm("你确定要重置？")){
		$.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?managerid="+managerid+"&password="+repassword,// 发送请求的地址
			async : true, // 默认为true为异步,false为同步
			dataType : "json",
			contentType:'application/x-www-form-urlencoded; charset=UTF-8',
			beforeSend: function (XMLHttpRequest) {
	            XMLHttpRequest.setRequestHeader("Accept", "application/json");
	        },
			error : function(err) {// 如果确定能正确运行,可不写
				alert("服务器正忙,请稍后再试!");
				top.location ="logout";
			},
			success : function(result){
				var status=result.status;
				var msg=result.msg;
				alert(msg);
				close_window('resetpassowrd');
				//退出登录
				if(markid>0)
				{
					top.location ="logout";
				}
			}
	  });
	}
}

// 省份下拉联动市州值
function prov_to_city(praykey)
{
	var vlaue=jQuery("#"+praykey+" option:selected").val();
	if(vlaue==1)
	{
	    // alert('请选择省份');
		$("#cittrid").html('');
		$("#cunttrid").html('');
	}else
	{
		$("#cunttrid").html('');
	   // 查询市州列表
	   $.ajax({
			type : "post",// 指定是post还是get
							// encodeURI(managername="+managername+")
    		url : "querycitOrCuntlist?pid="+vlaue,// 发送请求的地址
			async : true, // 默认为true为异步,false为同步
			dataType : "json",
			contentType:"application/json;charset=UTF-8;",
			beforeSend: function (XMLHttpRequest) {
	            XMLHttpRequest.setRequestHeader("Accept", "application/json");
	        },
			error : function(err) {// 如果确定能正确运行,可不写
				alert("服务器正忙,请稍后再试!");
				top.location ="logout";
			},
			success : function(result){
				var status=result.status;
				var msg=result.msg;
				var citOrCuntlist=result.data.citOrCuntlist;
				var html='';
				if(citOrCuntlist!=null && citOrCuntlist.length>0)
				{
					html+='<td style="width: 25%;">市&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;州：</td>';
					html+='<td colspan="2" style="text-align: left;">';
					html+='<select id="citid" onchange="city_to_cunty(\'citid\')" class="share-input-style" style="width: 180px;text-align:center;"> ';
					html+='<option value ="-1">---请选择市州---</option>';
					for(var i=0;i<citOrCuntlist.length;i++)
					{
						html+='<option value ='+citOrCuntlist[i].id+'>'+citOrCuntlist[i].name+'</option>';
					}
					html+='</select>';
					html+='</td>';
					
					$("#cittrid").html(html);
				}
			}
	   });
	}
}

// 市州下拉联动县级值
function city_to_cunty(praykey)
{
	var vlaue=jQuery("#"+praykey+" option:selected").val();
	if(vlaue>0)
	{
		// 查询县级列表
	    $.ajax({
			type : "post",// 指定是post还是get
							// encodeURI(managername="+managername+")
    		url : "querycitOrCuntlist?pid="+vlaue,// 发送请求的地址
			async : true, // 默认为true为异步,false为同步
			dataType : "json",
			contentType:"application/json;charset=UTF-8;",
			beforeSend: function (XMLHttpRequest) {
	            XMLHttpRequest.setRequestHeader("Accept", "application/json");
	        },
			error : function(err) {// 如果确定能正确运行,可不写
				alert("服务器正忙,请稍后再试!");
				top.location ="logout";
			},
			success : function(result){
				var status=result.status;
				var msg=result.msg;
				var citOrCuntlist=result.data.citOrCuntlist;
				var html='';
				if(citOrCuntlist!=null && citOrCuntlist.length>0)
				{
					html+='<td style="width: 25%;">县&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;级：</td>';
					html+='<td colspan="2" style="text-align: left;">';
					html+='<select id="cuntid" class="share-input-style" style="width:180px;text-align:center;">';
					html+='<option value ="-1">---请选择县级---</option>';
					for(var i=0;i<citOrCuntlist.length;i++)
					{
						html+='<option value ='+citOrCuntlist[i].id+'>'+citOrCuntlist[i].name+'</option>';
					}
					html+='</select>';
					html+='</td>';
					$("#cunttrid").html(html);
				}
			}
	   });
	}
}

// 确认添加
function confir_add_manager(url)
{
	var managername=$("#managerid").val();
	var password=$("#passwordid").val();
	var confirpassword=$("#confirpassid").val();
	var provid=jQuery("#provid option:selected").val();
	var cityid=jQuery("#citid option:selected").val();
	var icon=$("#imghideid").val(); 
	var estatecompany=$("#estatecompany").val(); 
	var countid='';
	
	//获取市区html 
	var cunttext=$("#cunttrid").text();
	if(cunttext==''||cunttext==null)
	{
		countid=100;
	}else
	{
		countid=jQuery("#cuntid option:selected").val();
	}
    var rightids=new Array();  
    $('input[name="rightid"]:checked').each(function(){  
    	rightids.push($(this).val());// 向数组中添加元素
    });  
    if(managername==null || managername=="")
    {
    	alert("请输入账号");
    	$("#managerid").focus();
    	return false;
    }
    if(managername.length<2)
    {
    	alert("账号至少2位");
    	$("#managerid").focus();
    	return false;
    }
    if(managername.length>12)
    {
    	alert("账号最多12位");
    	$("#managerid").focus();
    	return false;
    }
    if(password==null || password=="")
    {
    	alert("请输入密码");
    	$("#passwordid").focus();
    	return false;
    }
    if(password.length<8)
    {
    	alert("密码至少8位");
    	$("#passwordid").focus();
    	return false;
    }
    if(password.length>12)
    {
    	alert("密码最多12位");
    	$("#passwordid").focus();
    	return false;
    }
    if(confirpassword==null || confirpassword=="")
    {
    	alert("请输入确认密码");
    	$("#confirpassid").focus();
    	return false;
    }
    if(password!=confirpassword)
	{
    	alert("确认密码错误");
    	$("#confirpassid").focus();
    	return false;
	}
    if(provid==1)
	{
		alert("请选择地区");
		return false;
	} 
	if(cityid==-1)
	{
		alert("请选择州市");
		return false;
	}
	if(countid==-1)
	{
		alert("请选择县级");
		return false;
	}
	if(estatecompany==null || estatecompany=="")
    {
    	alert("请填写物业公司");
    	$("#estatecompany").focus();
    	return false;
    }
	if(icon==null || icon=="")
    {
    	alert("请上传图标");
    	$("#imghideid").focus();
    	return false;
    }
    if(rightids.length<1)
    {
    	alert("请勾选权限");
    	return false;
    }
   
	$.ajax({
		type : "post",// 指定是post还是get encodeURI()
		url : ""+url+"?managername="+encodeURI(encodeURI(managername))
		+"&password="+password
		+"&provid="+provid
		+"&cityid="+cityid
		+"&countid="+countid
		+"&rightids="+rightids
		+"&icon="+icon
		+"&estatecompany="+encodeURI(encodeURI(estatecompany)),// 发送请求的地址
		async : true, // 默认为true为异步,false为同步
		dataType : "json",
		contentType:'application/x-www-form-urlencoded; charset=UTF-8',
		beforeSend: function (XMLHttpRequest) {
            XMLHttpRequest.setRequestHeader("Accept", "application/json");
        },
		error : function(err) {// 如果确定能正确运行,可不写
			alert("服务器正忙,请稍后再试!");
			top.location ="logout";
		},
		success : function(result){
			var status=result.status;
			var msg=result.msg;
			alert(msg);
			
			close_window('addManager');
		}
	 }); 
  }

/**
 * 通用关闭窗口
 */
function close_window(param){
    var GSCP = $("#"+param);
    var mask = $("#mask"); 
    mask.css("display","none"); 
    GSCP.css("display","none");  
    $(".bodyid").mask("正在加载中..."); 			 
	$("#formid").submit(); 
}