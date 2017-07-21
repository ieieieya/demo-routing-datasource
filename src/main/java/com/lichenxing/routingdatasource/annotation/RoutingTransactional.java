package com.lichenxing.routingdatasource.annotation;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * RoutingTransactional
 *
 * Transactional in routingTransactionManager
 * @author Chenxing Li
 * @date 20/07/2017 19:11
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Transactional
public @interface RoutingTransactional {

    String transactionManager() default "routingTransactionManager";

    Propagation propagation() default Propagation.REQUIRED;

}
