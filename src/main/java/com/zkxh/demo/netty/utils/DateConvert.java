package com.zkxh.demo.netty.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DateConvert
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/17 9:08
 * @Vserion v0.0.1
 */

public class DateConvert {

    private static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyy-MM-dd");
    private static final SimpleDateFormat yyyyMMddHHmmss = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat yyyyMMddHHmmss_t = new SimpleDateFormat("yyyyMMddHHmmss");
    private static final SimpleDateFormat HHmmss = new SimpleDateFormat("HH:mm:ss");
    private static final int T_YYYY_MM_DD = 10;
    private static final int T_yyyyMMddHHmmss = 19;
    private static final int T_HHmmss = 8;

    public static String convert(Date source, int type) {
        switch (type) {
            case 10:
                return yyyyMMdd.format(source);
            case 15:
                return yyyyMMddHHmmss_t.format(source);
            case 19:
                return yyyyMMddHHmmss.format(source);
            case 8:
                return HHmmss.format(source);
            default:
                return yyyyMMddHHmmss.format(source);
        }
    }

    public static Date convertStringToDate(String s, int type) throws ParseException {
        switch (type) {
            case 10:
                return yyyyMMdd.parse(s);
            case 19:
                return yyyyMMddHHmmss.parse(s);
            case 8:
                return HHmmss.parse(s);
            default:
                return yyyyMMddHHmmss.parse(s);
        }
    }
}
