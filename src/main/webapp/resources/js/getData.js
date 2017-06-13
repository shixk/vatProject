/**
 * 设置行项目: className:datagrid的class ,url:请求Url
 */
function getBTCPItems (className, url)
{
	$ ('.' + className + '').datagrid (
	{
	    url : url,
	    title : '行项目',
	    columns : [
		    [
		            {
		                field : 'ItemID',
		                title : '行项目编号',
		                width : 100
		            },
		            {
		                field : 'DeatLike',
		                title : '产品组',
		                width : 100
		            },
		            {
		                field : 'RefText',
		                title : '物料',
		                width : 100
		            },
		            {
		                field : 'ItemName',
		                title : '商品名称',
		                width : 190
		            },
		            {
		                field : 'OrdQty',
		                title : '商品数量',
		                width : 100
		            },
		            {
		                field : 'Unit',
		                title : '单位',
		                width : 100
		            },
		            {
		                field : 'FactoryCode',
		                title : '工厂',
		                width : 100
		            },
		            {
		                field : 'WarehouseCode',
		                title : '库存地',
		                width : 100
		            },
		            {
		                field : 'StorageCode',
		                title : '仓库',
		                width : 100
		            },
		            {
		                field : 'Price',
		                title : '单价',
		                width : 100
		            },
		            {
		                field : 'AmountSku',
		                title : '物料总金额',
		                width : 100
		            },
		            {
		                field : 'DiscountPrice',
		                title : '折扣总金额',
		                width : 100
		            },
		            {
		                field : 'DiscountRate',
		                title : '折扣比率',
		                width : 100
		            },
		            {
		                field : 'IsPhysical',
		                title : '是否实物',
		                width : 100
		            },
		            {
		                field : 'IsServiceProd',
		                title : '是否服务',
		                width : 100
		            },
		            {
		                field : 'MachineSN',
		                title : '主机编号',
		                width : 100
		            },
		            {
		                field : 'GroupingID',
		                title : '组号',
		                width : 100
		            },
		            {
		                field : 'IsGift',
		                title : '是否赠品',
		                width : 100
		            },
		            {
		                field : 'IsLenovoProduct',
		                title : '是否自营商品',
		                width : 100
		            },
		            {
		                field : 'Point',
		                title : '积分',
		                width : 100
		            }
		    ]
	    ],
	    rownumbers : true,
	    striped : true,
	    singleSelect : true,
	    loadMsg : '数据加载中...',
	    pagination : false,
	    loadFilter : function (d)
	    {
		    if (d != '')
		    {
			    var data = eval ('(' + d + ')');
			    var rows = [];
			    // var total = data.total;//总页数
			    var dataRows = data.rows;// 每页数据
			    
			    for (var i = 0; i < dataRows.length; i++)
			    {
				    rows.push (
				    {
				        ItemID : dataRows[i].itemID,
				        DeatLike : dataRows[i].deatLike,
				        RefText : dataRows[i].refText,
				        ItemName : dataRows[i].itemName,
				        OrdQty : dataRows[i].ordQty,
				        Unit : dataRows[i].unit,
				        FactoryCode : dataRows[i].factoryCode,
				        WarehouseCode : dataRows[i].warehouseCode,
				        StorageCode : dataRows[i].storageCode,
				        Price : dataRows[i].price,
				        DeatLike : dataRows[i].deatLike,
				        
				        AmountSku : dataRows[i].amountSku,
				        DiscountPrice : dataRows[i].discountPrice,
				        DiscountRate : dataRows[i].discountRate,
				        IsPhysical : dataRows[i].isPhysical,
				        IsServiceProd : dataRows[i].isServiceProd,
				        MachineSN : dataRows[i].machineSN,
				        GroupingID : dataRows[i].groupingID,
				        IsGift : dataRows[i].isGift,
				        IsLenovoProduct : dataRows[i].isLenovoProduct,
				        Point : dataRows[i].point
				    });
			    }
			    var newData =
			    {
				    // "total" : total,
				    "rows" : rows
			    };
			    return newData;
		    }
	    }
	});
}

/**
 * @param id
 *            下拉框ID
 * @param id
 *            接口地址-需要查询的物料号的btcpso
 */
function getMaterialData (id, url)
{
	$.ajax (
	{
	    type : "Post",
	    url : url,
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
			        paravalue : data[i].refText,
			        paracode : data[i].refText
			    });
		    }
		    var da = new Object ();
		    da.paracode = "";
		    da.paravalue = "";
		    rows.unshift (da);
		    $ ('#' + id + '').combobox (
		    {
		        data : rows,
		        valueField : 'paracode',
		        textField : 'paravalue',
		        panelHeight : 'auto'
		    });
	    }
	});
}
	/****
	 * mark 1 产品组 2 库存地  3支付类型
	 * id  当前下拉框的id
	 * url  对应当前路径的访问url
	 */
	function getDropValues (mark,id, url)
	{
		
        url = url+"?mark="+mark;	// 参数。。。。
		$.ajax (
		{
		    type : "Post",
		    url : url,
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
				        paravalue : data[i].key2,
				        paracode : data[i].value2
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
		    }
		});
	}	
	
	 /**
	 * @param id  根据ID-绑定订单类型 ZOR ZSD ZRE RE
	 */
	function  getordersType(id){
	    	var rows = [{"paravalue":"ZOR","paracode":"ZOR"},{"paravalue":"ZSD","paracode":"ZSD"},{"paravalue":"ZRE","paracode":"ZRE"},{"paravalue":"RE","paracode":"RE"}];
	    	 var da = new Object ();
			    da.paracode = "";
			    da.paravalue = "全部"; // 设置全部
			    rows.unshift (da);
	    	$ ('#' + id + '').combobox (
		     {
		        data : rows,
		        valueField : 'paracode',
		        textField : 'paravalue',
		        panelHeight : 'auto'
		     });
	     }
	/**
	 *  根据id 来绑定
	 *    P Z D T
	 * */
    function  getInvoiceType(id){
    	var rows = [{"paravalue":"增普票","paracode":"P"},{"paravalue":"增专票","paracode":"Z"},{"paravalue":"电子票","paracode":"D"},{"paravalue":"通用机打发票","paracode":"T"}];
    	 var da = new Object ();
		    da.paracode = "";
		    da.paravalue = "全部"; // 设置全部
		    rows.unshift (da);
    	$ ('#' + id + '').combobox (
	     {
	        data : rows,
	        valueField : 'paracode',
	        textField : 'paravalue',
	        panelHeight : 'auto'
	     });
     }

    /**
     * 根据id 生成是否下拉-参数
     * @param id
     */
    function selById (id)
    {
    	
    var rows = [{"paravalue":"是","paracode":"1"},{"paravalue":"否","paracode":"0"}];
   	 var da = new Object ();
		    da.paracode = "";
		    da.paravalue = "全部"; // 设置全部
		    rows.unshift (da);
   	$ ('#' + id + '').combobox (
	     {
	        data : rows,
	        valueField : 'paracode',
	        textField : 'paravalue',
	        panelHeight : 'auto'
	     });
    }   
