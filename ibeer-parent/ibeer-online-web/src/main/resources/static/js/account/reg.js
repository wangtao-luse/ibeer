$(function(){
	 /**注册**/
    $(".ui-dialog-close").click(function(){
    	$(".ui-dialog").hide();
    	$(".ui-mask").hide();
    	location.href="/index";
    });
    $(".protocol-button button").click(function(){
    	$(".feedback").hide();
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
    $(".form-item.form-item-phone").mouseover(function(){
    	$("#select-country").css("border-color","rgb(153,153,153)");
    	$("#select-country").css("border-style","solid none solid solid");
    	$("#select-country").css("border-width","1px medium 1px 1px");
    	$(this).find(".item-input-wrap").css("border-color","rgb(153,153,153)");
    });
    $(".form-item.form-item-phone").mouseout(function(){
    	$("#select-country").css("border-color","rgb(221,221,221)");
    	$("#select-country").css("border-style","solid none solid solid");
    	$("#select-country").css("border-width","1px medium 1px 1px");
    	$(this).find(".item-input-wrap").css("border-color","rgb(221,221,221)");
    });
    $("#form-phone").focus(function(){
    	$(this).prev().css("display","none");
    	var tip = $(this).attr("default");
    	$(this).parent().next().find("span").html(tip);
    	$(this).parent().css("border","1px solid rgb(102, 102, 102)");
    	$("#select-country").css("border-color","rgb(221,221,221)");
    });    
    $("#form-phone").blur(function(){
    	$(this).prev().css("display","inline");
    	$(this).parent().css("border","1px solid rgb(221, 221, 221)");
    	$(this).parent().next().find("span").html("");
    	
    });
    $(".item-input-wrap txt").click(function(){
    	$("#form-phone").focus();
    });
    $("#select-country").click(function(){    	
    	$("#country_code_layer").css("top","354.4px").css("left","559.6px");
    	var down=$(this).find("a");
    	if(down.hasClass("down")){
    		down.removeClass("down");
    		$("#country_code_layer").css("display","none");
    	}else{
    		down.addClass("down");
    		$("#country_code_layer").css("display","block");
    	}
    	
    	
    });
   /* $("#scrollbar2 .scrollbar").scroll(function(){
    	var top = $(this).scrollTop();
    	console.log(top);
    	$(this).find(".thumb").css("top",top+"px");
    	$(this).find(".thumb").css("height","35.9118px");
    	$(this).find(".overview").css("top",top+"px");
    });*/
    
    $("#form-register").click(function(){
    	var url="/account/regSub";
    	var postData =$("#register-form").serialize();
    	console.log(postData);
    	//JSON.stringify(postData);
    	postAjax(url,postData,function(data){
    		console.log(data);
    	}, {errorFunction:function(data){
    		
    	},cache: false, async: false});
    });
});