//做一个过滤器处理服务端超时后302的情况
$.ajaxSetup({
	 complete: function(jqXHR){
//		 debugger;
		 var parent =window.parent;
	        //未登录
	        if(jqXHR.status == '401'){
	        	try {
		        	/*	if(confirm("登录超时，是否跳转到登录页面？")){
		        			parent.location.href = webRoot;
		        		}*/
	        		   $.messager.confirm('确认', '登录超时，是否跳转到登录页面？', function(r) {
	                       if (r) {
	                    	   parent.location.href = vatRoot;
//	                          window.location.href="http://btcportal.lenovo.com.cn/";
	                       }
	                     });
				} catch (e) {
					// TODO: handle exception
					alert(e);
				}
	        }
	 }
});

//格式化日期(2-1)
Date.prototype.format = function (fmt) {
  var o = {
    "M+": this.getMonth() + 1, //月份 
    "d+": this.getDate(), //日 
    "h+": this.getHours(), //小时 
    "m+": this.getMinutes(), //分 
    "s+": this.getSeconds(), //秒 
    "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
    "S": this.getMilliseconds() //毫秒 
  };
  if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
  for (var k in o)
  if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
  return fmt;
};
//格式化日期(2-2)datagrid 中显示时间时，可以使用此方法
function formatDatebox(value) {
    if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    return dt.format("yyyy-MM-dd hh:mm:ss"); //扩展的Date的format方法(上述插件实现)
}

//显示提示信息
function promptMessage3(value){
	if(value){
		return '<span title='+value+'>'+value+'</span>';
	}else{
		return "";
	}
}

/**
 *	格式化操作列--修改 详情 删除
 */
function operateEditDetailDelete(val,row,index){
	var aa = '<a href="#"  style="color:blue;" onclick="editById(\'' + row.id+ '\')">修改</a>';
	var bb = '<a href="#"  style="color:blue;" onclick="showDetailById(\'' + row.id+ '\')">详情</a>';
	var cc = '<a href="#"  style="color:blue;" onclick="deleteById(\'' +row.id+ '\')">删除</a>';
	return aa+"&nbsp;"+bb+"&nbsp;"+cc;
}
/**
 *	格式化操作列--修改 删除
 */
function operateEditDelete(val,row,index){
	var aa = '<a href="#" style="color:blue;" onclick="editById(\'' + row.id+ '\')">修改</a>';
	var cc = '<a href="#" style="color:blue;" onclick="deleteById(\'' +row.id+ '\')">删除</a>';
	return aa+"&nbsp;"+"&nbsp;"+cc;
}

/**
 *	格式化操作列--修改
 */
function operateEdit(val,row,index){
	var aa = '<a href="#" style="color:blue;" onclick="editById(\'' + row.id+ '\')">修改</a>';
	return aa;
}
//********************************************************************以下内容是和 【搜索】 相关的


$(function(){
	/**
	 * 搜索按钮
	 */
	$('#btnSearch').click(function(){
		$('#dataList').datagrid('load' ,serializeForm($('#mysearch')));
	});
	
	/**
	 * 重置按钮
	 */
	$('#btnClear').click(function(){
		$('#mysearch').form('clear');
		$('#dataList').datagrid('load' ,{});
		$('#dataList').datagrid('unselectAll');
	});
});

//js方法：序列化表单 			
function serializeForm(form){
	var obj = {};
	$.each(form.serializeArray(),function(index){
		if(obj[this['name']]){
			obj[this['name']] = obj[this['name']] + ','+this['value'];
		} else {
			obj[this['name']] =this['value'];
		}
	});
	return obj;
}


//扩展easyui表单的验证  
$.extend($.fn.validatebox.defaults.rules, {  
    //验证汉子  
    CHS: {  
        validator: function (value) {  
            return /^[\u0391-\uFFE5]+$/.test(value);  
        },  
        message: '只能输入汉字'  
    },  
    // 电话号码或手机号码  
    phoneAndMobile : {
        validator : function(value) {  
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value) || /^(13|15|18)\d{9}$/i.test(value);  
        },  
        message : '电话号码或手机号码格式不正确'  
    },
    zip : {// 验证邮政编码  
        validator : function(value) {  
            return /^[1-9]\d{5}$/i.test(value);  
        },  
        message : '邮政编码格式不正确'  
    },  
    faxno : {// 验证传真  
        validator : function(value) {  
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);  
        },  
        message : '传真号码不正确'  
    },  
    mobile : {// 验证手机号码  
        validator : function(value) {  
            return /^(13|15|18)\d{9}$/i.test(value);  
        },  
        message : '手机号码格式不正确'  
    },  
    phone : {// 验证电话号码  
        validator : function(value) {  
            return /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/i.test(value);  
        },  
        message : '格式不正确,请使用下面格式:020-88888888'  
    },
    english : {// 验证英语  
        validator : function(value) {  
            return /^[A-Za-z]+$/i.test(value);  
        },  
        message : '请输入英文'  
    },
    idcard : {// 验证身份证  
        validator : function(value) {  
            return /^\d{15}(\d{2}[A-Za-z0-9])?$/i.test(value) || /^\d{18}(\d{2}[A-Za-z0-9])?$/i.test(value);  
        },  
        message : '身份证号码格式不正确'  
    }
});



/*
 获取form对象
 usage:
 searchForm 为form 的 id
 var params = getFormValues("#searchForm");
 */
function getFormValues(id) {
    var params = {};
    var form = $(id).serializeArray();

    $.each(form, function () {
        if (params[this.name]) {
            if (params[this.name].push) {
                params[this.name] = [params[this.name]];
            }
            params[this.name].push(this.value || '');
        } else {
            params[this.name] = this.value || '';
        }
    });

    return params;
}

/**
 * 将 javascript object 转换为 array
 * @param obj javascript object
 * @returns {*} array
 */
function obj2array(obj) {

    var a = [];

    a = $.map(obj, function (value, index) {
        return [value];
    });

    return a;
}

function string2date(sDateTime) {
    return new Date(Date.parse(sDateTime.replace(/-/g, "/")));
}

/**
 *	是否 
 */
function yesOrNo(val) {
	if(val == 0){
		return "<span style=\"color:red;\">否</span>";  
	}else if(val == 1){
		return "<span style=\"color:green;\">是</span>";
	}	
};



/* 格式化日志*/
function formatDate(val, row, index) {
    if (val == null || val == '') {
        return '';
    }
    var str = val.substring(0,val.length-2);
    return str;
}

/*格式化数字 */
function formatNumber(val, row, index){
	var aa = parseInt(val);
	return aa.toFixed(0);
}
/*格式化数字(两位小数) */
function formatNumberTwo(val, row, index) {
	if(val == null){
		return "0.00";
	}
	var aa = parseInt(val);
	return aa.toFixed(2);
}



/*格式化返回checkbox，并判断是否选中*/
function isCheck(val, row, index) {
	if (val != 1) {
		return '<input type="checkbox" onclick="return false"></input>';
	}
	return '<input type="checkbox" checked="checked" onclick="return false"></input>';
};


//Ajax请求是覆盖body
function ajaxLoading() {
	$("<div class=\"datagrid-mask\"></div>").css({
		display : "block",
		width : "100%",
		height : $(window).height()
	}).appendTo("body");
	$("<div class=\"datagrid-mask-msg\"></div>").html("Loading...")
			.appendTo("body").css({
				display : "block",
				left : ($(document.body).outerWidth(true) - 190) / 2,
				top : ($(window).height() - 45) / 2
			});
}

//Ajax请求是释放body
function ajaxLoadEnd() {
	$(".datagrid-mask").remove();
	$(".datagrid-mask-msg").remove();
}




