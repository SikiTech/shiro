/**
 * projectName: shiro
 * fileName: User.java
 * packageName: com.sikiapp.shiro.entity
 * date: 2019-06-16 上午1:27
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @className: User
 * @packageName: com.sikiapp.shiro.entity
 * @description:
 * @author: Robert
 * @data: 2019-06-16 上午1:27
 * @version: V1.0
 **/
@Data
@EqualsAndHashCode
@Accessors(chain = true)
public class User {
    private Long uid;       // 用户id
    private String uname;   // 登录名，不可改
    private String pwd;     // 已加密的登录密码
    private String salt;    // 加密盐值
    private Date created;   // 创建时间
    private Date updated;   // 修改时间
}