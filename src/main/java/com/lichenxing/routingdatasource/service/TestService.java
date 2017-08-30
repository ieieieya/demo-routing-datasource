package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.cloud.netflix.ribbon.apache.RibbonLoadBalancingHttpClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * TestService
 *
 * @author Chenxing Li
 * @date 29/08/2017 15:06
 */
@Slf4j
@Service
public class TestService {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private LoadBalancerRequestFactory loadBalancerRequestFactory;

    @Autowired
    private UserFeignClient userFeignClient;


    public void test(String instanceId) throws IOException {
//        log.info("services:{}", discoveryClient.getServices());
//        List<ServiceInstance> kceRegistryExample = discoveryClient.getInstances(instanceId);
//        log.info("instances:{}", kceRegistryExample);

        log.info("instanceId:{}", instanceId);
        ServiceInstance choose = loadBalancerClient.choose(instanceId);
        log.info("host:{}", choose.getHost());
        log.info("port:{}", choose.getPort());
        loadBalancerClient.execute(instanceId, loadBalancerRequestFactory.createRequest(null, null, null));
    }

    public void getUser(String username) {
        log.info("get user username:{}", username);
        ResponseEntity<Object> user = userFeignClient.getUser(username);
        log.info("get user end username:{} status:{} body:{}", username, user.getStatusCode(), user.getBody());
    }
}
