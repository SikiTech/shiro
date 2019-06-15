/**
 * projectName: spring-boot-in-action
 * fileName: ShiroConfiguration.java
 * packageName: com.sikiapp.springbootinaction.config
 * date: 2019-05-27 上午11:45
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.config;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @className: ShiroConfiguration
 * @packageName: com.sikiapp.springbootinaction.config
 * @description: Shiro 配置
 * @author: Robert
 * @data: 2019-05-27 上午11:45
 * @version: V1.0
 **/
@Configuration
public class ShiroConfig {

    private static final Logger logger = LoggerFactory.getLogger(ShiroConfig.class);

    @Bean(name = "authRealm")
    public AuthRealm authRealm() {
        AuthRealm authRealm = new AuthRealm();
        return authRealm;
    }

    @Bean(name = "securityManager")
    @DependsOn(value = {"authRealm"})
    public DefaultWebSecurityManager getDefaultWebSecurityManager(AuthRealm authRealm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setRealm(authRealm);
        return defaultWebSecurityManager;
    }

    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor getLifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }

    @Bean
    public DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
        // 在@Controller注解的类的方法中加入@RequiresRole等shiro注解，会导致该方法无法映射请求，导致返回404
        DefaultAdvisorAutoProxyCreator autoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        autoProxyCreator.setProxyTargetClass(true);
        return autoProxyCreator;
    }

    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        // 必须设置 SecurityManager
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 如果不设置默认会自动寻找Web工程根目录下的"/login"页面
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        // 登录成功后要跳转的连接
        shiroFilterFactoryBean.setSuccessUrl("/index");
        shiroFilterFactoryBean.setUnauthorizedUrl("/denied");
        loadShiroFilterChain(shiroFilterFactoryBean);
        return shiroFilterFactoryBean;
    }

    /**
     * 加载shiroFilter权限控制规则（从数据库读取然后配置）
     */
    private void loadShiroFilterChain(ShiroFilterFactoryBean shiroFilterFactoryBean) {
        /////////////////////// 下面这些规则配置最好配置到配置文件中 ///////////////////////
        // TODO 重中之重啊，过滤顺序一定要根据自己需要排序
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();
        // 需要验证的写 authc 不需要的写 anon
        filterChainDefinitionMap.put("/user/login", "anon");
        filterChainDefinitionMap.put("/user/logout", "anon");
        filterChainDefinitionMap.put("/resource/**", "anon");
        filterChainDefinitionMap.put("/install", "anon");
        filterChainDefinitionMap.put("t5/hello", "anon");
        logger.info("##################从数据库读取权限规则，加载到shiroFilter中##################");

        // 不用注解也可以通过 API 方式加载权限规则
        Map<String, String> permissions = new LinkedHashMap<>();
        permissions.put("/roles/find", "authc, perms[user:find]");
        filterChainDefinitionMap.putAll(permissions);
        filterChainDefinitionMap.put("/**", "authc");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
    }


}