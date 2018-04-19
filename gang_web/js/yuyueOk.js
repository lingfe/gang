/* 
* @Author: Marte
* @Date:   2018-04-19 15:56:09
* @Last Modified by:   Marte
* @Last Modified time: 2018-04-19 16:02:20
*/

/*angularJs  start */
var app = angular.module('myApp', []);
app.controller('yuyueOk', function($scope, $http) {
    //图片路径
    $scope.imgPath="https://raw.githubusercontent.com/lingfe/gang/master/resource/";

});
/*angularJs  end */

        $(function(){
            $('.shadeclick').click(function(){              
                $('.shade').show();                 
                $('.alert_success').show();                                 
            })
            $('.shade').click(function(){               
                $('.shade').hide();                 
                $('.alert_success').hide();                                 
            })
            $('.alert_success').click(function(){               
                $('.shade').hide();                 
                $('.alert_success').hide();                                 
            })
        })  

        /* 设置头  start */
        $(function(){
            var ah = $('.advertising').height();                             
            var ch = ah+42;
            $('body').css('padding-top',ch+'px');
        })
        /* 设置头  end */ 



                    $(function(){
                                
                var demo = document.getElementById("demo");
                var demo1 = document.getElementById("demo1");
                var demo2 = document.getElementById("demo2");
                demo2.innerHTML=document.getElementById("demo1").innerHTML;
                function Marquee(){
                if(demo.scrollLeft-demo2.offsetWidth>=0){
                 demo.scrollLeft-=demo1.offsetWidth;
                }
                else{
                 demo.scrollLeft++;
                }
                }
                var myvar=setInterval(Marquee,30);
                demo.onmouseout=function (){myvar=setInterval(Marquee,30);}
                demo.onmouseover=function(){clearInterval(myvar);}
                    
                $("#openApps").openApp({
                        defualt_url :"http://a.app.qq.com/o/simple.jsp?pkgname=com.beidu.ybrenstore", 
                        Type:"6", //1 打开网页  2今日风尚 3今天穿什么  6优惠券 7 我的身材数据 8我的私人着装顾问 9客服 10 商品详情 11 
                        Param:"",
                        Message:""
                }); 

            });