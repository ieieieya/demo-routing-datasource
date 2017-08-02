package com.lichenxing.routingdatasource.conf;

import com.lichenxing.routingdatasource.annotation.ShardInfo;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractBeanFactoryPointcutAdvisor;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * TestAdvisor
 *
 * @author Chenxing Li
 * @date 02/08/2017 16:26
 */
public class ShardInfoAdvisor extends AbstractBeanFactoryPointcutAdvisor {

    private final StaticMethodMatcherPointcut pointcut = new StaticMethodMatcherPointcut() {
        @Override
        public boolean matches(Method method, Class<?> targetClass) {
            return targetClass.isAnnotationPresent(ShardInfo.class);
        }
    };

    private final ShardInfoInterceptor shardInfoInterceptor;

    public ShardInfoAdvisor(ShardInfoInterceptor shardInfoInterceptor) {
        this.shardInfoInterceptor = shardInfoInterceptor;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return shardInfoInterceptor;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }
}
