package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.annotation.ShardInfo;
import org.aopalliance.aop.Advice;
import org.springframework.aop.support.StaticMethodMatcherPointcutAdvisor;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * ShardInfoAdvisor
 *
 * @author Chenxing Li
 * @date 02/08/2017 16:26
 */
public class ShardInfoAdvisor extends StaticMethodMatcherPointcutAdvisor {

    private final ShardInfoInterceptor shardInfoInterceptor;

    public ShardInfoAdvisor(ShardInfoInterceptor shardInfoInterceptor) {
        this.shardInfoInterceptor = shardInfoInterceptor;
    }

    @Override
    public Advice getAdvice() {
        return shardInfoInterceptor;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

    @Override
    public boolean matches(Method method, Class<?> targetClass) {
        return targetClass.isAnnotationPresent(ShardInfo.class);
    }
}
