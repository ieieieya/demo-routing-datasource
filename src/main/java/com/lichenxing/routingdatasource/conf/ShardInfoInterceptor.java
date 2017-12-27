package com.lichenxing.routingdatasource.conf;


import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.datasource.MultipleDataSource;
import com.lichenxing.routingdatasource.mybatis.DbContext;
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
 * @link https://tech.meituan.com/mtddl.html
 * 美团的分库分表算法
 * 分表算法为 (#shard_key % (group_shard_num * table_shard_num))，
 * 分库算法为 (#shard_key % (group_shard_num * table_shard_num)) / table_shard_num，
 * 其中group_shard_num为分库个数，table_shard_num为每个库的分表个数。
 * 例如把一张大表分成100张小表然后散到2个库，则0-49落在第一个库、50-99落在第二个库
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
                        DbContext.setDbIndex(generateDbIndex(tenantId));
                    } else if (o != null && o instanceof Integer) {
                        log.info("ShardOn found index:{} value:{}", index, o);
                        Integer tenantId = (Integer) o;
                        ((MultipleDataSource) routingDataSource).setDataSourceKey(shardOn.readOnly(), tenantId);
                        DbContext.setDbIndex(generateDbIndex(tenantId));
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

    private String  generateDbIndex(Integer tenantId) {
        int index = tenantId % (3 * 3) + 1;
        return "_" + index;
    }
}
