package com.lichenxing.routingdatasource.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * EnableShard
 *
 * @author Chenxing Li
 * @date 01/08/2017 20:50
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(ShardInfoRegistrar.class)
public @interface EnableShard {

    String[] value() default {};

    String dataSource() default "routingDataSource";

}
