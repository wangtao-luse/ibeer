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
    
   $("#loginsubmit").click(function(){
		var url="/account/login";
    	var postData =$("#formlogin").serializeJSON();
    	postAjax(url,JSON.stringify(postData),function(data){
    		location.href="/index";
    	}, {errorFunction:function(data){
    		$(".msg-wrap .msg-error").removeClass("hide");
    		$(".msg-wrap .msg-error").html("<b></b>"+data.resultMessage);
    	},cache: false, async: false});
   });
   
})


