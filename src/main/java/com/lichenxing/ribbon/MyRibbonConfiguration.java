package com.lichenxing.ribbon;

import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * MyRibbonConfiguration
 *
 * @author Chenxing Li
 * @date 30/08/2017 10:20
 */
@Configuration
public class MyRibbonConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ServerListUpdater serverListUpdater(IClientConfig clientConfig) {
        return new PollingServerListUpdater(clientConfig);
    }

    @Bean
    @ConditionalOnMissingBean
    public ILoadBalancer ribbonLoadBalancer(IClientConfig config,
                                            ServerList<Server> serverList, ServerListFilter<Server> serverListFilter,
                                            IRule rule, IPing ping, ServerListUpdater serverListUpdater) {
        return new ZoneAwareLoadBalancer<>(config, rule, ping, serverList, serverListFilter, serverListUpdater);
    }

}
