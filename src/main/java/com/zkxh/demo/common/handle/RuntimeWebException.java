package com.zkxh.demo.common.handle;

import com.zkxh.demo.common.enums.ResultEnum;

@SuppressWarnings("serial")
public class RuntimeWebException extends RuntimeException {

//	public RuntimeWebException() {
//		super();
//	}
//
//	public RuntimeWebException(String message, Throwable cause) {
//		super(message, cause);
//	}
//
//	public RuntimeWebException(String message) {
//		super(message);
//	}
//
//	public RuntimeWebException(Throwable cause) {
//		super(cause);
//	}

    private Integer code;


    /**
     * 自定义异常代码
     *
     * @param code
     * @param msg
     */
    public RuntimeWebException(Integer code, String msg) {
        super(msg);
        this.code = code;
    }

    /**
     * 事先定义好的，
     * 根据 ResultEnum定义
     *
     * @param resultEnum
     * @see ResultEnum
     */
    public RuntimeWebException(ResultEnum resultEnum) {
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
