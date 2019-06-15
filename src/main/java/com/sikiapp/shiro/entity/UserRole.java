/**
 * projectName: shiro
 * fileName: UserRole.java
 * packageName: com.sikiapp.shiro.entity
 * date: 2019-06-15 下午11:24
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @className: UserRole
 * @packageName: com.sikiapp.shiro.entity
 * @description: 用户角色
 * @author: Robert
 * @data: 2019-06-15 下午11:24
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class UserRole {

    private Long userId;
    private Long roleId;
}