package com.wupeng.demo.util;

import com.wupeng.demo.enumerate.ResultEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 通用Result帮助类
 * */
public class ResultUtil {

    private static final Logger log = LoggerFactory.getLogger(ResultUtil.class);



    /**成功且带数据**/
    public static Result success(Object object){
        Result result = new Result();
        result.setCode(ResultEnum.SUCCESS.getCode());
        result.setMsg(ResultEnum.SUCCESS.getMsg());
        result.setData(object);
        return result;
    }
    /**成功但不带数据**/
    public static Result success(){

        return success(null);
    }
    /**失败**/
    public static Result error(Integer code,String msg){
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }


}
