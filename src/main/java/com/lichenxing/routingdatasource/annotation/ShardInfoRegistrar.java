package com.lichenxing.routingdatasource.annotation;

import com.lichenxing.routingdatasource.conf.ShardInfoAdvisor;
import com.lichenxing.routingdatasource.conf.ShardInfoInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

import java.util.Map;

/**
 * ShardInfoRegistrar
 *
 * @author Chenxing Li
 * @date 01/08/2017 20:47
 */
@Slf4j
public class ShardInfoRegistrar implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<String, Object> attributes = importingClassMetadata.getAnnotationAttributes(EnableShard.class.getCanonicalName());
        String dataSource = (String) attributes.get("dataSource");

        BeanDefinitionBuilder interceptorBuilder = BeanDefinitionBuilder.genericBeanDefinition(ShardInfoInterceptor.class);
        interceptorBuilder.addConstructorArgReference(dataSource);
        registry.registerBeanDefinition("shardInfoInterceptor", interceptorBuilder.getBeanDefinition());

        BeanDefinitionBuilder shardInfoAdvisorBuilder = BeanDefinitionBuilder.genericBeanDefinition(ShardInfoAdvisor.class);
        shardInfoAdvisorBuilder.addConstructorArgReference("shardInfoInterceptor");
        registry.registerBeanDefinition("shardInfoAdvisor", shardInfoAdvisorBuilder.getBeanDefinition());
    }

}
