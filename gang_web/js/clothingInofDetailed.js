/*
* @Author: Marte
* @Date:   2018-04-18 17:43:43
* @Last Modified by:   Marte
* @Last Modified time: 2018-04-20 15:14:37
*/

/*angularJs  start */
var app = angular.module('myApp', []);
var urlValue='';
//取得整个地址栏
var href = location.href; 
//此处只有一个参数，先截取参数值（等号后的值）。
urlValue = href.substr(href.indexOf("=") + 1);
//传参会转码，所以先解码，再把字符串string转对象
var data=angular.fromJson(decodeURI(urlValue));

app.controller('clothingInofDetailed', function($scope, $http) {
    //图片路径
    $scope.imgPath="https://raw.githubusercontent.com/lingfe/gang/master/resource/";
    //服装信息详情
    goInfo();
    function goInfo(){
        var datas=[
            'id='+data.id
        ];
        //发送请求
        $http({
            url: "/gang/clothingInfo/getClothingInfoWhereId.action",
            method: 'POST',
            data: datas.join("&"),
            headers:{ 
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        }).then(function successCallback(response) {
            $scope.list=response.data;
            $scope.info=$scope.list[0];
            $scope.imgArray=$scope.info.imgArray.split(',');
            $scope.infoImgArray=$scope.info.infoImgArray.split(",");
        });
    }
});
/*angularJs  end */


/* 设置头  start */
$(function(){ 

    var swiper = new Swiper('.swiper-container', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        loop: false,
    });

})
/* 设置头  end */   

/* mescroll.js  start */
$(function(){
});
/* mescroll.js  end */