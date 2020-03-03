/**数据处理*/



/*
 * 校验是否是身份证
 * true 验证通过
 * false 验证失败
 */
isCardNo = function (card) { 
    var reg = /^[1-9]{1}[0-9]{14}$|^[1-9]{1}[0-9]{16}([0-9]|[xX])$/;
    if(reg.test(card)){
        return true;
    }else{
        return false;
    }
}
/**
 * 校验是否是手机号
 * true 通过
 * false 失败
 */
isPhoneNo = function(phone){
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(phone)) {
        return false;
    } else {
        return true;
    }
}






/**
 * 字符串隔开
 * @param  str   带有分隔的字符串
 */
commaPartition = function(str,v){
	return str.split(v);
};

/*
 * 追加
 * @param  json:  空数组
 * @param  key:   键
 * @param  code:  值
 * */
appendJson = function(json,key,code,key1,code1){
	if(typeof(key1)=="undefined" ){
		var newJson = '{"' + key +'":"' + code + '"}';
	}else{
		var newJson = '{"' + key +'":"' + code + '","' + key1 +'":"' + code1 + '"}';
	}
	
    json.push(JSON.parse(newJson));  
    return json;
};



 var lunar = {
    tg: '甲乙丙丁戊己庚辛壬癸',
    dz: '子丑寅卯辰巳午未申酉戌亥',
    number: '一二三四五六七八九十',
    year: '鼠牛虎兔龙蛇马羊猴鸡狗猪',
    month: '正二三四五六七八九十冬腊',
    monthadd: [0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334],
    calendar: [0xA4B, 0x5164B, 0x6A5, 0x6D4, 0x415B5, 0x2B6, 0x957, 0x2092F, 0x497, 0x60C96, 0xD4A, 0xEA5, 0x50DA9, 0x5AD, 0x2B6, 0x3126E, 0x92E, 0x7192D, 0xC95, 0xD4A, 0x61B4A, 0xB55, 0x56A, 0x4155B, 0x25D, 0x92D, 0x2192B, 0xA95, 0x71695, 0x6CA, 0xB55, 0x50AB5, 0x4DA, 0xA5B, 0x30A57, 0x52B, 0x8152A, 0xE95, 0x6AA, 0x615AA, 0xAB5, 0x4B6, 0x414AE, 0xA57, 0x526, 0x31D26, 0xD95, 0x70B55, 0x56A, 0x96D, 0x5095D, 0x4AD, 0xA4D, 0x41A4D, 0xD25, 0x81AA5, 0xB54, 0xB6A, 0x612DA, 0x95B, 0x49B, 0x41497, 0xA4B, 0xA164B, 0x6A5, 0x6D4, 0x615B4, 0xAB6, 0x957, 0x5092F, 0x497, 0x64B, 0x30D4A, 0xEA5, 0x80D65, 0x5AC, 0xAB6, 0x5126D, 0x92E, 0xC96, 0x41A95, 0xD4A, 0xDA5, 0x20B55, 0x56A, 0x7155B, 0x25D, 0x92D, 0x5192B, 0xA95, 0xB4A, 0x416AA, 0xAD5, 0x90AB5, 0x4BA, 0xA5B, 0x60A57, 0x52B, 0xA93, 0x40E95]
}
	 
/***
 * 日期转农历  
 * 参数date:2019-3-7
 * @param {Object} date
 * 
 */
getLunarDate = function (date) {var year, month, day;if (!date) {date = new Date(), year = date.getFullYear(), month = date.getMonth(), day = date.getDate();} else {date = date.split('-'), year = parseInt(date[0]), month = date[1] - 1, day = parseInt(date[2]);}if (year < 1921 || year > 2020) {return {}}var total, m, n, k, bit, lunarYear, lunarMonth, lunarDay;var isEnd = false;var tmp = year;if (tmp < 1900) {tmp += 1900;}total = (tmp - 1921) * 365 + Math.floor((tmp - 1921) / 4) + lunar.monthadd[month] + day - 38;if (year % 4 == 0 && month > 1) {total++;}for (m = 0;; m++) {k = (lunar.calendar[m] < 0xfff) ? 11 : 12;for (n = k; n >= 0; n--) {bit = (lunar.calendar[m] >> n) & 1;if (total <= 29 + bit) {isEnd = true;break;}total = total - 29 - bit;}if (isEnd) break;}lunarYear = 1921 + m;lunarMonth = k - n + 1;lunarDay = total;if (k == 12) {if (lunarMonth == Math.floor(lunar.calendar[m] / 0x10000) + 1) {lunarMonth = 1 - lunarMonth;}if (lunarMonth > Math.floor(lunar.calendar[m] / 0x10000) + 1) {lunarMonth--;}};var obj = {lunarYear: lunarYear,lunarMonth: lunarMonth,lunarDay: lunarDay,};return getLunarDateString(obj);}
function getLunarDateString(lunarDate) {if (!lunarDate.lunarDay) return;var data = {},lunarYear = lunarDate.lunarYear,lunarMonth = lunarDate.lunarMonth,lunarDay = lunarDate.lunarDay;data.tg = lunar.tg.charAt((lunarYear - 4) % 10);data.dz = lunar.dz.charAt((lunarYear - 4) % 12);data.year = lunar.year.charAt((lunarYear - 4) % 12);data.month = lunarMonth < 1 ? '(闰)' + lunar.month.charAt(-lunarMonth - 1) : lunar.month.charAt(lunarMonth - 1);data.day = (lunarDay < 11) ? "初" : ((lunarDay < 20) ? "十" : ((lunarDay < 30) ? "廿" : "三十"));if (lunarDay % 10 != 0 || lunarDay == 10) {data.day += lunar.number.charAt((lunarDay - 1) % 10);}return data;}

/**日期转农历   end*/

	
	
	
	
	


/**
 * 时间戳转日期
 * */
stampChangeTime = function(stamp){
 	var date = new Date(stamp);
    var y = date.getFullYear();  
    var m = date.getMonth() + 1;  
    m = m < 10 ? ('0' + m) : m;  
    var d = date.getDate();  
    d = d < 10 ? ('0' + d) : d;  
    var h = date.getHours();
    h = h < 10 ? ('0' + h) : h;
    var minute = date.getMinutes();
    var second = date.getSeconds();
    minute = minute < 10 ? ('0' + minute) : minute;  
    second = second < 10 ? ('0' + second) : second; 
    var model = {
    	year:y,
    	month:parseInt(m),
    	months:m,
    	day:d,
    	hour:h,
    	minute:minute,
    	second:second,
    	obj1:y + '-' + m + '-' + d+' '+h+':'+minute+':'+second,
    	obj4: y + '-' + m + '-' + d + ' ' + h + ':' + minute,
    	obj11:y + '年' + m + '月' + d+'日 '+h+'时'+minute+'分'+second + '秒',
    	obj111:y + '年' + m + '月' + d+'日 '+h+':'+minute,
    	obj2: y + "年" + m + "月" + d  + "日",
    	obj22: y + "-" + m + "-" + d,
    	obj3: m + "月" + d + "日",
    	obj33: m + "-" + d,
    	week: getWeek( y + "-" + m + "-" + d)
    }  
    /**农历*/
    var lunarModel = getLunarDate(model.obj22);
    lunarModel.obj1 = lunarModel.month + "月" + lunarModel.day;
    lunarModel.obj2 = lunarModel.tg + lunarModel.dz + "年";
    model.lunarModel = lunarModel;
    return model;  

};

/**星期*/
getWeek = function(dateString){
    var date;
    if(dateString == null){
        date = new Date();
    }else{
        var dateArray = dateString.split("-");
        date = new Date(dateArray[0], parseInt(dateArray[1] - 1), dateArray[2]);
    }
    return "星期" + "日一二三四五六".charAt(date.getDay());
};






/**倒计时秒*/
countDownSecond = function(second,func){
	var hour = 0;
	var minute = 0;
	var time=new Date();
	time.setHours(hour);
	time.setMinutes(minute);
	time.setSeconds(second);
	var timeout;
	console.log(time);
	var timeshow=document.getElementById("countDownSecond");
	function countdown(){
		var hour=time.getHours();
		var min=time.getMinutes();
		var second=time.getSeconds();
		if(hour=="0"&&min=="0"&&second=="0"){
			//timeOut();
			if(func){
				func();
			}
			clearInterval(timeout);
		}
		time.setSeconds(second-1);
		hour<10?hour="0"+hour:hour;
		min<10?min="0"+min:min;
		second<10?second="0"+second:second;
		timeshow.innerHTML=second;
	}
	timeout= setInterval(countdown,1000);
};
/**倒计时时分秒*/
countDownHourMinuteSecond = function(hour,minute,second,func){
	
	var time=new Date();
	time.setHours(hour);
	time.setMinutes(minute);
	time.setSeconds(second);
	var timeout;
	console.log(time);
	var timeshow=document.getElementById("countDownHourMinuteSecond");
	function countdown(){
		var hour=time.getHours();
		var min=time.getMinutes();
		var second=time.getSeconds();
		if(hour=="0"&&min=="0"&&second=="0"){
			//timeOut();
			if(func){
				func();
			}
			clearInterval(timeout);
		}
		time.setSeconds(second-1);
		hour<10?hour="0"+hour:hour;
		min<10?min="0"+min:min;
		second<10?second="0"+second:second;
		timeshow.innerHTML=hour+":"+min+":"+second;
	}
	timeout= setInterval(countdown,1000);
};

/*gx*/

/**
 * 将传入的秒数转换成对应的时长
 * @param {Object} second ： 要处理的秒数
 * return ：当处理出现异常时返回null
 */
timeProcessing = function(second) {
	var time = "";
	//时间显示处理
	try{
		if (second == null) {
			return "0秒";
		}
		if (second < 60) {
			time = second + "秒";
		}else{
			if (second % 60 != 0) {
				time = second % 60 + "秒";
				second -= second % 60;
			}
			var minute = second / 60;
			if (minute % 60 != 0) {
				time = minute % 60 + "分种" + time;
			}
			if(minute >= 60){
				time = minute % 60 + "小时" + time;
			}
		}
	}catch(e){
		return null;
	}
	return time;
}