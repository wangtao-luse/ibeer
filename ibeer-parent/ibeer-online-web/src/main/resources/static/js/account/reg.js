$(function(){
	 /**注册**/
	$(".ui-dialog").hide();
	$(".ui-mask").hide();
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
    //注册 下一步按钮
    $("#step1-next").click(function(){
    	$("#step1-wrap").css("display","none");
    	$("#step2-wrap").css("display","block");
    	$(".cur-step").addClass("done-step");
    	$(".cur-step span").text("");
    	$(".cur-step").removeClass("cur-step");    	
    	$(".person-pro-step2").addClass("cur-step");
    })
    //选择区号
    $(".form-item.form-item-phone").mouseover(function(){
    	$("#select-country").css("border-color","rgb(153,153,153)");
    	$("#select-country").css("border-style","solid none solid solid");
    	$("#select-country").css("border-width","1px medium 1px 1px");
    	$(this).find(".item-input-wrap").css("border-color","rgb(153,153,153)");
    });
    ////选择区号
    $(".form-item.form-item-phone").mouseout(function(){
    	$("#select-country").css("border-color","rgb(221,221,221)");
    	$("#select-country").css("border-style","solid none solid solid");
    	$("#select-country").css("border-width","1px medium 1px 1px");
    	$(this).find(".item-input-wrap").css("border-color","rgb(221,221,221)");
    });
    //手机号码
    $("#form-phone").focus(function(){
    	$(this).prev().css("display","none");    	
    	var hasError = $(this).parent().next().find("span").hasClass("error");
    	if(!hasError){
    		var tip = $(this).attr("default");
    		$(this).parent().next().find("span").html(tip);
    	}
    	
    	$(this).parent().css("border","1px solid rgb(102, 102, 102)");
    	$("#select-country").css("border-color","rgb(221,221,221)");
    });
    //手机号码
    $("#form-phone").blur(function(){
    	if($(this).val()==""){
    		$(this).prev().css("display","inline");
    	}else{
    		$(this).prev().css("display","none");
    	}
    	
    	$(this).parent().css("border","1px solid rgb(221, 221, 221)");
    	$(this).parent().next().find("span").html("");
    	$(this).parent().find(".i-cancel").css("display","none");
    	if(test_phone($(this).val())){
    		$(this).parent().addClass("form-item-valid");
    	}else{
    		if($(this).val()!=""){
    			$(this).parent().next().find("span").addClass("error").attr("id","form-phone-error").html("<i class='i-error'></i>格式错误");
    		}
    		
    	}
    	
    });
    //https://www.cnblogs.com/zqifa/p/js-jquery-keyboard.html
    //手机号码
    $("#form-phone").keyup(function(){//事件会在按键释放时触发，也就是你按下键盘起来后的事件
    	var phone = $(this).val();
    	if(phone == ""){
    		$(this).parent().find(".i-cancel").css("display","none");
    		var tip = $(this).attr("default");
    		$(this).parent().next().find("span").removeClass("error").html(tip);
    			
    	}else{
    		if(!test_phone(phone)){    
        		$(this).parent().find(".i-cancel").css("display","block");
        		$(this).parent().removeClass("form-item-valid");
        		
        	}
    	}
    	
    });
    //手机号码的提示文本
    $(".item-input-wrap txt").click(function(){
    	$("#form-phone").focus();
    });
    //查找区号
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
    //验证码验证
    $(".form-item.form-item-getcode").click(function(){
    	var phone=$("#form-phone").val(); 
    	//验证手机号码
    	if(test_phone(phone)){
    		$(".slide-authCode-wraper").css("display","block");
    	}else{
    		if(phone==""){
    			$(".form-item.form-item-phone .input-tip span").attr("id","form-phone-error").attr("class","error").html("<i class='i-error'></i>请输入手机号");
    		}
    		
    		return;
    	}
    	
    });
    //验证码 关闭
    $(document).on("click",".slide-authCode-wraper .close",function(){
    	$(".slide-authCode-wraper").css("display","none");
    });
    	
    
   /* var dX;
    var dY;
	  $(".JDJRV-slide-inner.JDJRV-slide-btn").mousedown(function(e){
		  var event = e || window.event;
		  //console.log(event.pageX,event.pageY);
		   dX = $(this).offset().left;
           dY = $(this).offset().top;
         // console.log(dX,dY);
	  });
	  $(".JDJRV-slide-inner.JDJRV-slide-btn").mousemove(function(e){
		  var event = e || window.event;
		  var x= event.pageX;
		  console.log(x);
	  })
	  */
    //用户名
    $("#form-account").focus(function(){
    	var df=$(this).attr("default");
    	var hasError = $(this).parent().next().find("span").hasClass("error");
    	if(!hasError){
    		$(this).parent().next().find("span").html(df);
    	}
    	
    	
    });
    //用户名
    $("#form-account").blur(function(){
    	var df=$(this).attr("default");
    	$(this).parent().next().find("span").html("");
    	var nickName = $(this).val();
    	if(test_nickName(nickName)){
    		$(this).parent().find(".i-status").css("display","block");
    		$(this).parent().find(".i-cancel").css("display","none");
    		$(this).parent().addClass("form-item-valid");
    	}else{
    		$(this).parent().find(".i-status").css("display","none");
    		if(nickName!=""){
    			$(this).parent().next().find("span").addClass("error").html("<i class='i-error'></i>格式错误");
    		}
    		
    	}
    	
    });
    //用户名
    $("#form-account").keyup(function(){//事件会在按键释放时触发，也就是你按下键盘起来后的事件
    	var nickName = $(this).val();
    	if(nickName == ""){
    		$(this).parent().find(".i-cancel").css("display","none");
    		$(this).parent().next().find("span").removeClass("error")
    		var hasError = $(this).parent().next().find("span").hasClass("error");
    		if(!hasError){
    			var tip = $(this).attr("default");
        		$(this).parent().next().find("span").html(tip);
    		}	
    	}else{
    		if(!test_nickName(nickName)){    
        		$(this).parent().find(".i-cancel").css("display","block");
        		$(this).parent().removeClass("form-item-valid");
        		
        	}
    	}
    	
    });
    
    //设置密码
    $("#form-pwd").focus(function(){
    	var df=$(this).attr("default");
    	var hasError = $(this).parent().next().find("span").hasClass("error");
    	if(!hasError){
    		$(this).parent().next().find("span").html(df);
    	}
    });
    ////设置密码
    $("#form-pwd").blur(function(){
    	var df=$(this).attr("default");
    	$(this).parent().next().find("span").html("");
    	var pwd = $(this).val();
    	if(test_pwd(pwd)){
    		$(this).parent().find(".i-status").css("display","block");
    		$(this).parent().find(".i-cancel").css("display","none");
    		$(this).parent().addClass("form-item-valid");
    	}else{
    		$(this).parent().find(".i-status").css("display","none");
    		if(pwd!=""){
    			$(this).parent().next().find("span").addClass("error").html("<i class='i-error'></i>密码需要8-20位字符");
    		}
    		
    	}
    	
    });
    //设置密码
    $("#form-pwd").keyup(function(){//事件会在按键释放时触发，也就是你按下键盘起来后的事件
    	var pwd = $(this).val();
    	if(pwd == ""){
    		$(this).parent().find(".i-cancel").css("display","none");
    		$(this).parent().next().find("span").removeClass("error")
    		var hasError = $(this).parent().next().find("span").hasClass("error");
    		if(!hasError){
    			var tip = $(this).attr("default");
        		$(this).parent().next().find("span").html(tip);
    		}	
    	}else{
    		if(!test_pwd(pwd)){    
        		$(this).parent().find(".i-cancel").css("display","block");
        		$(this).parent().removeClass("form-item-valid");
        		
        	}
    	}
    	
    });
    //确认密码
    $("#form-equalTopwd").focus(function(){
    	var df=$(this).attr("default");
    	var hasError = $(this).parent().next().find("span").hasClass("error");
    	if(!hasError){
    		$(this).parent().next().find("span").html(df);
    	}
    });
    //确认密码
    $("#form-equalTopwd").blur(function(){
    	var df=$(this).attr("default");
    	$(this).parent().next().find("span").html("");
    	var pwd = $(this).val();
    	if(equalTopwd(pwd,$("#form-pwd").val())){
    		$(this).parent().find(".i-status").css("display","block");
    		$(this).parent().find(".i-cancel").css("display","none");
    		$(this).parent().addClass("form-item-valid");
    	}else{
    		$(this).parent().find(".i-status").css("display","none");
    		if(pwd!=""){
    			$(this).parent().next().find("span").addClass("error").html("<i class='i-error'></i>两次密码不一致");
    		}
    		
    	}
    	
    });
    //确认密码
    $("#form-equalTopwd").keyup(function(){//事件会在按键释放时触发，也就是你按下键盘起来后的事件
    	var pwd = $(this).val();
    	if(pwd == ""){
    		$(this).parent().find(".i-cancel").css("display","none");
    		$(this).parent().next().find("span").removeClass("error")
    		var hasError = $(this).parent().next().find("span").hasClass("error");
    		if(!hasError){
    			var tip = $(this).attr("default");
        		$(this).parent().next().find("span").html(tip);
    		}	
    	}else{
    		if(equalTopwd(pwd,$("#form-pwd").val())){    
        		$(this).parent().find(".i-cancel").css("display","block");
        		$(this).parent().removeClass("form-item-valid");
        		
        	}
    	}
    	
    });
    $("#form-register").click(function(){
    	
    	var  nickName = $("#form-account").val();
    	//检查用户名和密码
    	if(!test_nickName(nickName)){
    		return;
    	}
    	
    	/*if(!reg.test(nickName)){
    		var input-tip = $("#form-item-account").next();
    		input-tip.find("span").addClass("error").attr("id","form-account-error").html("<i class='i-error'></i>不正确");
    		return;
    	}*/
    	
    	
    	
    	
    	
    	var url="/account/regSub";
    	var postData =$("#register-form").serialize();
    	postAjax(url,JSON.stringify(postData),function(data){
    		console.log(data);
    	}, {errorFunction:function(data){
    		
    	},cache: false, async: false});
    });
});
var test_phone = function(phone){
	var phone_reg = /^1[3456789]\d{9}/;
	return phone_reg.test(phone);
}
var test_nickName = function(nickName){
	var nickName_reg = /^[\u4e00-\u9fa5_a-zA-Z-\d]{3,18}$/;
	return nickName_reg.test(nickName)
}
var test_pwd = function(pwd){
	var pwd_reg =/^[\D\d]{8,20}$/;
	return pwd_reg.test(pwd);
}
var equalTopwd = function(pwd,topPwd){
	return pwd == topPwd;
}
