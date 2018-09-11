package com.zkxh.demo.service.menu;

import com.alibaba.fastjson.JSONObject;

/**
 * @ClassName MenuService
 * @Description
 * @Auther lifeng
 * @DATE 2018/9/7 16:53
 * @Vserion v0.0.1
 */

public interface MenuService {

    JSONObject findMenusByRoleId(Integer roleId);
}
