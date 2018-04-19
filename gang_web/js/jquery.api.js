/*
Ajax 通过接口获取数据
日期：2018-01-03

------------------------------ */

(function($){
        $.fn.getAjaxHtml=function(settings){
        if(this.length<1){return;};
        // 默认值
        settings=$.extend({
            Method:"",
            Type:"",
            Param:"",
        },settings);
        var obj = this;

       // var init=function(){
            $.ajax({
                type: "post",
                dataType:'html',
                data:{'Param':settings.Param},
                url: "/index.php/Home/Api/"+settings.Method,
                beforeSend: function () {
                   
                },
                success: function (data) {
                    console.log(data);
                    obj.html(data);
                },
                complete: function () {
                    
                },
                error: function (data) {
                    console.info("error: " + data);
                }
            });
        // };

    };
})(jQuery);


