package com.zkxh.demo.common.handle;

public enum ErrorCode {

    OK(1, "success", "操作成功"),
    ERROR(0, "fail", "操作失败"),
    RESOURCE_NOT_FOUND(00000, "resource_not_found!", "您所请求的资源不存在"),
    UNKNOWN_ERROR(40000, "unknown_error!", "服务器繁忙，请重试"),
    DATA_ERROR(40001, "data_error!", "加载失败，请重试"),
    NOT_FOUND(40004, "not found.", "找不到文件"),
    INVALID_PARAM(40007, "invalid_param", "无效参数"),
    NO_DATA(5000, "not found data.", "没有找到数据");

    private int code;
    private String info;
    private String description;

    ErrorCode(int code, String info, String description) {
        this.code = code;
        this.info = info;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}