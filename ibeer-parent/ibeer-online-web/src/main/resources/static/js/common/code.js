/**
 * 1.$("seletor").offset();
 *   方法返回或设置匹配元素相对于文档的偏移（位置）
 *   该方法返回的对象包含两个整型属性：top 和 left，以像素计。此方法只对可见元素有效。
 * 2.event.pageX:属性返回鼠标指针的位置，相对于文档的左边缘;
 * 3.var event = e||window.event;
 *   为了实现多种浏览器兼容。https://www.jianshu.com/p/e8a6fad0f7bc
 * 4.基本思路:
 *     整体：将前台的坐标和后台生成的坐标比较;
 *     前端:
 * 5.基本步骤:
 * 6.前端：https://blog.csdn.net/jylonger/article/details/83855324
 *   后端：https://blog.csdn.net/YTenderness/article/details/99969355
 * 
 * 
 */





$(function(){
	//鼠标的位置
	var mX=0,mY=0;
	//滑块区域左上位置
	var dX=0,dY=0;
	//拖动按钮
	var divMove = $(".JDJRV-slide-btn");
	//鼠标可拖动的区域
	var divWrap=$(".JDJRV-slide-bg");
	//鼠标拖拽标志
	var isMousedown= true;
	//onmousedown 事件会在鼠标按键被按下时发生
	$(".JDValidate-wrap").on("mousedown",".JDJRV-slide-btn",function(e){
		var event = e||window.event;
		//鼠标的位置
		mX = event.pageX;
		//鼠标可拖动的区域的左上位置(坐标)
		dX = divWrap.offset().left;
		dY = divWrap.offset().top;
		//鼠标拖拽标志
		isMousedown= true;
		console.log(dX+","+dY);	
	});
	//当鼠标指针移动到元素上方，并松开鼠标左键时;
	$(".JDValidate-wrap").on("mouseup",".JDJRV-slide-btn",function(e){
		var lastX = $(".JDJRV-smallimg").offset().left - dX - 1; //---->需要研究
		//修改鼠标拖拽标记
		isMousedown=false;
		//校验验证码
		var url="/verifyImageCode";
		var postData={"moveLength":lastX};
		postAjax(url,postData,function(data){
			var result = data.returnResult;
   		    if(result.errcode == '0'){
   		    	//隐藏验证码浮出框
   		    	$(".item-getcode-wrap").css("display","none");
   		    	//隐藏验证验证码按钮
   		    	$(".slide-authCode-wraper").css("display","none");
   		    	timer120();
   		    	//显示手机验证码文本框
   		    	$(".item-phonecode-wrap").css("display","block");
   		        $(".item-phonecode-wrap .input-tip").html("<span><i class='i-def'></i>验证码已发送，120秒内输入有效</span>");
   		    }else{
   		    	//调整拖动按钮位置
   		    	divMove.css({"left": (0) + "px"});
   			    $(".JDValidate-wrap").find(".JDJRV-smallimg").css({"left": (0) + "px"});
   		    }
	   	}, {errorFunction:function(data){
	   		alert(data.resultMessage);
	   	},cache: false, async: false,type:"get",contentType:"application/x-www-form-urlencoded"}); 
		
		
	});
	 function timer120(){
		   setTime = setInterval(timer,1000);
	   }
	  var time=120;  
	  var timer = function(){
		  if(time<0){
	          clearInterval(setTime);
	          $("#getPhoneCode").text("重新获取"); 
	          time=120;
	          return;
	      }
		  $("#getPhoneCode").text(time+"秒后重新获取"); 
		time--;
		
	  }
	//当鼠标指针在指定的元素中移动时，就会发生 mousemove 事件。
	$(".JDJRV-slide-btn").mousemove(function(e){
		var event = e||event;
		//鼠标滑动的x的坐标
		var x = event.pageX;
		console.log("X:"+x);
		if(isMousedown){
			//鼠标滑动的x的坐标大于滑块区域x的坐标+30且滑块区域x的坐标+整个盒子的宽度-20-----》   需要研究
			if(x>(dX+30) && x<dX+$(".JDValidate-wrap").width()-20){				
                divMove.css({"left": (x - dX - 20) + "px"});//div动态位置赋值------》 需要研究
                $(".JDValidate-wrap").find(".JDJRV-smallimg").css({"left": (x - dX-30) + "px"});//---->需要研究
            }

		}
	});
	//滑块验证码 关闭
    $(document).on("click",".slide-authCode-wraper .close",function(){
    	$(".slide-authCode-wraper").css("display","none");
    	divMove.css({"left": (0) + "px"});	
    	$(".JDValidate-wrap").find(".JDJRV-smallimg").css({"left": (0) + "px"});
    });

});




