package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.datasource.MultipleDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Parameter;

/**
 * AopConfig
 *
 * @author Chenxing Li
 * @date 19/07/2017 01:06
 */
@Aspect
@Component
public class AopConfig {

    private static Logger log = LoggerFactory.getLogger(AopConfig.class);

    @Autowired
    private DataSource routingDataSource;

    @Around("execution(* com.lichenxing.routingdatasource.routing.jpa.*.*(..))")
    public Object logServiceAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start: " + joinPoint);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
//        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
//        if (parameterAnnotations != null) {
//            boolean find = false;
//            for (Annotation[] arr : parameterAnnotations) {
//                int index = 0;
//                for (Annotation annotation : arr) {
//                    if (ShardOn.class.equals(annotation.annotationType())) {
//                        find = true;
//                        break;
//                    }
//                    index++;
//                }
//                if (find) {
//                    log.info("Find! index:{}", index);
//                    Object o = joinPoint.getArgs()[index];
//                    log.info("Find! index:{} value:{}", index, o);
//                    if (o != null && o instanceof Integer) {
//                        Integer tenantId = (Integer) o;
//                        ((MultipleDataSource) routingDataSource).setDataSourceKey(tenantId);
//                    }
//                    break;
//                }
//            }
//        }
        Object[] args = joinPoint.getArgs();
        Integer tenantId = (Integer) args[0];
        ((MultipleDataSource) routingDataSource).setDataSourceKey(tenantId);
        Object result = joinPoint.proceed();
        log.info("Complete: " + joinPoint);
        return result;
    }

}
