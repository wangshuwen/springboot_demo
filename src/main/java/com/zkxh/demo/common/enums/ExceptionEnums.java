package com.zkxh.demo.common.enums;

/**
 * @ClassName ExceptionEnums
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/19 12:25
 * @Vserion v0.0.1
 */

public enum ExceptionEnums {

    UNKNOW_ERROR(1, "未知错误！"),
    NULL_POINT_ERROR(501, "空指针异常！"),
    SUCCESS(100, "处理成功！"),
    FAILED(101, "处理失败！"),
    NO_PERMISSION(102, "没有权限！"),
    REJECT_REQUEST(103, "拒绝请求！"),
    LOGIN_FAILE(104, "登录失败！"),
    NOT_IS_STAFF(-1, "非本公司员工！");

    private Integer code;
    private String msg;


    ExceptionEnums(Integer code) {
        this.code = code;
    }

    ExceptionEnums(String msg) {
        this.msg = msg;
    }

    ExceptionEnums(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
