package com.mobile.config;

import com.core.log.MyLogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2019/10/24 0024.
 * 全局错误日志拦截器
 */
@Aspect
@Component
public class LogErrorAspect {

    MyLogger myLogger = new MyLogger(this.getClass());

    @AfterThrowing(throwing = "ex", pointcut = "execution(* com.mobile..*.*(..))")
    public void afterThrow(JoinPoint joinPoint, Exception ex) {
        logInput(joinPoint, ex);
    }

    public void logInput(JoinPoint joinPoint, Exception ex) {
        //详细错误信息
        StringBuffer errorMsg = new StringBuffer();
        StackTraceElement[] trace = ex.getStackTrace();
        for (StackTraceElement s : trace) {
            if(s.toString().contains("com.mobile")||s.toString().contains("com.core")){
                errorMsg.append("\tat ");
                errorMsg.append(s);
                errorMsg.append("\r\n");
            }
        }
        String cla = joinPoint.getTarget().getClass().getName();//action
        String method = joinPoint.getSignature().getName();//method
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        StringBuffer sb = new StringBuffer();
        sb.append("\r\n-----------" + sdf.format(new Date()) + "------------\r\n");
        sb.append("接口方法：" + cla + "." + method + "\r\n");
        sb.append("错误信息：" + ex.toString() + "\r\n");
        sb.append(errorMsg + "\r\n");
        myLogger.errorAspect(sb.toString());
    }


}
