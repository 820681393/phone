package com.core.annotation;

import java.lang.annotation.*;

/**
 * <p>
 *
 * @author Gray
 * @Description </p>
 * @since 2020/12/18
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ExcelColumn {
    /**
     * Excel标题
     *
     * @return
     * @author Lynch
     */
    String value() default "";

    /**
     * Excel从左往右排列位置
     *
     * @return
     * @author Lynch
     */
    int col() default 0;
}
