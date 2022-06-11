package com.core.utils;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class ApplicationContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextUtil.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    public static <T> T getBean(Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext..faild");
        }
        return applicationContext.getBean(cls);
    }

    public static Object getBean(String name) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext..faild");
        }
        return applicationContext.getBean(name);
    }

    public static <T> T getBean(String name, Class<T> cls) {
        if (applicationContext == null) {
            throw new RuntimeException("applicationContext..faild");
        }
        return applicationContext.getBean(cls.getSimpleName() + "." + name, cls);
    }
}
