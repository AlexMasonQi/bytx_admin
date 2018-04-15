package com.bytx.admin.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import com.bytx.admin.realm.UserRealm;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.support.PropertiesLoaderUtils;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author Alex
 * @description Shiro配置类
 * @date 2018.03.28 09:32
 */
@Configuration
public class ShiroConfiguration
{
    private static final Logger logger = LoggerFactory.getLogger(ShiroConfiguration.class);

    /**
     * @description Shiro生命周期处理器
     * @author Alex
     * @date 2018.03.28 14:21
     */
    @Bean
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor()
    {
        return new LifecycleBeanPostProcessor();
    }

    /**
     * @description Shiro的Web过滤器定义
     * @author Alex
     * @date 2018.03.28 14:24
     */
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean()
    {
        logger.info("注入Shiro的Web过滤器---->shiroFilter", ShiroFilterFactoryBean.class);

        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        shiroFilterFactoryBean.setSecurityManager(securityManager());

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.html"页面
        shiroFilterFactoryBean.setLoginUrl("/login");
        // 登录成功后要跳转的链接
        shiroFilterFactoryBean.setSuccessUrl("admin/companyadmin");
        //未授权界面;
        shiroFilterFactoryBean.setUnauthorizedUrl("/403");

        Map<String, Filter> filters = new LinkedHashMap<>();
        filters.put("kickout", accessControlFilter());
        shiroFilterFactoryBean.setFilters(filters);


        //定义拦截器
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        //配置不会被拦截的链接，按顺序判断
        //拦截规则如下:
        //anon:所有url都都可以匿名访问
        //authc:需要认证才能进行访问
        //user:配置记住我或认证通过可以访问
        //配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
        filterChainDefinitionMap.put("/logout", "logout");
        //<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->:这是一个坑呢，一不小心代码就不好使了;
        //<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
        filterChainDefinitionMap.put("/css/**", "anon");
        filterChainDefinitionMap.put("/js/**", "anon");
        filterChainDefinitionMap.put("/ckeditor/**","anon");
        filterChainDefinitionMap.put("/fonts/**", "anon");
        filterChainDefinitionMap.put("/icons/**", "anon");
        filterChainDefinitionMap.put("/images/**", "anon");
        filterChainDefinitionMap.put("/music/**", "anon");
        filterChainDefinitionMap.put("/video/**", "anon");
        filterChainDefinitionMap.put("/login", "anon");
        filterChainDefinitionMap.put("/test/**", "anon");
        filterChainDefinitionMap.put("/**", "kickout,authc");

        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);

        return shiroFilterFactoryBean;
    }

    @Bean
    public AccessControlFilter accessControlFilter()
    {
        KickoutSessionControlFilter logoutFilter = new KickoutSessionControlFilter();
        logoutFilter.setSessionManager(sessionManager());
        logoutFilter.setCacheManager(redisCacheManager());
        return logoutFilter;
    }

    @Bean
    public UserRealm userRealm()
    {
        UserRealm userRealm = new UserRealm();
        //告诉realm,使用credentialsMatcher加密算法类来验证密文
        userRealm.setCredentialsMatcher(hashedCredentialsMatcher());
        userRealm.setCachingEnabled(false);

        return userRealm;
    }

    @Bean
    public DefaultWebSecurityManager securityManager()
    {
        logger.info("注入Shiro的Web过滤器---->shiroFilter", ShiroFilterFactoryBean.class);

        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userRealm());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(redisCacheManager());
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        return securityManager;
    }

    /**
     * 凭证匹配器
     * （由于我们的密码校验交给Shiro的SimpleAuthenticationInfo进行处理了
     * 所以我们需要修改下doGetAuthenticationInfo中的代码;
     * ）
     * 可以扩展凭证匹配器，实现 输入密码错误次数后锁定等功能，下一次
     *
     * @return
     */
    @Bean(name = "credentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher()
    {
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        //散列算法:这里使用MD5算法;
        hashedCredentialsMatcher.setHashAlgorithmName("md5");
        //散列的次数，比如散列两次，相当于 md5(md5(""));
        hashedCredentialsMatcher.setHashIterations(1);
        //storedCredentialsHexEncoded默认是true，此时用的是密码加密用的是Hex编码；false时用Base64编码
        hashedCredentialsMatcher.setStoredCredentialsHexEncoded(true);

        return hashedCredentialsMatcher;
    }

    /**
     * 开启Shiro的注解(如@RequiresRoles,@RequiresPermissions),需借助SpringAOP扫描使用Shiro注解的类,并在必要时进行安全逻辑验证
     * 配置以下两个bean(DefaultAdvisorAutoProxyCreator(可选)和AuthorizationAttributeSourceAdvisor)即可实现此功能
     *
     * @return
     */
    @Bean
    @DependsOn({"lifecycleBeanPostProcessor"})
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator()
    {
        DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
        advisorAutoProxyCreator.setProxyTargetClass(true);
        return advisorAutoProxyCreator;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor()
    {
        AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor = new AuthorizationAttributeSourceAdvisor();
        authorizationAttributeSourceAdvisor.setSecurityManager(securityManager());
        return authorizationAttributeSourceAdvisor;
    }

    /**
     * 添加ShiroDialect 为了在thymeleaf里使用shiro的标签的bean
     *
     * @return
     */
    @Bean(name = "shiroDialect")
    public ShiroDialect shiroDialect()
    {
        return new ShiroDialect();
    }

    /**
     * 配置shiro redisManager
     * <p>
     *
     * @return
     */
    public RedisManager redisManager()
    {
        Properties properties = null;
        try
        {
            properties = PropertiesLoaderUtils.loadAllProperties("redis.properties");
        }
        catch (Exception e)
        {
            logger.error("redisManager", e);
        }
        RedisManager redisManager = new RedisManager();

        redisManager.setHost(properties.getProperty("spring.redis.host"));
        redisManager.setPort(Integer.parseInt(properties.getProperty("spring.redis.port")));
        redisManager.setExpire(Integer.parseInt(properties.getProperty("spring.redis.sessionExpireTime")));
        redisManager.setTimeout(Integer.parseInt(properties.getProperty("spring.redis.timeout")));
        redisManager.setDatabase(Integer.parseInt(properties.getProperty("spring.redis.database")));
        return redisManager;
    }

    /**
     * redisCacheManager 缓存 redis实现
     * <p>
     *
     * @return
     */
    @Bean
    public RedisCacheManager redisCacheManager()
    {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * <p>
     */
    @Bean
    public RedisSessionDAO redisSessionDAO()
    {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * Session Manager
     * <p>
     */
    @Bean
    public DefaultWebSessionManager sessionManager()
    {
        DefaultWebSessionManager sessionManager = new DefaultWebSessionManager();
        sessionManager.setSessionIdUrlRewritingEnabled(false);
        sessionManager.setSessionDAO(redisSessionDAO());
        return sessionManager;
    }
}
