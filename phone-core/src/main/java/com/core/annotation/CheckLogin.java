package com.core.annotation;

import java.lang.annotation.*;

/**
 * Created by Miracle on 2020/8/16.
 * 验证是否登录
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(value = {ElementType.METHOD})
@Documented
public @interface CheckLogin {

}
