package com.zkxh.demo.common.global.xss;

import org.apache.commons.lang.StringUtils;

/**
 * @ClassName SQLFilter
 * @Description SQL过滤
 * @Auther lifeng
 * @DATE 2018/8/17 15:44
 * @Vserion v0.0.1
 */

public class SQLFilter {


    /**
     * SQL注入过滤
     *
     * @param str 待验证的字符串
     */
    public static String sqlInject(String str) throws Exception {
        if (StringUtils.isBlank(str)) {
            return null;
        }
        //去掉'|"|;|\字符
        str = StringUtils.replace(str, "'", "");
        str = StringUtils.replace(str, "\"", "");
        str = StringUtils.replace(str, ";", "");
        str = StringUtils.replace(str, "\\", "");

        //转换成小写
        str = str.toLowerCase();

        //非法字符
        String[] keywords = {"master", "truncate", "insert", "select", "delete", "update", "declare", "alert", "drop"};

        //判断是否包含非法字符
        for (String keyword : keywords) {
            if (str.indexOf(keyword) != -1) {
                throw new Exception("包含非法字符");
            }
        }

        return str;
    }


}
