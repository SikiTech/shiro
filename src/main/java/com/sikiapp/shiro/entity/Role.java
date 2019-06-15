/**
 * projectName: shiro
 * fileName: Role.java
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
 * @className: Role
 * @packageName: com.sikiapp.shiro.entity
 * @description: 角色
 * @author: Robert
 * @data: 2019-06-15 下午11:23
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class Role {

    private Long rid;       // 角色id
    private String rname;   // 角色名，用于显示
    private String rdesc;   // 角色描述
    private String rval;    // 角色值，用于权限判断
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
}