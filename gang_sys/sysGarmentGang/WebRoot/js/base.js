/***背景*/ 
function background_search(){   
	var allHeight1 = document.body.scrollHeight;
	var allHeight2 = document.documentElement.clientHeight;
	var allHeight = allHeight1>allHeight2?allHeight1:allHeight2;
	document.getElementById("search").style.height = allHeight+"px";
	document.getElementById("search").style.display = "block";
}
 //弹出层
 function showDialog(dialog) {
 		//var objW = $(window); //当前窗口
       // var objC = $("#"+dialog); //对话框
       // var brsW = objW.width();
       // var brsH = objW.height();
       // var sclL = objW.scrollLeft();
       // var sclT = objW.scrollTop();
       // var curW = objC.width();
      //  var curH = objC.height();
        /*
 	    var objW = document.body; //当前窗口 
        var objC = document.getElementById(dialog); //对话框
        var brsW = objW.clientWidth;
        var brsH = objW.clientHeight;
        var sclL = objW.scrollLeft;
        var sclT = objW.scrollTop;
        var curW = objC.offsetWidth; 
        var curH = objC.offsetHeight;
         */
        //计算对话框居中时的左边距
       // var leftt = sclL + (brsW - curW) / 2;
        //计算对话框居中时的上边距
      //  var topt = sclT + (brsH - curH) / 2;

        //设置对话框在页面中的位置
    
      // alert("brsW:"+brsW+" brsH:"+brsH+" sclL:"+sclL+" sclT:"+sclT+" curW:"+curW+" curH"+curH+" leftt:"+leftt+" topt:"+topt);
       // alert(objC)  
      //  $(".dialog").css({ "left": leftt, "top": topt }); 
      // objC.style.left=leftt;
      // objC.style.top=topt;
      // alert($("#sendMessageAddLinkmanPopup").offset().top);
 	 var objW = $(window); //当前窗口
        var objC = $("#"+dialog); //对话框
        var brsW = objW.width();
        var brsH = objW.height();
        var sclL = objW.scrollLeft();
        var sclT = objW.scrollTop();
        var curW = objC.width();
        var curH = objC.height();
        //计算对话框居中时的左边距
        var left = sclL + (brsW - curW) / 2;
        //计算对话框居中时的上边距
        var top = sclT + (brsH - curH) / 2;
        //设置对话框在页面中的位置
       // $(".dialog").css({ "left": left, "top": 105 });
        $(".dialog").css({ "left": left, "top": 40 });  
    }
    function singleCycle(dialog) { 
 	 var objW = $(window); //当前窗口 
        var objC = $("#"+dialog); //对话框
        var brsW = objW.width();
        var brsH = objW.height();
        var sclL = objW.scrollLeft();
        var sclT = objW.scrollTop();
        var curW = objC.width();
        var curH = objC.height();
        //计算对话框居中时的左边距
        var left = sclL + (brsW - curW) / 2;
        //计算对话框居中时的上边距
        var top = sclT + (brsH - curH) / 2;
        //设置对话框在页面中的位置
       // $(".dialog").css({ "left": left, "top": 105 });
        $("#singleCycleSendAddsms").css({ "left": left, "top": 87 });   
    } 
    function mmsDetalied(dialog) { 
 	 var objW = $(window); //当前窗口 
        var objC = $("#"+dialog); //对话框
        var brsW = $("#noteStoragePopupMMS").width();
        var brsH = $("#noteStoragePopupMMS").height();
        var sclL = $("#noteStoragePopupMMS").scrollLeft();
        var sclT =$("#noteStoragePopupMMS").scrollTop(); 
        var curW = objC.width();
        var curH = objC.height();
        //计算对话框居中时的左边距
        var left = sclL + (brsW - curW) / 2;
        //计算对话框居中时的上边距
        var top = sclT + (brsH - curH) / 2;
        //设置对话框在页面中的位置
        $("#Detalied").css({ "left": 270, "top": 10 });   
    }
    $(function(){
    	//全选
    	$("#all_id").bind("click",function(){
    		var checkboxall  = $("#checkboxall");
    		 if (checkboxall.is(":checked")) {
                    $("[name=checkboxid]:checkbox").prop("checked", true);
                    checkboxall.prop("checked", false);
                    $(this).removeClass("select-single").addClass("select-all");
                    $("[id=singlgid]").removeClass("select-single").addClass("select-all");
                 	
                } else {
                    $("[name=checkboxid]:checkbox").prop("checked", false);
                     checkboxall.prop("checked", true);
                     $(this).removeClass("select-all").addClass("select-single");
                     $("[id=singlgid]").removeClass("select-all").addClass("select-single");
                }
    	});
    	//单选
    	$("[id=singlgid]").bind("click",function(){
    		var singlgid = $("[name=checkboxid]:checkbox");
    		var _val = $(this).attr("for");
    		for(var i = 0;i<singlgid.length;i++){
    			if(singlgid[i].value==_val){
    				if(singlgid[i].checked){
    				   singlgid[i].checked=false;
    				   $(this).removeClass("select-all").addClass("select-single");
    				}else{
    				  singlgid[i].checked=true;
    				  $(this).removeClass("select-single").addClass("select-all");
    				}
    			}
    		}
    	});
    	var msg = ""; 
		$(".home_hd_user_intr_show").hover(function(){msg = $(this).html();$(this).html($(this).attr("dir"));},function(){$(this).html(msg);});	
           /*
		var dragging = false;
            var iX, iY;
            $(".dialog1").mousedown(function(e) {  
                dragging = true;
                iX = e.clientX - this.offsetLeft;
                iY = e.clientY - this.offsetTop;
                this.setCapture && this.setCapture();
                return true;
            });
            document.onmousemove = function(e) {
                if (dragging) {
                var e = e || window.event;
                var oX = e.clientX - iX;
                var oY = e.clientY - iY;
                $(".dialog1").css({"left":oX + "px", "top":oY + "px"});
                return true;
                }
            };
            $(document).mouseup(function(e) {
                dragging = false;
                $(".dialog1")[0].releaseCapture();
                e.cancelBubble = true;
            })
            */
            
		});
    
	/**
	 * 查询
	 */
	function query(url){
		// background_search();
		var $bodyid = $(".bodyid");
			 //执行页面加载提示
			 $bodyid.mask("正在加载中...");
			 /*
			  //关闭页面加载提示
                if ($rightinfo.isMasked()) {
                    $rightinfo.unmask();
                }
			  //延时执行页面加载提示
                $rightinfo.mask("正在加载中...", 1000);*/
			 
		 $("#formid").attr("action",url);
		 $("#formid").submit();  

	}
	/**手机号码验证**/
	function phone_validate(phone){  
		var reg_telnet = "^1(3[0-2]|45|5[56]|76|8[56])\\d{8}$";
		var reg_cmcc = "^1(3[456789]|47|5[012789]|78|8[23478])\\d{8}$";
		var reg_phs = "^1(3[3]|53|73|77|8[019])\\d{8}$";
		var result_telnet  = new RegExp(reg_telnet);
		var reg_cmcc  = new RegExp(reg_cmcc);
		var reg_phs  = new RegExp(reg_phs);
		var falg = false;  
		if(phone!=null&&(result_telnet.test(phone)||reg_cmcc.test(phone)||reg_phs.test(phone))){
			falg = true;   
		}
		return falg;
		
	}
	function fmt(data){
		var result = "";
		if(Number(data)==0){
			result ="00";
		}else if(Number(data)>0&&Number(data)<10){
			result ="0"+data;
		}else{
			result =data; 
		}
		return result;
	}
Array.prototype.remove=function(dx){ 
	if(isNaN(dx)||dx>this.length){return false;}
    for(var i=0,n=0;i<this.length;i++){if(this[i]!=this[dx]){this[n++]=this[i]}}
	this.length-=1;
}