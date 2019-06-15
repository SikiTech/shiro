/**
 * projectName: shiro
 * fileName: UserCtoller.java
 * packageName: com.sikiapp.shiro.controller
 * date: 2019-06-16 上午1:39
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.controller;

import com.sikiapp.shiro.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: UserCtoller
 * @packageName: com.sikiapp.shiro.controller
 * @description: 用户登录
 * @author: Robert
 * @data: 2019-06-16 上午1:39
 * @version: V1.0
 **/
@RestController
@RequestMapping("/user")
public class UserCtoller {

    private static final Logger logger = LoggerFactory.getLogger(UserCtoller.class);

    /**
     * 登录接口，由于UserService中是模拟返回用户信息的，
     * 所以用户名随意，密码123456
     *
     * @param username
     * @param password
     * @return
     */
    @GetMapping("/login")
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

    @GetMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }



}