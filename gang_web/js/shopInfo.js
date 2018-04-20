/* 
* @Author: Marte
* @Date:   2018-04-17 17:48:23
* @Last Modified by:   Marte
* @Last Modified time: 2018-04-20 12:23:14
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

app.controller('shopInfo', function($scope, $http) {

    //服装信息详情
    $scope.goInfo=function(id){
        data.id=id;
        window.parent.ifms.location.href="clothingInofDetailed.html?data="+angular.toJson(data);
    }

    //获取服装信息
    getInfo();
    function  getInfo  () {
        //参数
        var datas=[
            'styletypeinfoId='+data.styletypeinfoId,
            'styleName='+data.styleName
        ];
        //发送请求
        $http({
            url: "/gang/clothingInfo/getClothingInfoList.action",
            method: 'POST',
            data: datas.join("&"),
            headers:{ 
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        }).then(function successCallback(response) {
            $scope.list=response.data;
        });
    }

});
/* angularJs  end */

