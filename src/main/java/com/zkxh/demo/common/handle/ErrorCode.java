package com.zkxh.demo.common.handle;

public enum ErrorCode {

    OK(1, "操作成功"),
    ERROR(0, "操作失败"),
    RESOURCE_NOT_FOUND(00000, "您所请求的资源不存在"),
    UNKNOWN_ERROR(40000, "服务器繁忙，请重试"),
    DATA_ERROR(40001, "加载失败，请重试"),
    NOT_FOUND(40004, "找不到文件"),
    INVALID_PARAM(40007, "无效参数"),
    NO_DATA(5000, "没有找到数据"),
    CONVERT_ERROR(5001, "格式刷数据失败！");

    private int code;
    private String info;

    ErrorCode(int code, String info) {
        this.code = code;
        this.info = info;
    }

    public static ErrorCode getByCode(int code) {
        ErrorCode[] values = ErrorCode.values();
        for (ErrorCode value : values) {
            if (value.getCode() == code)
                return value;
        }
        return null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


}
