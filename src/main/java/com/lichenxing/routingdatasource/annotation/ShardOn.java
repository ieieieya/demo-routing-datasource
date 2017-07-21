package com.lichenxing.routingdatasource.annotation;

import java.lang.annotation.*;

/**
 * ShardOn
 *
 * @author Chenxing Li
 * @date 19/07/2017 00:49
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShardOn {

    String value() default "";

}