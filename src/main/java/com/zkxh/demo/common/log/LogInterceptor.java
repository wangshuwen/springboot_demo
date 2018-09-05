package com.zkxh.demo.common.log;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName LogInterceptor
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/20 10:07
 * @Vserion v0.0.1
 */

public class LogInterceptor implements MethodInterceptor {

    /**
     * 日志组件
     */
    public static org.slf4j.Logger Logger = LoggerFactory.getLogger(LogInterceptor.class);

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        // TODO Auto-generated method stub
        String methodName = invocation.getMethod().getName();
        //if(methodName.contains("onApplicationEvent"))
        //return invocation.proceed();
        String flag = "|";

        //Logger.info("开始调用方法名:{}",invocation.getMethod());
        StringBuffer sb = new StringBuffer(flag + methodName + flag);
        String param = "";
        for (Object obj : invocation.getArguments()) {
            param = param.concat(obj == null ? "" : obj.toString()).concat(",");
        }
        if (param.endsWith(",")) {
            param = param.substring(0, param.lastIndexOf(","));
        }
        Object obj = invocation.proceed();// 执行需要Log的方法
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sb.append(sf.format(new Date()) + flag);
        sb.append(param + flag);
        sb.append(obj == null ? "" : obj.toString()).append(flag);

        Logger.info(sb.toString());
        return obj;
    }

    public static void main(String args[]) {
        String param = "";
        if (param.endsWith(",")) {
            param = param.substring(0, param.lastIndexOf(","));
        }
        System.out.println("param = " + param);
    }
}
