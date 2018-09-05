package com.zkxh.demo.common.util.http;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @param
 * @description Response 封装
 * @date 19:00 2018/8/16
 * @auther lifeng
 * @return
 **/
public class Response {

    /**
     * 响应码
     */
    private int statusCode;

    /**
     * 响应内容
     */
    private byte[] content;

    /**
     * 响应头部
     */
    private Map<String, String> headers;

    /**
     * 设置响应头
     *
     * @param name
     * @param value
     */
    public void setHeader(String name, String value) {
        if (headers == null) {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(name, value);
    }

    /**
     * 按指定编码获得响应内容，有些响应乱码了 需要解决乱码问题
     *
     * @param encoding
     * @return
     */
    public String getContentString(String encoding) {
        try {
            String contentString = new String(getContent(), encoding);
            return contentString;
        } catch (Exception e) {
            System.out.println("不支持编码");
        }
        return null;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


}
