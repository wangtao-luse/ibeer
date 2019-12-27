var postAjax = function(url,postData,successFunction,options){
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
							successFunction(resultData,defaultOptions.successArguments);
						}else{
							successFunction(resultData);
							
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
			return returnFlag;
		}

/*var postAjaxNoLoading = function (url, postData, successFunction, options) {
    var returnFlag= false;
    var defaultOptions = {
        errorFunction: errorFunction,
        successArguments: "",
        errorArguments: "",
        successMessage: "",
        type: "post",
        async: true,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        cache:false
    };
    var currentOptions = $.extend(defaultOptions, options);
    url = url.indexOf('?')!=-1?url + '&securityToken='+$("#securityToken").val():url + '?securityToken='+$("#securityToken").val();
    $.ajax({
        type: currentOptions.type,
        data: postData,
        contentType: currentOptions.contentType,
        dataType: currentOptions.dataType,
        url: url,
        async: currentOptions.async,
        success: function (resultData) {
            if (resultData && isSuccess(resultData.resultcode)) {
                if (currentOptions && currentOptions.successArguments) {
                    successFunction(resultData, currentOptions.successArguments);
                } else {
                    successFunction(resultData);
                }
                returnFlag = true;
            } else if(isRediration(resultData.resultcode)){
                var pathName = document.location.pathname;
                var index = pathName.substr(1).indexOf("/");
                var result = pathName.substr(0, index + 1);
                alert(resultData.description);
                location.replace(result+'/index');
            }else if(isLogin(resultData.resultcode)){
                var pathName = document.location.pathname;
                var index = pathName.substr(1).indexOf("/");
                var result = pathName.substr(0, index + 1);
                alert(resultData.description);
                location.replace(result+resultData.returnResult);
            }else{
                defaultOptions.errorFunction(resultData);
                console.log(resultData);
                returnFlag = false;
            }
        },
        complete:function(){
            deleteLoading();
        },
        error: function (result) {
            returnFlag = false;
            deleteLoading();
            // $("html").html(result);
        }
    });
    return returnFlag;
};
var ajax = function (url, postData, successFunction, options) {
    var returnFlag= false;;
    var defaultOptions = {
        errorFunction: errorFunction,
        successArguments: "",
        errorArguments: "",
        successMessage: "",
        type: "post",
        async: true,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        cache:false
    };
    var currentOptions = $.extend(defaultOptions, options);
    url = url.indexOf('?')!=-1?url + '&securityToken='+$("#securityToken").val():url + '?securityToken='+$("#securityToken").val();
    $.ajax({
        type: currentOptions.type,
        data: postData,
        contentType: currentOptions.contentType,
        dataType: currentOptions.dataType,
        url: url,
        async: currentOptions.async,
        beforeSend: function () {
        	loading();
        },
        success: function (resultData) {
        	successFunction(resultData);
        },
        complete:function(){
            deleteLoading();
		},
        error: function (result) {
            returnFlag = false;
            deleteLoading();
            // $("html").html(result);
        }
    });
    return returnFlag;
};

var postForm = function (url, postData, successFunction, options) {
    var returnFlag= false;;
    var defaultOptions = {
        errorFunction: errorFunction,
        successArguments: "",
        errorArguments: "",
        successMessage: "",
        type: "post",
        async: true,
        contentType: "application/x-www-form-urlencoded; charset=UTF-8",
        cache:false
    };
    var currentOptions = $.extend(defaultOptions, options);
    url = url.indexOf('?')!=-1?url + '&securityToken='+$("#securityToken").val():url + '?securityToken='+$("#securityToken").val();
    $.ajax({
        type: currentOptions.type,
        data: postData,
        contentType: currentOptions.contentType,
        dataType: currentOptions.dataType,
        url: url,
        async: currentOptions.async,
        beforeSend: function () {
        	loading();
        },
        success: function (resultData) {
            if(typeof resultData =="string"){
                successFunction(resultData);
            }else{
                if(typeof resultData =="object") {
                    alert(resultData.description);
                }
            }
        },
        complete:function(){
            deleteLoading();
		},
        error: function (result) {
            returnFlag = false;
            deleteLoading();
            // $("html").html(result);
        }
    });
    return returnFlag;
};*/



		function loading(){
			 $('body').append('<div id="aLoad"><div id="aLoad-overlay"></div><div class="cssload-loader"><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div><div class="cssload-side"></div>' +
		                '<div class="cssload-side"></div></div>');
		}
		function deleteLoading(){
			 $("#aLoad").remove();
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
		
		
		function filterSpecial(value) {
		    return value.replace(/[']/g, '')
		}
		$.serializeObject = function (form) {
		    var o = {};
		    $.each(form.serializeArray(), function (index) {
		        if (o[this['name']]) {
		            o[this['name']] = filterSpecial(o[this['name']] + "," + this['value'])
		        } else {
		            o[this['name']] = filterSpecial(this['value'])
		        }
		    });
		    return o
		};