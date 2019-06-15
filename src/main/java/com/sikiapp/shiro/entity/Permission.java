/**
 * projectName: shiro
 * fileName: Permission.java
 * packageName: com.sikiapp.shiro.entity
 * date: 2019-06-15 下午11:23
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @className: Permission
 * @packageName: com.sikiapp.shiro.entity
 * @description: 权限
 * @author: Robert
 * @data: 2019-06-15 下午11:23
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class Permission {

    /**
     * 权限类型：菜单
     */
    public static int PTYPE_MENU = 1;
    /**
     * 权限类型：按钮
     */
    public static int PTYPE_BUTTON = 2;

    private Long pid;       // 权限id
    private String pname;   // 权限名称
    private Integer ptype;  // 权限类型：1.菜单；2.按钮
    private String pval;    // 权限值，shiro的权限控制表达式
    private Date created;   // 创建时间
    private Date updated;   // 修改时间

    public static int getPtypeMenu() {
        return PTYPE_MENU;
    }

    public static void setPtypeMenu(int ptypeMenu) {
        PTYPE_MENU = ptypeMenu;
    }

    public static int getPtypeButton() {
        return PTYPE_BUTTON;
    }

    public static void setPtypeButton(int ptypeButton) {
        PTYPE_BUTTON = ptypeButton;
    }
}