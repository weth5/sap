package com.whx.web;

import com.whx.exception.ServiceException;
import com.whx.pojo.vo.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2019/7/24.
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public JsonResult doHandleShiroException(
            ShiroException e) {
        JsonResult r=new JsonResult();
        r.setState(0);
        if(e instanceof UnknownAccountException) {
            r.setMessage("账户不存在");
        }else if(e instanceof LockedAccountException) {
            r.setMessage("账户已被禁用");
        }else if(e instanceof IncorrectCredentialsException) {
            r.setMessage("密码不正确");
        }else if(e instanceof AuthorizationException) {
            r.setMessage("没有此操作权限");
        }else {
            r.setMessage("系统维护中");
        }
       log.error(e.getMessage());
        return r;
    }

    /**
     * @ExceptionHandler 注解修饰的方法为异常处理方法
     * @param e
     * @return
     */
    @ExceptionHandler(ServiceException.class)
    @ResponseBody
    public JsonResult doHandleServiceException(
            ServiceException e) {
        log.error(e.getMessage());
        //封装异常基本信息
        return new JsonResult(e);
    }
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    public JsonResult doHandleRuntimeException(
            RuntimeException e) {
        log.error(e.getMessage());
        //封装异常基本信息
        return new JsonResult(e);
    }
    ///.......

}
