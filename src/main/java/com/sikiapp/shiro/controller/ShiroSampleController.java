/**
 * projectName: shiro
 * fileName: ShiroSampleController.java
 * packageName: com.sikiapp.shiro.controller
 * date: 2019-06-15 下午5:53
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.controller;

import com.sikiapp.shiro.service.ShiroSampleService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @className: ShiroSampleController
 * @packageName: com.sikiapp.shiro.controller
 * @description: Contoller
 * @author: Robert
 * @data: 2019-06-15 下午5:53
 * @version: V1.0
 **/
@RestController
public class ShiroSampleController {

    @Autowired
    private ShiroSampleService shiroSampleService;

    @GetMapping("/login")
    public void login(String username, String password) {
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        token.setRememberMe(true);
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.login(token);
    }

    @GetMapping("/logout")
    public void logout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }

    @GetMapping("/read")
    public String read() {
        return this.shiroSampleService.read();
    }

    @GetMapping("/write")
    public String write() {
        return this.shiroSampleService.write();
    }

}