var PAYFEES={
		//是否显示
		from_isDisplay:function(that,id){
			$("input[name='param']").val('isDisplay');
			if($(that).val() == '显示中'){
				$(that).val("隐藏中");
				$("input[name='isDisplay']").val('否');
				$(that).attr("class","cz_button is_none");
			}else{
				$(that).val("显示中");
				$("input[name='isDisplay']").val('是');
				$(that).attr("class","cz_button is_block");
			}
			$("#form_update"+id).submit();
		},
		//修改
		from_update:function(id){
			qikoo.dialog.confirm('确定要修改此吗？',function(){
				$("input[name='param']").val('update');
				$("#form_update"+id).submit();
	        },function(){ });
		},
		//删除
		from_delete:function(id){
			qikoo.dialog.confirm('确定要删除此吗？',function(){
				$("input[name='param']").val('delete');
				$("#form_update"+id).submit();
	        },function(){ });
		},
		
	//查询缴费单
	payfee_query:function(url){
	     $("#page").val(0);  
		 //执行页面加载提示 
		 $(".bodyid").mask("正在加载中...");	
		 $("#formid").attr("action",url); 
		 $("#formid").submit();
	},
	
	clear_text:function()
	{
		var sendrem=$.trim($("#editrem").val());//#CCCCCC
		if(sendrem=='请输入信息')
		{
			$("#editrem").val('');
			$("#editrem").css("color","#4A4A4A");
			return false;
		}else
		{
			return true;
		}
	},
    
	add_text:function()
	{
		var refundrem=$.trim($("#editrem").val());
		if(refundrem=='')
		{
			$("#editrem").val('请输入信息');
			$("#editrem").css("color","#CCCCCC");
		}
	},
	
	//修改缴费月数
	edit_pay_moths:function(url)
	{
		var ordernum=$("#payordernum").val();
		var moths=jQuery("#paymoths option:selected").val();
		if(confirm("你确定设置吗？")){
			$.ajax({
			   	type : "post",
	    	   	url : ""+url+"?ordernum="+ordernum+"&moths="+moths,
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
					var payfeeinfo=data.payfeeinfo;
					$("#paymoths option[value='"+payfeeinfo.paytype+"']").attr("selected","selected");
					$("#payfee").html(payfeeinfo.payfee);  
				  	$("#enddate").html(payfeeinfo.payfeefinishtime);
			   	}
			});
		}
	},
	
	//打开设置缴费窗口
	open_window_pay:function(ordernum,username,type,paytype,payfee,status,estatearea,enddate,param){
		var typevalue='';
		if(type=='1')typevalue='房屋';
		if(type=='2')typevalue='车位';
		
		//房屋缴费类型(未知0，按月1，按季度3，半年缴6，全年12) 
		var paytypevalue='';
		if(paytype=='1') paytypevalue='1个月';
		if(paytype=='2') paytypevalue='2个月';
		if(paytype=='3') paytypevalue='3个月';
		if(paytype=='4') paytypevalue='4个月';
		if(paytype=='5') paytypevalue='5个月';
		if(paytype=='6') paytypevalue='6个月';
		if(paytype=='7') paytypevalue='7个月';
		if(paytype=='8') paytypevalue='8个月';
		if(paytype=='9') paytypevalue='9个月';
		if(paytype=='10') paytypevalue='10个月';
		if(paytype=='11') paytypevalue='11个月';
		if(paytype=='12') paytypevalue='12个月';
		
		var payvalue='';
		if(status==0) payvalue='未缴费'
		if(status==1) payvalue='已缴费'
		
		$("#payordernum").val(ordernum);
		$("#paywner").html(username);
		
		$("#typevalue").html(typevalue);
		$("#paymoths option[value='"+paytype+"']").attr("selected","selected");
		$("#payfee").html(payfee);  
		$("#paystatus").html(payvalue);
	  	$("#estatearea").html(estatearea);
	  	$("#enddate").html(enddate);
	  	
		var GSCP = $("#"+param);
	  	var mask = $("#"+param);
	  	mask.css("display","block");
	  	GSCP.css("display","block");
	  	showDialog(param);
	},
	
	//确认缴费
	comfir_off_pay:function(url)
	{
		var ordernum=$("#payordernum").val();
		if(confirm("你确定缴费吗？")){
			$.ajax({
			   	type : "post",
	    	   	url : ""+url+"?ordernum="+ordernum,
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
	
	//打开短信发送窗口
	open_window_sms:function(phone,username,smsinfo,type,param){
	  	$("#phone").val(phone);
	  	$("#type").val(type);
	  	$("#uowner").html(username);
	  	$("#smsid").html(smsinfo);type
		var GSCP = $("#"+param);
	  	var mask = $("#"+param);
	  	mask.css("display","block");
	  	GSCP.css("display","block");
	  	showDialog(param);
	},
	
	sms_send_info:function(url)
	{
		var phone=$("#phone").val();
		var type=$("#type").val();
		if(confirm("你确定发送吗？")){
			$.ajax({
			   	type : "post",
	    	   	url : ""+url+"?phone="+phone+"&type="+type,
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
	//导出已缴未缴报表
	isno_payfee_export:function(url,status)
    {
    	 //获取导出时间
		 var searchvalue=$("#searchvalue").val();
		 var paystatus=status;
		 var startime=$("#time1").val();
	     var endtime=$("#time2").val();
	     $.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?searchvalue="+searchvalue
			+"&paystatus="+paystatus
			+"&startime="+startime
			+"&endtime="+endtime
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
				var mark=result.data.mark;
				if(status==0)
				{
					if(mark=='no')
					{
					   alert('没有可导出数据!');
					}
					else if(mark=='big')
					{
					   alert('导出数据量太大!');
					}else
					{
						var url="excelstreamym?searchvalue="+searchvalue+"&paystatus="+paystatus+"&startime="+startime+"&endtime="+endtime;
						window.open(url);
					}
				}else
				{
				    alert(msg);
				}
			}
	   });
	},
	
	//导出缴费报表
	payfee_list_export:function(url)
    {
    	 //获取导出时间
		 var searchvalue=$("#searchvalue").val();
		 var paystatus=$("input[name='paystatus']:checked").val();
		 var paymethod=$("input[name='paymethod']:checked").val();
		 var startime=$("#time1").val();
	     var endtime=$("#time2").val();
	     $.ajax({
			type : "post",// 指定是post还是get encodeURI()
			url : ""+url+"?searchvalue="+searchvalue
			+"&paystatus="+paystatus
			+"&paymethod="+paymethod
			+"&startime="+startime
			+"&endtime="+endtime
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
				var mark=result.data.mark;
				if(status==0)
				{
					if(mark=='no')
					{
					   alert('没有可导出数据!');
					}
					else if(mark=='big')
					{
					   alert('导出数据量太大!');
					}else
					{
						var url="exportexcelstream?searchvalue="+searchvalue+"&paystatus="+paystatus+"&paymethod="+paymethod+"&startime="+startime+"&endtime="+endtime;
						window.open(url);
					}
				}else
				{
				    alert(msg);
				}
			}
	   });
	},
	
	//打开添加业主窗口
	open_window:function(managerid,param){
	  	var GSCP = $("#"+param);
	  	var mask = $("#"+param);
	  	mask.css("display","block");
	  	GSCP.css("display","block");
	  	showDialog(param);
	},
	
	//通用关闭窗口
	close_window:function(param){
	    var GSCP = $("#"+param);
	    var mask = $("#mask"); 
	    mask.css("display","none"); 
	    GSCP.css("display","none");  
	    $(".bodyid").mask("正在加载中..."); 			 
		$("#formid").submit(); 
	}
};
