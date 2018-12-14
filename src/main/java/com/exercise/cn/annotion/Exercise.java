package com.exercise.cn.annotion;

import java.lang.annotation.*;

/**
 * @author mengyiren
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Exercise {
    String[] value() default {};

    String[] destation() default {};
}
