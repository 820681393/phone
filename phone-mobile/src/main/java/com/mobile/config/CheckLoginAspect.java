package com.mobile.config;

import com.google.gson.Gson;
import com.core.annotation.CheckLogin;
import com.core.service.IRedisBaseService;
import com.core.utils.ResponseCode;
import com.core.utils.ResponseResult;
import com.core.utils.TokenUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by Miracle on 2020/8/16.
 */
@Aspect
@Component
public class CheckLoginAspect {

    @Autowired
    private IRedisBaseService iRedisBaseService;

    @Around("execution(* com.mobile.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = attributes.getRequest();
        Class clazz = joinPoint.getSignature().getDeclaringType();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                CheckLogin checkLogin = method.getAnnotation(CheckLogin.class);
                if (checkLogin == null) {
                    return joinPoint.proceed();
                }
                ResponseResult responseResult = TokenUtil.verificationUserToken(request, iRedisBaseService);
                if (responseResult.getStatusCode() != (ResponseCode.SUCCESS)) {
                    writer(response, request);
                    return null;
                }
                request.setAttribute("userToken", responseResult.getData());
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 向前端返回信息
     */
    public void writer(HttpServletResponse response, HttpServletRequest request) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(new Gson().toJson(ResponseResult.userKeyExpireResult()));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
