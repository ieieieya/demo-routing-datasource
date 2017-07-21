package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.datasource.MultipleDataSource;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * AopConfig
 *
 * @author Chenxing Li
 * @date 19/07/2017 01:06
 */
@Aspect
@Order(Ordered.HIGHEST_PRECEDENCE)
@Configuration
public class AopConfig {

    private static Logger log = LoggerFactory.getLogger(AopConfig.class);


    @Autowired
    private DataSource routingDataSource;

    @Around("execution(* com.lichenxing.routingdatasource.service.RoutingChatMessageService.*(..))")
    public Object logServiceAccess(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("Start: " + joinPoint);
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Annotation[][] parameterAnnotations = signature.getMethod().getParameterAnnotations();
        if (parameterAnnotations != null) {
            boolean find = false;
            for (Annotation[] arr : parameterAnnotations) {
                int index = 0;
                String fieldName = null;
                for (Annotation annotation : arr) {
                    if (ShardOn.class.equals(annotation.annotationType())) {
                        ShardOn shardOn = (ShardOn) annotation;
                        fieldName = shardOn.value();
                        find = true;
                        break;
                    }
                    index++;
                }
                if (find) {
                    log.info("ShardOn found index:{}", index);
                    Object o = joinPoint.getArgs()[index];
                    if (o != null && StringUtils.isNotBlank(fieldName)) {
                        log.info("ShardOn found with fieldName index:{} fieldName:{}", index, fieldName);
                        Field field = o.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Integer tenantId = (Integer) field.get(o);
                        log.info("ShardOn found with fieldName index:{} fieldName:{} tenantId:{}", index, fieldName, tenantId);
                        ((MultipleDataSource) routingDataSource).setDataSourceKey(tenantId);
                    } else if (o != null && o instanceof Integer) {
                        log.info("ShardOn found index:{} value:{}", index, o);
                        Integer tenantId = (Integer) o;
                        ((MultipleDataSource) routingDataSource).setDataSourceKey(tenantId);
                    }
                    break;
                }
            }
            if (!find) {
                log.error("ShardOn Annotation needed!!!" + joinPoint);
                throw new IllegalArgumentException("ShardOn Annotation needed!!!");
            }
        }
        Object result = joinPoint.proceed();
        log.info("Complete: " + joinPoint);
        return result;
    }

}
