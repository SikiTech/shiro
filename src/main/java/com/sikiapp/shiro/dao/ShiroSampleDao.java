/**
 * projectName: shiro
 * fileName: ShiroSampleDao.java
 * packageName: com.sikiapp.shiro.dao
 * date: 2019-06-15 下午5:29
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.dao;

import com.sikiapp.shiro.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @className: ShiroSampleDao
 * @packageName: com.sikiapp.shiro.dao
 * @description: 用户账号管理 (模拟数据库)
 * @author: Robert
 * @data: 2019-06-15 下午5:29
 * @version: V1.0
 **/
@Repository
public class ShiroSampleDao {

    /**
     * 模拟查询返回用户信息
     * @param uname
     * @return
     */
    public User findUserByName(String uname){
        User user = new User();
        user.setUname(uname);
        user.setPwd("J/ms7qTJtqmysekuY8/v1TAS+VKqXdH5sB7ulXZOWho=");//密码明文是123456
        user.setSalt("wxKYXuTPST5SG0jMQzVPsg==");//加密密码的盐值
        user.setUid(new Random().nextLong());//随机分配一个id
        user.setCreated(new Date());
        return user;
    }

    /**
     * 模拟根据用户id查询返回用户的所有角色，实际查询语句参考：
     * SELECT r.rval FROM role r, user_role ur
     * WHERE r.rid = ur.role_id AND ur.user_id = #{userId}
     * @param uid
     * @return
     */
    public Set<String> getRolesByUserId(Long uid){
        Set<String> roles = new HashSet<>();
        //三种编程语言代表三种角色：js程序员、java程序员、c++程序员
        roles.add("js");
        roles.add("java");
        roles.add("cpp");
        return roles;
    }

    /**
     * 模拟根据用户id查询返回用户的所有权限，实际查询语句参考：
     * SELECT p.pval FROM perm p, role_perm rp, user_role ur
     * WHERE p.pid = rp.perm_id AND ur.role_id = rp.role_id
     * AND ur.user_id = #{userId}
     * @param uid
     * @return
     */
    public Set<String> getPermsByUserId(Long uid){
        Set<String> perms = new HashSet<>();
        //三种编程语言代表三种角色：js程序员、java程序员、c++程序员
        //js程序员的权限
        perms.add("html:edit");
        //c++程序员的权限
        perms.add("hardware:debug");
        //java程序员的权限
        perms.add("mvn:install");
        perms.add("mvn:clean");
        perms.add("mvn:test");
        return perms;
    }



}