package com.mobile.config;

import com.google.gson.Gson;
import com.core.log.MyLogger;
import com.core.utils.ResponseResult;
import com.core.utils.XssShieldUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author Miracle
 * @date 2021/3/27 12:34
 */
@Aspect
@Component
public class CheckXssParameterAspect {

    MyLogger myLogger = new MyLogger(CheckXssParameterAspect.class);

    @Around("execution(* com.mobile.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = attributes.getRequest();
        Enumeration<String> enumeration=request.getParameterNames();
        for(Enumeration e=enumeration;e.hasMoreElements();){
            String thisName=e.nextElement().toString();
            String thisValue=request.getParameter(thisName);
            if(XssShieldUtil.isXss(thisValue)){
                writer(response, "失败");
                return null;
            }
        }
        return joinPoint.proceed();
    }

    /**
     * 向前端返回信息
     *
     * @param info
     */
    public void writer(HttpServletResponse response, String info) {
        try {
            response.setContentType("application/json;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(new Gson().toJson(ResponseResult.failResult(info)));
            response.getWriter().flush();
            response.getWriter().close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
