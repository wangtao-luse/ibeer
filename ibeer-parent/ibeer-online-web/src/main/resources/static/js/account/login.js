$(function(){
	$(".login-tab.login-tab-l").click(function(){
		$(".login-box").css("display","none");
		$(".login-form .login-box").css("visibility","hidden");
		$(".form .item-fore2").css("visibility","hidden");
		$(".qrcode-login").css("display","block");
		$(".login-tab a").removeClass("checked");
		$(this).find("a").addClass("checked");
	});
	$(".login-tab.login-tab-r").click(function(){
		$(".qrcode-login").css("display","none");
		$(".login-box").css("display","block");
		$(".login-form .login-box").css("visibility","visible");
		$(".form .item-fore2").css("visibility","visible");
		$(".login-tab a").removeClass("checked");
		$(this).find("a").addClass("checked");
	});
	
    $(".qrcode-img").hover(function(){
    	$(".qrcode-help").css("display","block");
    	$(this).css("left","0");
    	$("#qrcode-error-2016").css("left","-64px");
    })
    $(".qrcode-img").mouseleave(function(){
    	$(".qrcode-help").css("display","none");
    	$(this).css("left","64px");
    	$("#qrcode-error-2016").css("left","0px");
    });
    
    /**注册**/
    $(".ui-dialog-close").click(function(){
    	$(".ui-dialog").hide();
    	$(".ui-mask").hide();
    });

    $("#step1-next").click(function(){
    	$("#step1-wrap").css("display","none");
    	$("#step2-wrap").css("display","block");
    	$(".cur-step").addClass("done-step");
    	$(".cur-step span").text("");
    	$(".cur-step").removeClass("cur-step");    	
    	$(".person-pro-step2").addClass("cur-step");
    })
    $("#form-register").click(function(){
    })
})


