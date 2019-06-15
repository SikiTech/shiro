/**
 * projectName: spring-boot-in-action
 * fileName: GlobalExceptionHandler.java
 * packageName: com.sikiapp.springbootinaction.Exception
 * date: 2019-05-25 上午3:32
 * copyright(c) 2018-2028 深圳识迹科技有限公司
 */
package com.sikiapp.shiro.Exception;

import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @className: GlobalExceptionHandler
 * @packageName: com.sikiapp.springbootinaction.Exception
 * @description: 全局异常处理类
 * @author: Robert
 * @data: 2019-05-25 上午3:32
 * @version: V1.0
 **/
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     *@description: 自定义异常处理类
     * 可以定义多个
     */
    @ExceptionHandler(CustomException.class)
    public ErrorResponseEntity customExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        CustomException exception = (CustomException) e;
        return new ErrorResponseEntity(exception.getCode(), exception.getMessage());
    }

    /**
     *@description: Runtime异常处理类
     * 可以定义多个，也可以使用if(e instanceof xxxException)
     */
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponseEntity runtimeExceptionHandler(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.BAD_REQUEST.value();
        response.setStatus(code);
        RuntimeException exception = (RuntimeException) e;
        return new ErrorResponseEntity(code, exception.getMessage());
    }

    @ExceptionHandler(UnknownAccountException.class)
    public ErrorResponseEntity unknownAccount(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.FORBIDDEN.value();
        response.setStatus(code);
        return new ErrorResponseEntity(code, "用户帐号或密码不正确");
    }

    @ExceptionHandler(IncorrectCredentialsException.class)
    public ErrorResponseEntity incorrectCredentials(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.FORBIDDEN.value();
        response.setStatus(code);
        return new ErrorResponseEntity(code, "用户密码不正确");
    }

    @ExceptionHandler(LockedAccountException.class)
    public ErrorResponseEntity lockedAccount(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.FORBIDDEN.value();
        response.setStatus(code);
        return new ErrorResponseEntity(code, "用户帐号被锁定");
    }

    @ExceptionHandler(ShiroException.class)
    public ErrorResponseEntity handleShiroException(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.BAD_REQUEST.value();
        response.setStatus(code);
        RuntimeException exception = (RuntimeException) e;
        return new ErrorResponseEntity(code, exception.getMessage());
    }

    @ExceptionHandler(UnauthenticatedException.class)
    public ErrorResponseEntity unauthorized(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.UNAUTHORIZED.value();
        response.setStatus(code);
        RuntimeException exception = (RuntimeException) e;
        return new ErrorResponseEntity(code, "请先登录");
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ErrorResponseEntity forbidden(HttpServletRequest request, final Exception e, HttpServletResponse response) {
        int code = HttpStatus.FORBIDDEN.value();
        response.setStatus(code);
        RuntimeException exception = (RuntimeException) e;
        return new ErrorResponseEntity(code, "权限不足");
    }

    /**
     * 通用的接口映射异常处理方
     */
    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {
        if (ex instanceof MethodArgumentNotValidException) {
            MethodArgumentNotValidException exception = (MethodArgumentNotValidException) ex;
            return new ResponseEntity<>(new ErrorResponseEntity(status.value(),
                    exception.getBindingResult().getAllErrors().get(0).getDefaultMessage()), status);
        }

        if (ex instanceof MethodArgumentTypeMismatchException) {
            MethodArgumentTypeMismatchException exception = (MethodArgumentTypeMismatchException) ex;
            logger.error("参数转换失败，方法：{}，参数：{}，信息：{}", exception.getParameter().getMethod().getName(),
                    exception.getName(), exception.getLocalizedMessage());
            return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
        }
        return new ResponseEntity<>(new ErrorResponseEntity(status.value(), "参数转换失败"), status);
    }
}