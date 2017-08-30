package com.lichenxing.routingdatasource.conf;

import com.lichenxing.ribbon.MyRibbonConfiguration;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Configuration;

/**
 * MyRibbonAutoConfiguration
 *
 * @author Chenxing Li
 * @date 30/08/2017 10:18
 */
@Configuration
@RibbonClients(defaultConfiguration = MyRibbonConfiguration.class)
public class MyRibbonAutoConfiguration {
}
