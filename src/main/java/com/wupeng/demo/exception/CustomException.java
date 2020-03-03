package com.wupeng.demo.exception;

import com.wupeng.demo.enumerate.ResultEnum;

public class CustomException extends RuntimeException {

    private static final long serialVersionUID = 1L;


    private  Integer code;

    public CustomException(ResultEnum resultEnum){
        super(resultEnum.getMsg());
        this.code = resultEnum.getCode();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

}
