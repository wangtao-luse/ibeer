$(function(){
	//https://blog.csdn.net/LEE18254290736/article/details/81326490
	//https://blog.csdn.net/ChibiMarukoChan/article/details/77934248
	//https://www.cnblogs.com/raphael1982/p/7994157.html
	var htmlObj=$("html");
	var height=window.screen.height;
	var width=window.screen.width;
	console.log("屏幕分辨率的高:"+height);
	console.log("屏幕分辨率的宽:"+width);
	//1. 1536*864
	//2. 1280*720
	console.log(document.body.clientWidth );
	console.log(document.body.clientHeight );
	
	if(height<864){
		htmlObj.addClass("o2_mini");
	}
	
	
	
	
	
	
	
	$("#b_cate .cate_menu_item").mouseenter(function(){
        var index=$(this).attr("data-index");
        $(this).addClass("cate_menu_item_on");
		$("#b_popCtn").css("display","block");
		$("#b_popCtn .cate_part").css("display","none");
		$("#cate_item"+index).css("display","block");
	});	
	$("#b_cate .cate_menu_item").mouseleave(function(){
		var index=$(this).attr("data-index");
		$(this).removeClass("cate_menu_item_on");
		$("#b_popCtn").css("display","none");
	});
	$("#b_popCtn").hover(function(){
		$("#b_popCtn").css("display","block");
	});
	$("#b_popCtn").mouseleave(function(){
		$("#b_popCtn").css("display","none");
	});
	$(window).scroll(function(){
		console.log($(document).scrollTop());
		var s = $(document).scrollTop();
		//左侧菜单
		if(s>=187.2&&s<1000){
			$("#b_popCtn").css("top",$(document).scrollTop()-187.2);
		}else{
			$("#b_popCtn").css("top",0);
		}
		//右侧导航
		if(s>1000){
			$(".elevator").addClass("elevator_fix");
		}else{
		$(".elevator").removeClass("elevator_fix");	
		}
		
	});	
	var bannerSlider = new Slider($('.sliderBannerWrapper .slider_list'), {
            time: 5000,
            delay: 400,
            event: 'hover',
            auto: true,
            mode: 'fade',
            controller: $('.sliderBannerWrapper .slider_wrappers,.slider_indicators'),
            activeControllerCls: 'slider_indicators_btn_active'
        });
       $('.sliderBanner .slider_control_prev').click(function() {
            bannerSlider.prev();
        });
        $('.sliderBanner .slider_control_next').click(function() {
            bannerSlider.next();
        });
        var recommendSlider = new Slider($('.sliderRecommend .slider_list'), {
            time: 10000,
            delay: 400,
            event: 'hover',
            auto: true,
            mode: 'fade',
            controller: $('.sliderRecommend .slider_wrappers'),
            activeControllerCls: ''
        });
 		$('.sliderRecommend .slider_control_prev').click(function() {
            recommendSlider.prev();
        });
        $('.sliderRecommend .slider_control_next').click(function() {
            recommendSlider.next();
        });
         var newBeerSlider = new Slider($('.box-new-inner .seckill-wrapper-new'), {
            time: 5000,
            delay: 400,
            event: 'hover',
            auto: true,
            mode: 'fade',
            controller: $('.box-new-inner  .seckill-list-conf'),
            activeControllerCls: ''
        });
        $('.box-new-inner .slider_control_prev').click(function() {
            newBeerSlider.prev();
        });
        $('.box-new-inner .slider_control_next').click(function() {
            newBeerSlider.next();
        });
        
       var setTime=setInterval(goods,100);
       var start=0;
       var goods=function(){
       	start=start-2;
       	 //console.log(start);
       	 $("#b_niceGoods .goods-list").css("transform","translate3d("+start+"px,0,0)");
       	 if(start<-2000){
       	 	start=0;
       	 }
       }
       $("#b_niceGoods .goods-list").mouseenter(function(){
       	  clearInterval(setTime);
       });
       $("#b_niceGoods .goods-list").mouseleave(function(){
       	  setTime=setInterval(goods,100);
       });
       
});
