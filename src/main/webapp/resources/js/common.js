var RE = /^[0-9]*[1-9][0-9]*$/;// js判断是否为正整数

/**
 * 获取当前时间
 */
function getNowTime ()
{
	var n = new Date ();
	n.setDate (n.getDate ());
	// console.log (n.getFullYear () + "-" + (n.getMonth () + 1) + "-" +
	// n.getDate ());
	return n.getFullYear () + "-" + (n.getMonth () + 1) + "-" + n.getDate ();
}

/**
 * 获取过去时间
 * 
 * @param daysNum(天数)
 */
function getPastTime (daysNum)
{
	// console.log (daysNum);
	if (daysNum != null && daysNum != '')
	{
		var n = new Date ();
		n.setDate (n.getDate () - daysNum);
		// console.log (n.getFullYear () + "-" + (n.getMonth () + 1) + "-" +
		// n.getDate ());
		return n.getFullYear () + "-" + (n.getMonth () + 1) + "-" + n.getDate ();
	}
}

/**
 * 类型转换(0和1) by:yangzonglei
 * 
 * @param typeFormat
 * @param zero
 * @param one
 * @returns {String}
 */
function whether_TypeFormat (typeFormat, zero, one)
{
	if (typeFormat == null)
	{
		return 'NaN..';
	}
	else
	{
		var str = '';
		if (typeof typeFormat === 'number')
		{
			if (typeFormat == 0)
			{
				str = zero;
			}
			else if (typeFormat == 1)
			{
				str = one;
			}
		}
		else if (typeof typeFormat === 'string')
		{
			if (typeFormat == zero)
			{
				str = 0;
			}
			else if (typeFormat == one)
			{
				str = 1;
			}
		}
		return str;
	}
}

/**
 * 订单来源转换
 * 
 * @param formatType
 * @returns {String}
 */
function dataSourcing_TypeFormat (formatType)
{
	if (formatType == null)
	{
		return 'NaN..';
	}
	else
	{
		var str = '1';
		
		// 开始是想从服务器取数据,可以每次都需要交互,所以直接写死数据
		var soucing = '[{"paravalue":"官网","paracode":"0"},{"paravalue":"Epack","paracode":"15"},{"paravalue":"人人微店","paracode":"1"},{"paravalue":"游戏平台","paracode":"2"},{"paravalue":"京东","paracode":"3"},{"paravalue":"苏宁","paracode":"4"},{"paravalue":"ThinkWorld","paracode":"5"},{"paravalue":"EPP","paracode":"6"},{"paravalue":"官网闪购","paracode":"7"},{"paravalue":"APP","paracode":"8"},{"paravalue":"APP闪购","paracode":"9"},{"paravalue":"微信商城","paracode":"10"},{"paravalue":"微信商城闪购","paracode":"11"},{"paravalue":"客户端触屏","paracode":"12"},{"paravalue":"客户端触屏闪购","paracode":"13"},{"paravalue":"元庆微信店","paracode":"14"},{"paravalue":"MBG","paracode":"16"},{"paravalue":"ThinkPC","paracode":"17"},{"paravalue":"ThinkWAP","paracode":"18"},{"paravalue":"ThinkAPP","paracode":"19"},{"paravalue":"TMALL","paracode":"20"},{"paravalue":"NBD","paracode":"21"}]';
		
		var data = eval (soucing);
		
		if (typeof formatType === 'number')
		{
			for ( var p in data)
			{
				if (data[p].paracode == formatType)
				{
					str = data[p].paravalue;
				}
			}
		}
		else if (typeof formatType === 'string')
		{
			for ( var p in data)
			{
				if (data[p].paravalue == formatType)
				{
					str = data[p].paracode;
				}
			}
		}
		
		return str;
	}
}

/**
 * 销售类型转换
 * 
 * @param formatType
 * @returns {String}
 */
function salesType_TypeFormat (formatType)
{
	if (formatType == null)
	{
		return 'NaN..';
	}
	else
	{
		var str = '1';
		
		// 开始是想从服务器取数据,可以每次都需要交互,所以直接写死数据
		var soucing = '[{"paravalue":"普通","paracode":"0"},{"paravalue":"闪购","paracode":"1"},{"paravalue":"预售","paracode":"2"},{"paravalue":"先试后买","paracode":"3"},{"paravalue":"定制","paracode":"4"}]';
		
		var data = eval (soucing);
		
		if (typeof formatType === 'number')
		{
			for ( var p in data)
			{
				if (data[p].paracode == formatType)
				{
					str = data[p].paravalue;
				}
			}
		}
		else if (typeof formatType === 'string')
		{
			for ( var p in data)
			{
				if (data[p].paravalue == formatType)
				{
					str = data[p].paracode;
				}
			}
		}
		
		return str;
	}
}

/**
 * JS对象转成String类型 by:yangzonglei time:20150902
 * 
 * @param o
 *            对象
 * @returns
 */
function objConvertStr (o)
{
	if (o == undefined)
	{
		return "";
	}
	var r = [];
	if (typeof o == "string")
		return "\""
		        + o.replace (/([\"\\])/g, "\\$1").replace (/(\n)/g, "\\n").replace (/(\r)/g, "\\r").replace (/(\t)/g,
		                "\\t") + "\"";
	if (typeof o == "object")
	{
		if (!o.sort)
		{
			for ( var i in o)
				r.push ("\"" + i + "\":" + objConvertStr (o[i]));
			if (!!document.all
			        && !/^\n?function\s*toString\(\)\s*\{\n?\s*\[native code\]\n?\s*\}\n?\s*$/.test (o.toString))
			{
				r.push ("toString:" + o.toString.toString ());
			}
			r = "{" + r.join () + "}"
		}
		else
		{
			for (var i = 0; i < o.length; i++)
				r.push (objConvertStr (o[i]))
			r = "[" + r.join () + "]";
		}
		return r;
	}
	return o.toString ().replace (/\"\:/g, '":""');
}

function getPayment (id)
{
	$.ajax (
	{
	    type : "Post",
	    url : "../../../Email/getPayment.do",
	    datatype : "json",
	    contentType : "application/json; charset=utf-8",
	    success : function (d)
	    {
		    var data = eval (d);
		    var rows = [];
		    for (var i = 0; i < data.length; i++)
		    {
			    rows.push (
			    {
			        paravalue : data[i].paravalue,
			        paracode : data[i].paracode
			    });
		    }
		    var da = new Object ();
		    da.paracode = "-1";
		    da.paravalue = "全部";
		    rows.unshift (da);
		    $ ('#' + id + '').combobox (
		    {
		        data : rows,
		        valueField : 'paracode',
		        textField : 'paravalue'
		    });
	    }
	});
}
/**
 * 获取订单涞源 by:yangzonglei,time:20150804
 */
function getSoucingData (id)
{
	$.ajax (
	{
	    type : "Post",
	    url : "../../../Email/getSoucing.do",
	    datatype : "json",
	    contentType : "application/json; charset=utf-8",
	    success : function (d)
	    {
		    var data = eval (d);
		    var rows = [];
		    for (var i = 0; i < data.length; i++)
		    {
			    rows.push (
			    {
			        paravalue : data[i].paravalue,
			        paracode : data[i].paracode
			    });
		    }
		    var da = new Object ();
		    da.paracode = "";
		    da.paravalue = "全部";
		    rows.unshift (da);
		    $ ('#' + id + '').combobox (
		    {
		        data : rows,
		        valueField : 'paracode',
		        textField : 'paravalue'
		    });
		    // $('#' + id + '').combobox('setValue', '全部');
		    $('#' + id + '').combobox('select', '');
	    }
	});
}

// 两级获取订单来源数据
function getSoucingData2 (id)
{
	$.ajax (
	{
	    type : "Post",
	    url : "../../Email/getSoucing.do",
	    datatype : "json",
	    contentType : "application/json; charset=utf-8",
	    success : function (d)
	    {
		    var data = eval (d);
		    var rows = [];
		    for (var i = 0; i < data.length; i++)
		    {
			    rows.push (
			    {
			        paravalue : data[i].paravalue,
			        paracode : data[i].paracode
			    });
		    }
		    var da = new Object ();
		    da.paracode = "";
		    da.paravalue = "全部";
		    rows.unshift (da);
		    $ ('#' + id + '').combobox (
		    {
		        data : rows,
		        valueField : 'paracode',
		        textField : 'paravalue'
		    });
		    // $('#' + id + '').combobox('setValue', '全部');
		    $('#' + id + '').combobox('select', '');
	    }
	});
}
/**
 * 设置combobox的值(全部,是,否)
 * 
 * @param id
 */
function setYN (className)
{
	var d = '[{"text":"","value":"全部"},{"text":"1","value":"是"},{"text":"0","value":"否"}]';
	
	$ ('.' + className + '').combobox (
	{
	    data : eval (d),
	    valueField : 'text',
	    textField : 'value'
	});
	// $('.' + className + '').combobox('setValue', '全部');
}

/**
 * 初始化日期(一对)
 * 
 * @param startTimeId:开始日期ID
 * @param endTimeId
 *            :结束日期ID
 * @param formatTime
 *            :时间格式
 */
// 初始化 -日期
function intDate_StartToEnd (startTimeId, endTimeId, formatTime)
{
	// 开始日期
	var start =
	{
	    elem : '#' + startTimeId + '',
	    format : '' + formatTime + '',
	    // min: laydate.now(), //设定最小日期为当前日期
	    // max: '2019-06-16 23:59:59', //最大日期
	    istime : true, // 下端显示 小时选择项
	    // istoday : false, // 下端显示 汉字今天 清除确定 (默认)
	    choose : function (datas)
	    {
		    end.min = datas; // 开始日选好后，重置结束日的最小日期
		    end.start = datas; // 将结束日的初始值设定为开始日
	    }
	};
	var end =
	{
	    elem : '#' + endTimeId + '',
	    format : '' + formatTime + '',
	    // min: laydate.now(), 当前的日期
	    // max: '2019-06-16 23:59:59',
	    istime : true, // 下端显示 小时选择项
	    // istoday : true, // 下端显示 汉字今天 清除确定 (默认)
	    choose : function (datas)
	    {
		    start.max = datas; // 结束日选好后，重置开始日的最大日期
	    }
	};
	laydate (start); // 加载-数据
	laydate (end); // 加载-数据
}

/**
 * 初始化日期(单个)
 * 
 * @param TimeId
 *            :时间ID
 * @param formatTime:时间格式
 */
function intDate_Single (TimeId, formatTime)
{
	var start =
	{
	    elem : '#' + TimeId + '',
	    format : '' + formatTime + '',
	    // min: laydate.now(), //设定最小日期为当前日期
	    // max: '2019-06-16 23:59:59', //最大日期
	    istime : true, // 下端显示 小时选择项
	    // istoday : false, // 下端显示 汉字今天 清除确定 (默认)
	    choose : function (datas)
	    {
		    
	    }
	};
	laydate (start); // 加载-数据
}
/**
 * IsRevoked
 * 
 * @param className
 */
function setIsRevoked (className)
{
	var d = '[{"text":"-1","value":"全部"},{"text":"0","value":"未撤单"},{"text":"1","value":"已撤单"}]';
	
	$ ('.' + className + '').combobox (
	{
	    data : eval (d),
	    valueField : 'text',
	    textField : 'value'
	});
	// $('.' + className + '').combobox('setValue', '-1');
	$('.' + className + '').combobox('select', '-1');
	//$('#' + id + '').combobox('select', '');
}

/**
 * 是否需要开票
 * 
 * @param className
 */
function setIsNeedInvoice (className)
{
	var d = '[{"text":"-1","value":"全部"},{"text":"0","value":"不需要开发票"},{"text":"0","value":"需要开发票"}]';
	
	$ ('.' + className + '').combobox (
	{
	    data : eval (d),
	    valueField : 'text',
	    textField : 'value'
	});
	// $('.' + className + '').combobox('setValue', '全部');
}

function closes ()
{
	$ ("#Loading").fadeOut ("normal", function ()
	{
		$ (this).remove ();
	});
}

var pc;
$.parser.onComplete = function ()
{
	if (pc)
		clearTimeout (pc);
	pc = setTimeout (closes, 9);
}

Date.prototype.pattern = function (fmt)
{
	var o =
	{
	    "M+" : this.getMonth () + 1, // 月份
	    "d+" : this.getDate (), // 日
	    "h+" : this.getHours () % 12 == 0 ? 12 : this.getHours () % 12, // 小时
	    "H+" : this.getHours (), // 小时
	    "m+" : this.getMinutes (), // 分
	    "s+" : this.getSeconds (), // 秒
	    "q+" : Math.floor ((this.getMonth () + 3) / 3), // 季度
	    "S" : this.getMilliseconds ()
	// 毫秒
	};
	var week =
	{
	    "0" : "/u65e5",
	    "1" : "/u4e00",
	    "2" : "/u4e8c",
	    "3" : "/u4e09",
	    "4" : "/u56db",
	    "5" : "/u4e94",
	    "6" : "/u516d"
	};
	if (/(y+)/.test (fmt))
	{
		fmt = fmt.replace (RegExp.$1, (this.getFullYear () + "").substr (4 - RegExp.$1.length));
	}
	if (/(E+)/.test (fmt))
	{
		fmt = fmt.replace (RegExp.$1,
		        ((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "/u661f/u671f" : "/u5468") : "")
		                + week[this.getDay () + ""]);
	}
	for ( var k in o)
	{
		if (new RegExp ("(" + k + ")").test (fmt))
		{
			fmt = fmt.replace (RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
			        : (("00" + o[k]).substr (("" + o[k]).length)));
		}
	}
	return fmt;
};

/**
 * 时间戳转成时间 2015/07/27 By:yangzonglei
 * 
 * @param t时间戳
 * @returns 正常时间
 */
function timestampFormatter (t)
{
	// return t == null ? '' : new Date (t).pattern ("yyyy-MM-dd HH:mm:ss");
	if (t == '' || t == null)
	{
		return '';
	}
	else
	{
		var time = '';
		
		if (typeof t === 'number')
		{
			time = t;
		}
		else if (typeof t === 'string')
		{
			time = parseInt (t);
		}
		
		return time == null ? '' : new Date (time).pattern ("yyyy-MM-dd HH:mm:ss");
	}
}

function dataFormatter (t,format){
	// return t == null ? '' : new Date (t).pattern ("yyyy-MM-dd HH:mm:ss");
	if(t == '' || t == null){
		return '';
	} else {
		var time = '';
		
		if (typeof t === 'number'){
			time = t;
		}
		else if (typeof t === 'string'){
			time = parseInt (t);
		}
		return time == null ? '' : new Date (time).pattern (format);
	}
}
/**
 * 克隆对象
 * 
 * @param myObj
 * @returns
 */
function clone (myObj)
{
	if (typeof (myObj) != 'object' || myObj == null)
		return myObj;
	var newObj = new Object ();
	for ( var i in myObj)
	{
		newObj[i] = clone (myObj[i]);
	}
	// console.log(newObj);
	return newObj;
}
