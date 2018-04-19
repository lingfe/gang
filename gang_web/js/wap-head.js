  $(function(){
        var hw = $('.headimg').width();
        var hh = $(window).width();
        var th = (hh-hw)/2;
        $('.headimg').css('padding-right',th+'px');
            $(".home").on("click",function(){
                  $("body").bind("touchmove",function(event){event.preventDefault;},false); 
                
                    if($(".navigation").attr('class')=='navigation'){
                            $(".navigation").addClass('-closed');
                            $('.second').hide();
                             $("body").unbind("touchmove");
                    }else{
                            $(".navigation").removeClass('-closed');  	
                    }
         
                  

                    $('.shade').on("click",function(){
                            //$(".nav").animate({left: '-70%'}, "1000");
                            $(".nav").css('-webkit-transform','translate(0)');
                            $('.shade').hide();
                            //$('.second').animate({left: '-70%'}, "1000");
                            $(".second").css('-webkit-transform','translate(0)');
                            $("body").unbind("touchmove");
                            return false;
                    })

                    $('.underlay').on("click",function(){
                            $(".navigation").addClass('-closed');
                            $("body").unbind("touchmove");
                            $('.second').hide();
                             $('.help').show();
                            return false;
                    }) 					


                    $('.fson').on("click",function(){
                            $('.fson').css('background-color','#1D1F1F');
							  						$(this).css('background-color','#171917');
							  						$('.second').hide();
							  						
							  						var obj = $(this).next('ul');
							  						obj.show();
                    })

                    
                    return false;
            });
        });