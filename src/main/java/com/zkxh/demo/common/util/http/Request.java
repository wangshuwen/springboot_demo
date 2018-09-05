package com.zkxh.demo.common.util.http;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * @param
 * @description Request 请求类封装
 * @date 19:00 2018/8/16
 * @auther lifeng
 * @return
 **/
public class Request {


    /**
     * 请求属性
     */
    private Map<String, String> properties;

    /**
     * 请求参数
     */
    private Map<String, String> params;

    /**
     * 请求头部
     */
    private Map<String, String> headers;

    private byte[] postData;

    /**
     * 是否同步
     */
    private boolean sync = false;


    /**
     * 获得设置的属性
     *
     * @param property
     * @return
     */
    public String getProperty(String property) {
        if (properties == null) {
            return null;
        }
        return properties.get(property);
    }

    /**
     * 设置属性
     *
     * @param propery
     * @param value
     */
    public void setProperty(String propery, String value) {
        if (properties == null) {
            properties = new LinkedHashMap<String, String>();
        }
        properties.put(propery, value);
    }


    /**
     * 设置参数
     *
     * @param params
     */
    public void setParam(String param, String value) {
        if (params == null) {
            params = new LinkedHashMap<String, String>();
        }
        params.put(param, value);
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    /**
     * 设置头部信息
     *
     * @param header
     * @param value
     */
    public void setHeader(String header, String value) {
        if (headers == null) {
            headers = new LinkedHashMap<String, String>();
        }
        headers.put(header, value);
    }

    public Map<String, String> getParams() {
        return params;
    }

    public String getHeader(String header) {
        if (headers == null) {
            return null;
        }
        return headers.get(header);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, String> properties) {
        this.properties = properties;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }


    public byte[] getPostData() {
        return postData;
    }

    public void setPostData(byte[] postData) {
        this.postData = postData;
    }

    public boolean isSync() {
        return sync;
    }

    public void setSync(boolean sync) {
        this.sync = sync;
    }


}
