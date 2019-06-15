/**
 * projectName: spring-boot-in-action
 * fileName: ErrorResponseEntity.java
 * packageName: com.sikiapp.springbootinaction.Exception
 * date: 2019-05-25 上午3:24
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.Exception;

import lombok.Data;

import java.io.Serializable;

/**
 * @className: ErrorResponseEntity
 * @packageName: com.sikiapp.springbootinaction.Exception
 * @description: 异常实体类
 * @author: Robert
 * @data: 2019-05-25 上午3:24
 * @version: V1.0
 **/
@Data
public class ErrorResponseEntity implements Serializable {

    private static final long serialVersionUID = 5690249997109205506L;

    private int code;
    private String msg;

    public ErrorResponseEntity() {
    }

    public ErrorResponseEntity(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}