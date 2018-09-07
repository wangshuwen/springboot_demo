package com.zkxh.demo.controller.role;

import com.zkxh.demo.common.result.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName RoleController
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/28 13:32
 * @Vserion v0.0.1
 */

@RestController
@Api(description = "RoleController", tags = {"角色操作接口"})  // API文档总说明
public class RoleController {

    /**
     * @param [name, desc]
     * @return java.lang.String
     * @description
     * @date 13:35 2018/8/28
     * @auther lifeng
     **/
    @ApiOperation(value = "创建角色", notes = "根据输入的name，和desc字段创建一个角色")  //api文件描述
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.String", name = "name", value = "角色名称", required = true, paramType = "path"),
            @ApiImplicitParam(dataType = "java.lang.String", name = "desc", value = "角色描述", required = true)
    })
    @PostMapping("/role/add")
    public String addRole(String name, String desc) {

        return ResultUtil.jsonToStringSuccess();
    }

    /**
     * @param [name, desc, id]
     * @return java.lang.String
     * @description
     * @date 13:35 2018/8/28
     * @auther lifeng
     **/
    @ApiOperation(value = "更新角色信息123", notes = "根据角色ID更新角色信息123")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.String", name = "name", value = "角色名称", required = true, paramType = "path"),
            @ApiImplicitParam(dataType = "java.lang.String", name = "desc", value = "角色描述", required = true),
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "id", value = "角色ID", required = true)
    })
    @PutMapping("/role/update/{id}")
    public String updateRole(String name, String desc, @PathVariable Integer id) {

        return ResultUtil.jsonToStringSuccess();
    }

    /**
     * @description 根据角色ID删除角色信息
     * @date 10:37 2018/9/7
     * @param [id]
     * @auther lifeng
     * @return java.lang.String
     **/
    @ApiOperation(value = "删除角色信息", notes = "根据角色ID删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "id", value = "角色ID")
    })
    @DeleteMapping("/role/delete/{id}")
    public String deleteRole(@PathVariable Integer id) {
        return ResultUtil.jsonToStringSuccess();
    }


    @ApiOperation(value = "批量删除角色信息", notes = "根据角色ID批量删除角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "ids", value = "角色ID数组", required = true)
    })
    @DeleteMapping("/role/delete/roles")
    public String deleteRoles(Integer... ids) {
        return ResultUtil.jsonToStringSuccess();
    }

    @ApiOperation(value = "获取角色信息", notes = "根据ID获取角色信息")
    @ApiImplicitParams({
            @ApiImplicitParam(dataType = "java.lang.Integer", name = "id", value = "角色ID", required = true)
    })
    @GetMapping("role/find/{id}")
    public String findRoleByRoleId(@PathVariable Integer id) {
        return ResultUtil.jsonToStringSuccess();
    }

    @ApiOperation(value = "获取全部角色信息", notes = "获取角色信息")
    @GetMapping("role/find/")
    public String findRoleAll() {
        return ResultUtil.jsonToStringSuccess();
    }


}
