package com.wupeng.demo.enumerate;

/**
 * 自定义异常Enum类
 * */
public enum ResultEnum {

    /**
     * 自定义枚举参数
     * */
    UNKNOW_ERROR(-1,"系统错误"),
    SUCCESS(0,"成功"),
    ERROR(1,"失败"),
    DATE_IS_EXCEPTION(2,"数据信息异常"),
    LIST_IS_EXCEPTION(3,"LIST集合数据异常"),
    MAP_IS_EXCEPTION(301,"MAP集合数据异常"),
    SET_IS_EXCEPTION(302,"SET集合数据异常"),
    XML_ANALUYSIS_EXCEPTION(303,"XML解析异常"),
    ;
    private Integer code;
    private String  msg;

    ResultEnum(Integer code,String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

}
