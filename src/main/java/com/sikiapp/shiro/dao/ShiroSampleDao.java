/**
 * projectName: shiro
 * fileName: ShiroSampleDao.java
 * packageName: com.sikiapp.shiro.dao
 * date: 2019-06-15 下午5:29
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.dao;

import org.springframework.stereotype.Repository;

import java.util.HashSet;
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

    public Set<String> getRolesByUsername(String username) {
        Set<String> roles = new HashSet<>();
        switch (username) {
            case "zhangsan":
                roles.add("admin");
                break;
            case "lisi":
                roles.add("guest");
                break;
            default:
                break;
        }
        return roles;
    }

    public Set<String> getPermissionsByRole(String role) {
        Set<String> permissions = new HashSet<>();
        switch (role) {
            case "admin":
                permissions.add("read");
                permissions.add("write");
                break;
            case "guest":
                permissions.add("read");
                break;
            default:
                break;
        }
        return permissions;
    }

    public String getPasswordByUsername(String username) {
        switch (username) {
            case "zhangsan":
                return "zhangsan";
            case "lisi":
                return "lisi";
            default:
                break;
        }
        return null;
    }




}