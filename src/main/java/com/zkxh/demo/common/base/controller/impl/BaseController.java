package com.zkxh.demo.common.base.controller.impl;

import com.zkxh.demo.common.base.controller.IBaseController;
import com.zkxh.demo.common.base.log.BaseLog;
import com.zkxh.demo.common.enums.ResultEnum;
import com.zkxh.demo.common.result.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
public abstract class BaseController implements IBaseController {

    protected Logger logger = null;

    public BaseController() {
        logger = LoggerFactory.getLogger(getClass().getName());
    }
    //降低代码耦合，防止异常信息进入客户显示
//    @ExceptionHandler(Exception.class)
//    public Result handleException(Exception e) {
//        e.printStackTrace();
//        return new Result(ResultEnum.FAILED.getCode(),ResultEnum.FAILED.getMsg());
//    }

}
