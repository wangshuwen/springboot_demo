package com.zkxh.demo.controller.staff;

/**
 * @ClassName Test
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/19 15:54
 * @Vserion v0.0.1
 */

public class Test {
    /**
     * @param [args]
     * @return void
     * @description 切割IP字符串 测试
     * @date 18:51 2018/10/29
     * @auther lifeng
     **/
    public static void main(String[] args) {
        String ips = "1.168.2";

        String src[] = ips.split("\\.");

        for (String s : src) {
            System.out.println(s);
        }
//        System.out.println(src[1]);
    }
}
