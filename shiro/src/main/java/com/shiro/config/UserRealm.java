package com.shiro.config;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.shiro.entity.User;
import com.shiro.mapper.UserMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-30-15:20
 */
// 自定义的userRealm只需要继承一个类即可
public class UserRealm extends AuthorizingRealm {
    @Resource
    UserMapper userMapper;
    /**
     * 进行授权
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println(" 这里进行了授权操作===>  doGetAuthorizationInfo");
        return null;
    }

    /**
     * 进行认证
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        /**
         * 此处的用户名字和密码均从数据库中获取
         */

        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
        LambdaQueryWrapper<User> userLambdaQueryWrapper = new LambdaQueryWrapper<>();
        userLambdaQueryWrapper.eq(User::getName, usernamePasswordToken.getUsername());
        User user = userMapper.selectOne(userLambdaQueryWrapper);

        if (user==null){
            // 次数返回null 即可以在前端抛出用户名异常
            return null;
        }
        /**
         * 此处密码认证教程shiro来做,为了安全
         * param1:获取当前用户的认证,
         * param2:传递密码的对象
         * param3:认证名字
          */
        return new SimpleAuthenticationInfo("",user.getPassWord(),"");
    }
}
