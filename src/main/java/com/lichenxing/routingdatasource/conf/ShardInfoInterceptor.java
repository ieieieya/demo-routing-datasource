package com.lichenxing.routingdatasource.conf;


import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.datasource.MultipleDataSource;
import lombok.extern.slf4j.Slf4j;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.StringUtils;

import javax.sql.DataSource;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * ShardInfoInterceptor
 *
 * @author Chenxing Li
 * @date 02/08/2017 16:24
 */
@Slf4j
public class ShardInfoInterceptor implements MethodInterceptor {

    private final DataSource routingDataSource;

    public ShardInfoInterceptor(DataSource routingDataSource) {
        this.routingDataSource = routingDataSource;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Annotation[][] parameterAnnotations = invocation.getMethod().getParameterAnnotations();
        if (parameterAnnotations != null) {
            boolean find = false;
            for (Annotation[] arr : parameterAnnotations) {
                int index = 0;
                String fieldName = null;
                ShardOn shardOn = null;
                for (Annotation annotation : arr) {
                    if (ShardOn.class.equals(annotation.annotationType())) {
                        shardOn = (ShardOn) annotation;
                        fieldName = shardOn.value();
                        find = true;
                        break;
                    }
                    index++;
                }
                if (find) {
                    log.info("ShardOn found index:{}", index);
                    Object o = invocation.getArguments()[index];
                    if (o != null && StringUtils.isNotBlank(fieldName)) {
                        log.info("ShardOn found with fieldName index:{} fieldName:{}", index, fieldName);
                        Field field = o.getClass().getDeclaredField(fieldName);
                        field.setAccessible(true);
                        Integer tenantId = (Integer) field.get(o);
                        log.info("ShardOn found with fieldName index:{} fieldName:{} tenantId:{}", index, fieldName, tenantId);
                        ((MultipleDataSource) routingDataSource).setDataSourceKey(shardOn.readOnly(), tenantId);
                    } else if (o != null && o instanceof Integer) {
                        log.info("ShardOn found index:{} value:{}", index, o);
                        Integer tenantId = (Integer) o;
                        ((MultipleDataSource) routingDataSource).setDataSourceKey(shardOn.readOnly(), tenantId);
                    }
                    break;
                }
            }
            if (!find) {
                log.error("ShardOn Annotation needed!!!" + invocation);
                throw new IllegalArgumentException("ShardOn Annotation needed!!!");
            }
        }
        Object result = invocation.proceed();
        log.info("Complete: " + invocation);
        return result;
    }
}
