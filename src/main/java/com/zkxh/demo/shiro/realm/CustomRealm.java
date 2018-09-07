package com.zkxh.demo.shiro.realm;

import com.zkxh.demo.common.da.redis.RedisService;
import com.zkxh.demo.model.user.SysUser;
import com.zkxh.demo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;

/**
 * @ClassName CustomRealm
 * @Description
 * @Auther lifeng
 * @DATE 2018/8/23 9:16
 * @Vserion v0.0.1
 */
public class CustomRealm extends AuthorizingRealm {


    Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    //注入userService 登录业务逻辑
    @Resource
    private UserService userService;


    @Resource
    RedisService redisService;

    /**
     * @param principals
     * @return org.apache.shiro.authz.AuthorizationInfo
     * @description 授权过程
     * @date 9:28 2018/8/23
     * @auther lifeng
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();

        logger.info("执行授权过程");
//      authorizationInfo.addRole("admin");
        authorizationInfo.addStringPermission("list");

//      获取权限集合 TODO
//      authorizationInfo.addStringPermission("list");
//      User userInfo = (User) principals.getPrimaryPrincipal();
//
//        for (SysRole role : userInfo.getRoleList()) {
//          authorizationInfo.addRole(role.getRole());
//            for (SysPermission p : role.getPermissions()) {
//                  authorizationInfo.addStringPermission(p.getPermission());
//              }
//         }
//      SecurityUtils.getSubject().checkPermission("admin:list");

        return authorizationInfo;

    }

    /**
     * @param token
     * @return org.apache.shiro.authc.AuthenticationInfo
     * @description 认证过程
     * @date 9:28 2018/8/23
     * @auther lifeng
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //1、从主题传过来的认证信息中，获取用户名
        String account = (String) token.getPrincipal();
        //从数据库获取SysUser信息
        SysUser sysUser = userService.findSysUserByAccount(account);
        //2、通过用户名到数据库中获取凭证
        if (sysUser == null) {
            throw new AuthenticationException();
        }
        if (!sysUser.getEnabled()) { //账户冻结
            throw new LockedAccountException();
        }
        //Shiro认证
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(sysUser,
                sysUser.getSysPassword(),
                ByteSource.Util.bytes(sysUser.getSysAccount()),
                getName());

        return authenticationInfo;
    }


    /**
     * @param [args]
     * @return void
     * @description 密码生成工具方法的
     * 进行两次hash
     * 对密码和账号加盐加密
     * @date 9:25 2018/9/4
     * @auther lifeng
     **/
    public static void main(String[] args) {
        /**
         * source 用户密码
         * salt 用户的账号，也可是任意已知数值
         */
        Md5Hash md5Hash = new Md5Hash("123456", "jkuser");
        md5Hash = new Md5Hash(md5Hash);
        System.out.println(md5Hash);
    }

}
