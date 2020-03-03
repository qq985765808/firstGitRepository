package com.wupeng.demo.exception;

import com.wupeng.demo.util.Result;
import com.wupeng.demo.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;

/**
 * 全局控制类异常处理类
 * */
@ControllerAdvice
public class GlobalDefultExceptionHandler implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(GlobalDefultExceptionHandler.class);

    //声明要捕获的异常
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public <T> Result<?> defaultExcepitonHandler(HttpServletRequest request, Exception e) {
        e.printStackTrace();
        if(e instanceof CustomException) {
            log.error("业务异常："+e.getMessage(),this.getClass());
            CustomException customException = (CustomException)e;
            return ResultUtil.error(customException.getCode(), customException.getMessage());
        }else if(e instanceof ArrayIndexOutOfBoundsException ){
            log.error("数据异常："+e.getMessage(),this.getClass());
            return ResultUtil.error(3, "下标越界");
        }
        //未知错误
        log.error("系统异常:"+e);
        return ResultUtil.error(-1, "系统异常");
    }

}
