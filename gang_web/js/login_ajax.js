var rurl = apphost+"/index.php?g=logistics&m=user&a=register";
var registerUrl="/gang/user/registerApp.action";
var lurl = apphost+"/index.php?g=logistics&m=user&a=do_login";
var lgoinUrl="/gang/user/loginApp.action";
$(function(){
	$("#submit").click(function(event){
		$.post(registerUrl,{
			userName: $("#user_name").val(),
			email: $("#user_email").val(),
			tel: $("#phone").val(),
			pwd: $("#password").val()
		},function(data){
			if(data.status==0){
				alert(data.msg);
				location.href = "index.html?data="+data;
			}else{
				alert(data.msg);
			}
			
		},
		"json")
	})
})
$(function(){
	$("#button").click(function(event){
		$.post(lgoinUrl,{
			userName: $("#login_user_name").val(),
			pwd: $("#login_password").val()
		},function(data){
			if (data.status==0) {
				alert(data.msg);
				location.href = "index.html?data="+data;
			}
			else{
				alert(data.msg);
			}
				
		},
		"json")
	})
})