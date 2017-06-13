
/**
 * 清空\r\n
 * @param val
 * @returns
 */
function cleanrn(val){
	val = val.replace(/\r/g,","); /// 替换内容 /g
	val = val.replace(/\n/g,",");
	val = val.replace(/\r\n/g,",");
	return val;
}


/*
 * UUID生成代码
 */
function getUUID() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+"-"+S4()+"-"+S4()+"-"+S4()+"-"+S4()+S4()+S4());
}

/**
 * 增专信封操作状态
 * 0：已输出(有发票号)
 * 1：已打印(打印或导出)
 * 2：已返填(返填面单号)
 * 4：已推送(推送BTCP)
 * @param mailStatus
 */
function exchageMailStatus(mailStatus){
	switch (mailStatus) {
	case -1: return "已输出(有发票号)";
	case 0: return "已汇总(未处理)";
	case 1: return "已打印(打印或导出)";
	case 2: return "已返填(有面单号)";
	case 3: return "已打印+已返填";
	case 4: return "异常(1+3)";
	case 5: return "异常(1+4)";
	case 6: return "异常(2+4)";
	case 7: return "已推送(推送BTCP)";
	}
	return "未知状态("+mailStatus+")";
}

//单据状态
function invoiceSatusFormatter(value,row,index){
	if(row!=null){
		if(row.out == '0'){
			return '未处理';
		}else if(row.out == '1'){
			return '已输出';
		}else if(row.out == '2'){
			return '已废弃';
		}else if(row.out == '3'){
			return '已修改';
		}else if(row.out == '4'){
			return '已合并';
		}else if(row.out == '5'){
			return '已撤单';
		}else {
			return '未知';
		}
	}
}

//发票类型
function invoiceTypeFormatter(value,row,index){
	if(row!=null){
		if(row.flag_ValueAddedTax == 'TP'){
			return '增普';
		}else if(row.flag_ValueAddedTax == 'TD'){
			return '电子票';
		}else if(row.flag_ValueAddedTax == 'TZ'){
			return '增专';
		}else {
			return '未知('+row.flag_ValueAddedTax+')';
		}
	}
}