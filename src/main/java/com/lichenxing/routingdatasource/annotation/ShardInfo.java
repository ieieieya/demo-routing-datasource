package com.lichenxing.routingdatasource.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * ShardInfo
 *
 * @author Chenxing Li
 * @date 01/08/2017 19:11
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ShardInfo {

    String value() default "";

}
