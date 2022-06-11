package com.mobile.config;

import com.github.xiaoymin.swaggerbootstrapui.annotations.EnableSwaggerBootstrapUI;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.core.utils.ResponseMessageUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by Administrator on 2019/10/9 0009.
 */
@Configuration
@EnableSwagger2
@EnableSwaggerBootstrapUI
public class SwaggerConfig {


    private static final String splitor = ";";

    @Bean
    public Docket createRestApi1() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        pars.add(tokenPar.build());
        return new Docket(DocumentationType.SWAGGER_2)
                .enable(true)
                .apiInfo(apiInfo("商城前台API", "1.1.1", "商城前台API", "商城前台API"))
                .select()
                .paths(PathSelectors.any())
                .apis(basePackage("com.mobile.controller"))
                .build()
                .groupName("商城前台API")
                .securitySchemes(securitySchemes())
                .globalResponseMessage(RequestMethod.GET, ResponseMessageUtils.getResponseState())
                .globalResponseMessage(RequestMethod.POST, ResponseMessageUtils.getResponseState())
                .securityContexts(securityContexts());
    }


    /**
     * @param title     标题
     * @param version   版本
     * @param describe  描述
     * @param introduce 简介
     * @return
     */
    private ApiInfo apiInfo(String title, String version, String describe, String introduce) {
        return new ApiInfoBuilder()
                .title(title)
                .termsOfServiceUrl(describe)
                .description(introduce)
                .version(version)
                .build();
    }


    private List<ApiKey> securitySchemes() {
        return newArrayList(
                new ApiKey("authorization", "authorization", "header"), new ApiKey("languageType", "languageType", "header"));
    }

    private List<SecurityContext> securityContexts() {
        return newArrayList(
                SecurityContext.builder()
                        .securityReferences(defaultAuth())
                        .forPaths(PathSelectors.regex("^(?!auth).*$"))
                        .build()
        );
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return newArrayList(
                new SecurityReference("authorization", authorizationScopes));
    }


    /**
     * 重写basePackage方法，使能够实现多包访问，复制贴上去
     *
     * @param basePackage
     * @return com.google.common.base.Predicate<springfox.documentation.RequestHandler>
     * @author teavamc
     * @date 2019/1/26
     */
    public static Predicate<RequestHandler> basePackage(final String basePackage) {
        return input -> declaringClass(input).transform(handlerPackage(basePackage)).or(true);
    }

    private static Function<Class<?>, Boolean> handlerPackage(final String basePackage) {
        return input -> {
            // 循环判断匹配
            for (String strPackage : basePackage.split(splitor)) {
                boolean isMatch = input.getPackage().getName().startsWith(strPackage);
                if (isMatch) {
                    return true;
                }
            }
            return false;
        };
    }

    private static Optional<? extends Class<?>> declaringClass(RequestHandler input) {
        return Optional.fromNullable(input.declaringClass());
    }

}
