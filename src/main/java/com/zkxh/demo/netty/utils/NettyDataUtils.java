package com.zkxh.demo.netty.utils;

/**
 * @ClassName NettyDataUtils
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/28 16:15
 * @Vserion v0.0.1
 */

public class NettyDataUtils {

    public static byte[] intToByteArray(int num) {
        return new byte[]{
                (byte) ((num >> 24) & 0xFF),
                (byte) ((num >> 16) & 0xFF),
                (byte) ((num >> 8) & 0xFF),
                (byte) (num & 0xFF)
        };
    }

    public static void main(String[] args) {
        byte[] l = NettyDataUtils.intToByteArray(30);
        System.out.println(l[2]);
        System.out.println(l[3]);
    }
}
