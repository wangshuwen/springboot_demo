package com.zkxh.demo.common.result;

import com.alibaba.fastjson.JSON;

/**
 * 返回 工具类 jsonToString
 *
 * @version 1.0
 * @auther li
 * @date 2018/1/3-11:25
 */
public class ReturnUtil {
    /**
     * @return java.lang.String
     * @Description
     * @params [ResultUtil ]
     * @auther li
     * @date 2018/1/3-14:28
     */
    public static String jsonToString(ResultUtil result) {
        String str = JSON.toJSONString(result);
        return str;
    }

}
