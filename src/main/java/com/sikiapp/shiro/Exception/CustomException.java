/**
 * projectName: spring-boot-in-action
 * fileName: CustomException.java
 * packageName: com.sikiapp.springbootinaction.Exception
 * date: 2019-05-25 上午3:17
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.Exception;

import lombok.Data;

/**
 * @className: CustomException
 * @packageName: com.sikiapp.springbootinaction.Exception
 * @description: 全局异常处理
 * @author: Robert
 * @data: 2019-05-25 上午3:17
 * @version: V1.0
 **/
@Data
public class CustomException extends RuntimeException {

    private static final long serialVersionUID = -9196556375573221718L;

    private int code;

    public CustomException() {
    }

    public CustomException(int code) {
        this.code = code;
    }

    public CustomException(int code, String message) {
        super(message);
        this.code = code;
    }
}