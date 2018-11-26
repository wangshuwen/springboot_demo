package com.zkxh.demo.common.page;

import com.zkxh.demo.common.enums.ResultEnum;

/**
 * @ClassName NotSupportedException
 * @Description
 * @Auther lifeng
 * @DATE 2018/11/23 16:46
 * @Vserion v0.0.1
 */

public class NotSupportedException extends RuntimeException {

    /**
     * @param [msg]
     * @return
     * @description 自定义异常信息
     * @date 16:47 2018/11/23
     * @auther lifeng
     **/
    public NotSupportedException(String msg) {
        super(msg);
    }
}
