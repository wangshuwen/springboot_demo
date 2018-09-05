package com.zkxh.demo.common.base.controller.impl;

import com.zkxh.demo.common.base.controller.IBaseController;
import com.zkxh.demo.common.base.log.BaseLog;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @version 1.0
 * @auther li
 * @date 2018/1/2-15:02
 */
@RestController
public abstract class BaseController extends BaseLog implements IBaseController, ErrorController {

    //降低代码耦合，防止异常信息进入客户显示
//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e) {
//        e.printStackTrace();
//        return new Result(ResultEnum.FAILED.getCode(),ResultEnum.FAILED.getMsg());
//    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     *
     * @return
     */
    @RequestMapping(value = "unauth", method = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String unauth() {
        return ResultUtil.jsonToStringError(ResultEnum.IS_NOT_LOGIN);
    }


    private static final String ERROR_PATH = "/error";

    /**
     * 主要是登陆后的各种错误路径  404页面改为返回此json
     * 未登录的情况下,大部分接口都已经被shiro拦截,返回让用户登录了
     *
     * @return 501的错误信息json
     */
    @RequestMapping(value = ERROR_PATH, method = {RequestMethod.DELETE, RequestMethod.POST, RequestMethod.GET})
    @ResponseBody
    public String handleError() {
        return ResultUtil.jsonToStringError(ResultEnum.FAILED);
    }

    @Override
    public String getErrorPath() {
        return ERROR_PATH;
    }
}
