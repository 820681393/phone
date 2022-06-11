package com.mobile.config;

import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

/**
 * @author Miracle
 * @date 2020/11/17 8:47
 */
@Configuration
public class SpringBootListener {

    @EventListener(WebServerInitializedEvent.class)
    public void onWebServerReady(WebServerInitializedEvent event) {
        WebServerApplicationContext applicationContext = event.getApplicationContext();
        System.out.println("前端接口模块==================================启动端口：" + applicationContext.getWebServer().getPort());
        System.out.println("前端接口模块==================================启动环境：" + applicationContext.getEnvironment().getActiveProfiles()[0]);
        System.out.println("前端接口模块==================================redis地址：" + applicationContext.getEnvironment().getProperty("spring.redis.host"));
        System.out.println("前端接口模块==================================redis端口：" + applicationContext.getEnvironment().getProperty("spring.redis.port"));
        String url = applicationContext.getEnvironment().getProperty("spring.datasource.url");
        System.out.println("前端接口模块==================================数据库地址：" + url.substring(url.indexOf("//") + 2, url.lastIndexOf(":")));
        System.out.println("前端接口模块==================================数据库端口：" + url.substring(url.lastIndexOf(":"), url.lastIndexOf("/")));
    }
}
