package com.zkxh.demo.service.call.impl;

/**
 * @ClassName Main
 * @Description
 * @Auther lifeng
 * @DATE 2018/10/25 15:31
 * @Vserion v0.0.1
 */

public class Main {

    public static void main(String[] args) {
        String ip = "1.181";
        String terminalIps[] = ip.split("\\.");
        Integer terminalIp1 = Integer.parseInt(terminalIps[0]);
        Integer terminalIp2 = Integer.parseInt(terminalIps[1]);
        System.out.println(terminalIp1);
        System.out.println(terminalIp2);
    }
}
