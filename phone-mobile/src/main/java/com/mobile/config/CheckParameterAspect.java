package com.mobile.config;

import com.google.gson.Gson;
import com.core.annotation.CheckParameter;
import com.core.log.MyLogger;
import com.core.utils.ResponseResult;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Miracle on 2020/8/16.
 */
@Aspect
@Component
public class CheckParameterAspect {

    MyLogger myLogger = new MyLogger(CheckParameterAspect.class);

    @Around("execution(* com.mobile.controller..*.*(..))")
    public Object around(ProceedingJoinPoint joinPoint) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        HttpServletRequest request = attributes.getRequest();
        Map<Object,Object> argsMap=new HashMap<>();
        Enumeration<String> enumeration=request.getParameterNames();
        for(Enumeration e=enumeration;e.hasMoreElements();){
            String thisName=e.nextElement().toString();
            String thisValue=request.getParameter(thisName);
            argsMap.put(thisName,thisValue);
        }
//        myLogger.info("=================================================================================================================");
//        myLogger.info("【接口AOP】访问接口 : " + request.getRequestURL().toString());
//        myLogger.info("【接口AOP】请求方式 : " + request.getMethod());
//        myLogger.info("【接口AOP】请求IP : " + IpUtil.getIpAddr(request));
//        myLogger.info("【接口AOP】执行的业务方法名= : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
//        if(argsMap.keySet().size()>0){
//            myLogger.info("【接口AOP】业务方法获得的参数= : " + JSONObject.toJSONString(argsMap));
//        }
//        myLogger.info("=================================================================================================================");
//        myLogger.info(" ");
//        myLogger.info(" ");
        Class clazz = joinPoint.getSignature().getDeclaringType();
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(joinPoint.getSignature().getName())) {
                CheckParameter checkParameter = method.getAnnotation(CheckParameter.class);
                if (checkParameter == null) {
                    return joinPoint.proceed();
                }
                ApiImplicitParams apiImplicitParams = method.getAnnotation(ApiImplicitParams.class);
                if (apiImplicitParams != null) {
                    for (ApiImplicitParam apiImplicitParam : apiImplicitParams.value()) {
                        Object param = request.getParameter(apiImplicitParam.name());
                        if (param == null) {
                            param = request.getAttribute(apiImplicitParam.name());
                        }
                        if (apiImplicitParam.required()) {
                            if (param == null) {
                                writer(response, apiImplicitParam.name() + " 不能为空");
                                return null;
                            }
                            if (param instanceof String) {
                                String paramStr = (String) param;
                                if (paramStr.equals("") || paramStr.length() == 0) {
                                    writer(response, apiImplicitParam.name() + " 不能为空");
                                    return null;
                                }
                            }
                        }
                    }
                }
                ApiImplicitParam apiImplicitParam = method.getAnnotation(ApiImplicitParam.class);
                if (apiImplicitParam != null) {
                    Object param = request.getParameter(apiImplicitParam.name());
                    if (param == null) {
                        param = request.getAttribute(apiImplicitParam.name());
                    }
                    if (apiImplicitParam.required()) {
                        if (param == null) {
                            writer(response, apiImplicitParam.name() +  " 不能为空");
                            return null;
                        }
                        if (param instanceof String) {
                            String paramStr = (String) param;
                            if (paramStr.equals("") || paramStr.length() == 0) {
                                writer(response, apiImplicitParam.name() + " 不能为空");
                                return null;
                            }
                        }
                    }
                }
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
