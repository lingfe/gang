/* 
* @Author: Marte
* @Date:   2018-04-19 15:15:17
* @Last Modified by:   Marte
* @Last Modified time: 2018-04-19 15:25:13
*/

/* 地址点击  start  */

            $(function(){
                $('.content span').click(function(){
                    var item1 = $(this).attr('addr');
                    var item2 = $(this).attr('city');                   
                    $('.addr').find('.right').html(item1);
                    $('.city').find('.right').text(item2);
                    $('.alert').show();
                })
                $('.xx').click(function(){
                    $('.alert').hide();
                })
            })
/* 地址点击  end  */


/*angularJs  start */
var app = angular.module('myApp', []);
app.controller('exhibitionAddress', function($scope, $http) {
    //图片路径
    $scope.imgPath="https://raw.githubusercontent.com/lingfe/gang/master/resource/";

});
/*angularJs  end */