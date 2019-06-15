/**
 * projectName: shiro
 * fileName: RolePerm.java
 * packageName: com.sikiapp.shiro.entity
 * date: 2019-06-15 下午11:25
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @className: RolePerm
 * @packageName: com.sikiapp.shiro.entity
 * @description: 角色权限
 * @author: Robert
 * @data: 2019-06-15 下午11:25
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class RolePerm {

    private Long roleId;
    private Long permId;

}