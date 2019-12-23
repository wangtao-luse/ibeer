var postAjax = function(url,postData,successFunction,option){
			var returnFlag=false;
			var defaultOptions={
				type:"post",
				async:true,
				errorFunction:errorFunction,
				successArguments: "",
				errorArguments: "",
				contentType:"application/json; charset=utf-8",/*传入数据类型*/
				dataType:"json"/* 返回的数据类型*/
			};			
			var currentOptions = $.extend(defaultOptions, options);//https://www.runoob.com/jquery/misc-extend.html
			$.ajax({
				type:currentOptions.type,
				url:url,
				data:postData,
				async:currentOptions.async,
				contentType:currentOptions.contentType,
				dataTyp:currentOptions.dataType,
				beforeSend:function(){
					loading();
				},
				success:function(resultData){
					if(resultData&&isSuccess(resultData.resultCode)){
						if(defaultOptions&&defaultOptions.successArguments){
							sucessFunction(resultData,defaultOptions.successArguments);
						}else{
							sucessFunction(resultData);
						}
						returnFlag = true;
					}else{
						defaultOptions.errorFunction(resultData);
						returnFlag = false;
					}
					
					
				},
				complete:function(){
					deleteLoading();
				},
				error:function(result){
					deleteLoading();
				},
				
				
			});
		}
		function loading(){
		
		}
		function deleteLoading(){
		
		}
		function isSuccess(resultCode){
		return ("10"===resultCode)
		}
		function errorFunction(resultData) {
		showMessage(resultData);
		}
		
		function showMessage(resultData) {
		alert(resultData.description);
		}
