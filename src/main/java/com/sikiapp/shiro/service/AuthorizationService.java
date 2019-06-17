/**
 * projectName: shiro
 * fileName: AuthorizationService.java
 * packageName: com.sikiapp.shiro.service
 * date: 2019-06-17 下午4:59
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.service;

import com.sikiapp.shiro.entity.User;
import com.sikiapp.shiro.util.PasswordEncrypter;
import org.apache.commons.lang3.RandomUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @className: AuthorizationService
 * @packageName: com.sikiapp.shiro.service
 * @description: 用户权限管理
 * @author: Robert
 * @data: 2019-06-17 下午4:59
 * @version: V1.0
 **/
@Service
public class AuthorizationService {

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationService.class);
    public static final Map<String, User> USERS_CACHE = new ConcurrentHashMap<>();

    // 注册
    public boolean userRegister(String username, String password) {
        User user = new User().setUname(username).setPwd(password);
        PasswordEncrypter<User> passwordEncrypter = new PasswordEncrypter<>();
        passwordEncrypter.encryptPassword(user);
        user.setCreated(new Date()).setUpdated(new Date());
        user.setUid(RandomUtils.nextLong());
        // 模拟存数据库操作
        USERS_CACHE.put(username, user);
        return true;
    }

    // 修改密码
    public boolean editPassword(String username, String password) throws Exception {
        // 判断输入的旧密码是否正确
        User old = USERS_CACHE.get(username);
        PasswordEncrypter<User> passwordEncrypter = new PasswordEncrypter<>();
        String oldPassword = new SimpleHash(passwordEncrypter.getAlgorithmName(), "oldpwd",
                ByteSource.Util.bytes(old.getSalt()), 2).toHex();
        if (!oldPassword.equals(old.getPwd())) {
            //throw new RuntimeException("旧密码错误");
        }

        String newPassword = new SimpleHash(passwordEncrypter.getAlgorithmName(), password,
                ByteSource.Util.bytes(old.getSalt()), passwordEncrypter.getHashIterations()).toHex();
        old.setPwd(newPassword).setUpdated(new Date());
        USERS_CACHE.replace(username, old);
        return true;
    }

    // 登录
    public String login(String username, String password) throws Exception {

        String oper = "user login";
        logger.info("name:{}, password: {}", username, password);

        Subject currentUser = SecurityUtils.getSubject();
        try {
            //登录
            currentUser.login(new UsernamePasswordToken(username, password));
            //从session取出用户信息
            User user = (User) currentUser.getPrincipal();
            if (user == null) {
                throw new AuthenticationException();
            }
        } catch (UnknownAccountException uae) {
            logger.warn("用户帐号不正确");
            throw new UnknownAccountException("用户帐号或密码不正确");
        } catch (IncorrectCredentialsException ice) {
            logger.warn("用户密码不正确");
            throw new IncorrectCredentialsException("用户密码不正确");
        } catch (LockedAccountException lae) {
            logger.warn("用户帐号被锁定");
            throw new LockedAccountException("用户帐号被锁定");
        } catch (AuthenticationException ae) {
            logger.warn("登录出错");
            throw new AuthenticationException("登录出错");
        }
        return "login...";
    }

    // 注销
    public String logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
        return "logout...";
    }



}