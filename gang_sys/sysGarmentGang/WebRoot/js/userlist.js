var USER_OBJ={
	/**
	 * 搜索会员列表
	 */
	user_query:function(url){
	     $("#page").val(0);  
		 //执行页面加载提示 
		 $(".bodyid").mask("正在加载中...");	
		 $("#formid").attr("action",url); 
		 $("#formid").submit();
	},
	//删除所有业主
	/*del_allowner_info:function(url){
		if(confirm("你确定删除所有业主？")){
		   	$.ajax({
			   	type :"post",
	    	   	url : url,
			   	sync : true,
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
	},*/
	
	//批量删除业主
	batch_delete_owner:function(url){
		var singlgid = $("[name=checkboxid]:checkbox");
		var count = 0;
		var userids='';
		for(var i = 0;i<singlgid.length;i++){
			if(singlgid[i].checked==true){
				if(userids=='')
				{
					userids+=singlgid[i].value;
				}else
				{
					userids+=','+singlgid[i].value;
				}
				count+=1;
			} 
		}
		if(count==0){
			alert("请选择要删除的业主");
			return false;  
		}else{
			if(confirm("你确认删除吗？")){
				$.ajax({
				   	type : "post",
		    	   	url : ""+url+"?userids="+userids,
				   	sync : true,
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
					  	
						if(status==0){
							alert(msg);
							$(".bodyid").mask("正在加载中..."); 			 
			                $("#formid").submit(); 
					  	}
				   	}
				});
			}
		}
    },
	
	
	//批量导入业主数据
	open_import_data:function(managerid,param)
	{
		$("#excelmanagerid").val(managerid);
		var GSCP = $("#"+param);
	  	var mask = $("#"+param);
	  	mask.css("display","block");
	  	GSCP.css("display","block");
	  	showDialog(param);
	},
	
	//打开添加业主窗口
	open_add_user:function(managerid,param){
		$("#managerid").val(managerid);
	  	var GSCP = $("#"+param);
	  	var mask = $("#"+param);
	  	mask.css("display","block");
	  	GSCP.css("display","block");
	  	showDialog(param);
	  	$("#addusername").focus();
	},
	
	//添加业主信息
	confir_submit_user:function(url)
	{
		var patternphone = /^1[34578]\d{9}$/;//验证手机号
		var patternidcard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;//验证身份证
		var managerid=$("#managerid").val();
		var username=$("#addusername").val();
		var phone=$("#addphone").val();
		var idcard=$("#addidcard").val();
		var type=$("#addtypeid").val();
		var paytype=$("#addpaytypeid").val();
		var estateacreage=$("#addestateacreage").val();
		var unitestatefee=$("#addunitestatefee").val();
		var liveaddress=$("#addliveaddress").val();
		var typevalue='房屋';
		var paytypevalue='月付';
		
		//物业类型(type:1.房屋 2.车位)
		if(type=='1') typevalue="房屋";
		if(type=='2') typevalue="车位";
		
		//缴费类型(月付、季付、年付)
		if(paytype=='1') paytypevalue="月付";
		if(paytype=='3') paytypevalue="季付";
		if(paytype=='12') paytypevalue="年付";
		
		if(username=='' || username==null)
		{
			alert("请输入户名");
	    	$("#addusername").focus();
	    	return false;
		}
		if(phone=='' || phone==null)
		{
			alert("请输入电话");
	    	$("#addphone").focus();
	    	return false;
		}
		//验证手机号是否合法
		if(!patternphone.test(phone))
		{
			alert("请输入合法电话");
			$("#addphone").focus();
			return false;
		}
		if(idcard=='' || idcard==null)
		{
			alert("请输入身份证");
	    	$("#addidcard").focus();
	    	return false;
		}
		//验证身份证是否合法
		if(!patternidcard.test(idcard))
		{
			alert("请输入合法身份证");
			$("#addidcard").focus();
			return false;
		}
		if(estateacreage=='' || estateacreage==null)
		{
			alert("请输入有效面积");
	    	$("#addestateacreage").focus();
	    	return false;
		}
		//isNaN()函数，如果传入的参数是数字返回false,否则返回true
		if(isNaN(estateacreage))
		{
			alert("面积只能为数字");
	    	$("#addestateacreage").focus();
	    	return false;
		}
		
		if(unitestatefee=='' || unitestatefee==null)
		{
			alert("请输入单价");
	    	$("#addunitestatefee").focus();
	    	return false;
		}
		if(isNaN(unitestatefee))
		{
			alert("单价无效");
	    	$("#addunitestatefee").focus();
	    	return false;
		}
		if(liveaddress=='' || liveaddress==null)
		{
			alert("请输入有效地址");
	    	$("#addliveaddress").focus();
	    	return false;
		}
		$.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?managerid="+managerid
			+"&username="+encodeURI(encodeURI(username))
			+"&phone="+phone
			+"&idcard="+idcard
			+"&type="+type
			+"&paytype="+paytype
			+"&estateacreage="+estateacreage
			+"&unitestatefee="+unitestatefee
			+"&liveaddress="+encodeURI(encodeURI(liveaddress))
			+"&typevalue="+encodeURI(encodeURI(typevalue)) 
			+"&paytypevalue="+encodeURI(encodeURI(paytypevalue)) 
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
				USER_OBJ.close_window('adduserinfo');
			}
		 });
	},
	
	//删除业主信息
	del_owner_info:function(userid,url){
		if(confirm("你确定删除？")){
		   	$.ajax({
			   	type : "post",
	    	   	url : ""+url+"?userid="+userid,
			   	sync : true,
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
				  	
					if(status==0){
						alert(msg);
						$(".bodyid").mask("正在加载中..."); 			 
		                $("#formid").submit(); 
				  	}
			   	}
			});
		}
	},
	
	/**
	 * 启用、禁用
	 */
	user_operate:function(userid,param,url){
		var value="";
		if(param=='1'){value="启用"}
		if(param=='0'){value="禁用"}
		
		if(confirm("你确定"+value+"？")){
		   	$.ajax({
			   	type : "post",
	    	   	url : ""+url+"?userid="+userid+"&status="+param,
			   	sync : true,
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
	},
	
	//打开业主信息编辑界面
	open_user_info:function(userid,param,url){
		$("#edituserid").val(userid); 
		$.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?userid="+userid,// 发送请求的地址
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
				var userinfo=result.data.userinfo;
				var typehtml='';
				var paytypehtml='';
				
				//物业类型 
				typehtml+='<td style="width: 25%;">物业类型 ：</td>';
				typehtml+='<td colspan="2" style="text-align: left;">';
				typehtml+='<select id="etypeid" class="share-input-style" style="width: 193px;text-align:center;">';
				//物业类型(type:1.房屋 2.车位)
				if(userinfo.type=='1')
				{
					typehtml+='<option value ='+userinfo.type+' selected="selected">房屋</option>';
					typehtml+='<option value ="2">车位</option>';
				}
				if(userinfo.type=='2')
				{
					typehtml+='<option value ='+userinfo.type+' selected="selected">车位</option>';
					typehtml+='<option value ="1">房屋</option>';
				}
				typehtml+='</select>';
				typehtml+='</td>';
				$("#estatetypeid").html(typehtml);
				
				//付款类型
				paytypehtml+='<td style="width: 25%;">缴费类型：</td>';
				paytypehtml+='<td colspan="2" style="text-align: left;">';
				paytypehtml+='<select id="epaytypeid" class="share-input-style" style="width: 193px;text-align:center;"> ';
				//缴费类型(按月1，按季度3，半年6，全年12) 
				if(userinfo.paytype=='1')
				{
					paytypehtml+='<option value ='+userinfo.paytype+' selected="selected">月付</option>';
					paytypehtml+='<option value ="3">季付</option>';
					/*paytypehtml+='<option value ="6">半付</option>';*/
					paytypehtml+='<option value ="12">年付</option>';
				}
				if(userinfo.paytype=='3')
				{
					paytypehtml+='<option value ="1">月付</option>';
					paytypehtml+='<option value ='+userinfo.paytype+' selected="selected">季付</option>';
					/*paytypehtml+='<option value ="6">半付</option>';*/
					paytypehtml+='<option value ="12">年付</option>';
				}
				/*if(userinfo.paytype=='6')
				{
					paytypehtml+='<option value ='+userinfo.paytype+' selected="selected">半年缴</option>';
					paytypehtml+='<option value ="1">月缴费</option>';
					paytypehtml+='<option value ="3">季缴费</option>';
					paytypehtml+='<option value ="12">全年缴</option>';
				}*/
				if(userinfo.paytype=='12')
				{
					paytypehtml+='<option value ="1">月付</option>';
					paytypehtml+='<option value ="3">季付</option>';
					paytypehtml+='<option value ='+userinfo.paytype+' selected="selected">年付</option>';
					/*paytypehtml+='<option value ="6">半年缴</option>';*/
				}
				paytypehtml+='</select>';
				paytypehtml+='</td>';
				$("#estatepaytypeid").html(paytypehtml);
				
				$("#editusername").val(userinfo.username); 
				$("#editphone").val(userinfo.phone); 
				$("#editidcard").val(userinfo.idcard); 
				$("#editestateacreage").val(userinfo.estateacreage); 
				$("#editunitestatefee").val(userinfo.unitestatefee); 
				$("#editliveaddress").val(userinfo.liveaddress); 
			}
		 });
		
		var GSCP = $("#"+param);
	  	var mask = $("#"+param);
	  	mask.css("display","block");
	  	GSCP.css("display","block");
	  	showDialog(param);
	},
	
	//确认修改业主信息
	comfir_user_info:function(url)
	{
		var patternphone = /^1[34578]\d{9}$/;//验证手机号
		var patternidcard = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;//验证身份证
		var userid=$("#edituserid").val();
		var username=$("#editusername").val();
		var phone=$("#editphone").val();
		var idcard=$("#editidcard").val();
		var estateacreage=$("#editestateacreage").val();
		var unitestatefee=$("#editunitestatefee").val();
		var liveaddress=$("#editliveaddress").val();
		var type=jQuery("#etypeid option:selected").val();//物业类型
		var paytype=jQuery("#epaytypeid option:selected").val();//付款类型
		
		var typevalue='房屋';
		var paytypevalue='月付';
		
		//物业类型(type:1.房屋 2.车位)
		if(type=='1') typevalue="房屋";
		if(type=='2') typevalue="车位";
		
		//缴费类型(月付、季付、年付)
		if(paytype=='1') paytypevalue="月付";
		if(paytype=='3') paytypevalue="季付";
		if(paytype=='12') paytypevalue="年付";
		
		if(username=='' || username==null)
		{
			alert("请输入户名");
	    	$("#editusername").focus();
	    	return false;
		}
		if(phone=='' || phone==null)
		{
			alert("请输入电话");
	    	$("#editphone").focus();
	    	return false;
		}
		//验证手机号是否合法
		if(!patternphone.test(phone))
		{
			alert("请输入合法电话");
			$("#editphone").focus();
			return false;
		}
		if(idcard=='' || idcard==null)
		{
			alert("请输入身份证");
	    	$("#editidcard").focus();
	    	return false;
		}
		//验证身份证是否合法
		if(!patternidcard.test(idcard))
		{
			alert("请输入合法身份证");
			$("#editidcard").focus();
			return false;
		}
		if(estateacreage=='' || estateacreage==null)
		{
			alert("请输入有效面积");
	    	$("#editestateacreage").focus();
	    	return false;
		}
		//isNaN()函数，如果传入的参数是数字返回false,否则返回true
		if(isNaN(estateacreage))
		{
			alert("面积只能为数字");
	    	$("#editestateacreage").focus();
	    	return false;
		}
		if(unitestatefee=='' || unitestatefee==null)
		{
			alert("请输入单价");
	    	$("#editunitestatefee").focus();
	    	return false;
		}
		if(isNaN(unitestatefee))
		{
			alert("单价无效");
	    	$("#editunitestatefee").focus();
	    	return false;
		}
		$.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?userid="+userid
			+"&username="+encodeURI(encodeURI(username))
			+"&phone="+phone
			+"&idcard="+idcard
			+"&estateacreage="+estateacreage
			+"&unitestatefee="+unitestatefee
			+"&liveaddress="+encodeURI(encodeURI(liveaddress))
			+"&type="+type
			+"&paytype="+paytype
			+"&typevalue="+encodeURI(encodeURI(typevalue)) 
			+"&paytypevalue="+encodeURI(encodeURI(paytypevalue)) 
			,//发送请求的地址
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
				USER_OBJ.close_window('edituserinfo');
			}
		 });
	},
	
	/**
	 * 通用关闭窗口
	 */
	close_window:function(param){
	    var GSCP = $("#"+param);
	    var mask = $("#mask"); 
	    mask.css("display","none"); 
	    GSCP.css("display","none");  
	    $(".bodyid").mask("正在加载中..."); 			 
		$("#formid").submit(); 
	}
};
