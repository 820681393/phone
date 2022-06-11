package com.mobile.config;

import com.google.gson.Gson;
import com.core.exception.MyRuntimeException;
import com.core.log.MyLogger;
import com.core.utils.ResponseResult;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2019/10/24 0024.
 * 全局错误日志拦截器
 */
@Aspect
@Component
public class LogMobileErrorAspect {


    MyLogger myLogger = new MyLogger(this.getClass());

    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.mobile.controller..*.*(..)))")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpServletResponse response = attributes.getResponse();

        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            if(ex instanceof MyRuntimeException){
                response.getWriter().print(new Gson().toJson(ResponseResult.failResult(ex.getMessage())));
            }else{
                response.getWriter().print(new Gson().toJson(ResponseResult.failResult("程序异常")));
            }
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
