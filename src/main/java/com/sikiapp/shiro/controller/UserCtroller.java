/**
 * projectName: shiro
 * fileName: UserCtoller.java
 * packageName: com.sikiapp.shiro.controller
 * date: 2019-06-16 上午1:39
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.controller;

import com.sikiapp.shiro.service.AuthorizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
public class UserCtroller {

    private static final Logger logger = LoggerFactory.getLogger(UserCtroller.class);

    @Autowired
    private AuthorizationService authorizationService;

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
         return authorizationService.login(username, password);
    }

    @PostMapping("/login")
    public String loginx(String username, String password, Boolean rememberMe) throws Exception {
        return authorizationService.loginRemem(username, password, rememberMe);
    }

    @GetMapping("/logout")
    public String logout() {
        return authorizationService.logout();
    }

    @GetMapping("/register")
    public boolean register(String username, String password) throws Exception {
        return authorizationService.userRegister(username, password);
    }

    @GetMapping("/modify")
    public boolean editPassword(String username, String password) throws Exception {
        return authorizationService.editPassword(username, password);
    }



}