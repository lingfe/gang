/* 
* @Author: Marte
* @Date:   2018-04-18 14:18:18
* @Last Modified by:   Marte
* @Last Modified time: 2018-04-20 09:22:40
*/

/*angularJs  start */
var app = angular.module('myApp', []);
app.controller('index', function($scope, $http) {
    //图片路径
    $scope.imgPath="https://raw.githubusercontent.com/lingfe/gang/master/resource/";
    //页面可见区域高度
    $scope.wy_height=document.body.clientHeight;
    ///?styletypeinfoId={{item3.id}}&styleName={{item3.styleName}}
    var data={};
    $scope.shopInfo=function(styletypeinfoId,styleName){
        data.styletypeinfoId=styletypeinfoId;
        data.styleName=styleName;
        var ifm=$("#if");
        window.parent.ifms.location.href="shopInfo.html?data="+angular.toJson(data);
    }
    //获取所有服装款式分类信息
    getStyleTypeInfo();
    function  getStyleTypeInfo  () {
        //参数
        var data=[];
        //发送请求
        $http({
            url: "/gang/styleTypeInfo/getInfoList.action",
            method: 'GET',
            data: data.join("&"),
            headers:{ 
                'Content-Type': 'application/x-www-form-urlencoded'
            },
        }).then(function successCallback(response) {
            $scope.list=response.data;
        });
    }

});
/* angularJs  end */
