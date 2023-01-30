package com.shiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.stereotype.Controller;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author loongzhang
 * @Description DOING
 * @date 2023-01-30-15:26
 */
@Configuration
public class ShiroConfig {
    /**
     * 功能拦截器,拦截需要进行认证的业务逻辑
     * @param defaultWebSecurityManager
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean shiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        // 设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);
        /**
         * anon: 添加内置过滤器
         * authc: 无需认证就可实现
         * user: 必须拥有记住我功能才可以使用
         * perms: 拥有对某个资源的权限才可以使用
         * role: 拥有某个角色权限才能方法
         */

        /**
         * 该方法配置拦截请求路径
         */
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");
        // 设置需要进行拦截的东西
        bean.setFilterChainDefinitionMap(filterMap);

        // 设置拦截请求后跳转的页面
        bean.setLoginUrl("/user/toLogin");
        return bean;
    }
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(userRealm);
        return defaultWebSecurityManager;
    }
    /**
     * 该步骤为第一步 倒序创建
     * @return
     */
    @Bean
    public UserRealm userRealm(){
        return new UserRealm();
    }
}
