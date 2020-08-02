/* 
* @Author: Marte
* @Date:   2018-04-17 17:48:23
* @Last Modified by:   Marte
* @Last Modified time: 2018-05-08 13:19:04
*/

/*angularJs  start */
var app = angular.module('myApp', []);

app.controller('shopInfoAll', function($scope, $http) {
    var data={};
    //服装信息详情
    $scope.goInfo=function(id){
        data.id=id;
        window.parent.ifms.location.href="clothingInofDetailed.html?data="+angular.toJson(data);
    }

    //获取服装信息
    getInfo();
    function  getInfo  () {
        //参数
        var datas=[];
        //发送请求
        $http({
            url: "/gang/clothingInfo/getClothingInfoAll.action",
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

