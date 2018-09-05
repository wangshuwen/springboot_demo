package com.zkxh.demo.common.enums;

public enum ResultEnum {
    UNKNOW_ERROR(-100, "未知错误！"),
    NULL_POINT_ERROR(501, "空指针异常！"),
    SUCCESS(100, "处理成功！"),
    FAILED(101, "处理失败！"),
    NO_PERMISSION(102, "没有权限！"),
    REJECT_REQUEST(103, "拒绝请求！"),
    LOGIN_FAILE(104, "登录失败！"),
    NOT_IS_STAFF(-1, "非本公司员工！"),
    IS_NOT_LOGIN(105, "未登录，请先登录！"),
    TOKEN_ERROR(106, "token错误，请重试！"),
    UN_ANTUORIZED(107, "Unauthorized，操作失败，无操作权限！"),
    USER_IS_LOCKED(108, "账号已被冻结！"),
    PWD_IS_ERROR(109, "密码错误！"),
    USER_NOT_EXIST(109, "用户不存在！"),
    ;

    private Integer code;
    private String msg;


    ResultEnum(Integer code) {
        this.code = code;
    }

    ResultEnum(String msg) {
        this.msg = msg;
    }

    ResultEnum(Integer code, String msg) {
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
