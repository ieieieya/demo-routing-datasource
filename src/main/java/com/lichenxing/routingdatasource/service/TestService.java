package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.feign.UserFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationPreparedEvent;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequestFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.SmartLifecycle;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * TestService
 *
 * @author Chenxing Li
 * @date 29/08/2017 15:06
 */
@Slf4j
@Service
public class TestService implements ApplicationListener<ApplicationPreparedEvent>, InitializingBean, DisposableBean, SmartLifecycle {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private LoadBalancerRequestFactory loadBalancerRequestFactory;

    @Autowired
    private UserFeignClient userFeignClient;

    public void testhaha() {
        log.info("#################################### hahahaha start");
        ResponseEntity<Object> health = userFeignClient.health();
        log.info("something:{}", health);
        log.info("#################################### hahahaha end");
    }


    public void test(String instanceId) throws IOException {
//        log.info("services:{}", discoveryClient.getServices());
//        List<ServiceInstance> kceRegistryExample = discoveryClient.getInstances(instanceId);
//        log.info("instances:{}", kceRegistryExample);

        log.info("instanceId:{}", instanceId);
        ServiceInstance choose = loadBalancerClient.choose(instanceId);
        log.info("host:{}", choose.getHost());
        log.info("port:{}", choose.getPort());
//        loadBalancerClient.execute(instanceId, loadBalancerRequestFactory.createRequest(null, null, null));
    }

    public void getUser(String username) {
        log.info("get user username:{}", username);
        ResponseEntity<Object> user = userFeignClient.getUser(username);
        log.info("get user end username:{} status:{} body:{}", username, user.getStatusCode(), user.getBody());
    }

    @Override
    public void onApplicationEvent(ApplicationPreparedEvent event) {
        log.info("##############  Application prepared event");
        try {
//            testhaha();
//            Thread.sleep(5000L);
            log.info("##############  Application prepared event end");
        } catch (Exception e) {
            log.error("start error", e);
        }
    }

    @PostConstruct
    public void postC() {
        log.info("############## PostConstruct");
    }

    @PreDestroy
    public void preD() {
        log.info("############## PreDestroy");
    }

    @Override
    public void destroy() throws Exception {
        log.info("############## DisposableBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        log.info("############## InitializingBean");
    }

    private AtomicBoolean running = new AtomicBoolean(false);

    @Override
    public boolean isAutoStartup() {
        return true;
    }

    @Override
    public void stop(Runnable callback) {
        log.info("############## SmartLifecycle STOPPPPPPPPPPPPPPPPPP WITH callback");
        stop();
        callback.run();
        running.set(false);
        log.info("############## SmartLifecycle STOPPPPPPPPPPPPPPPPPP end WITH callback");
    }

    @Override
    public void start() {
        log.info("############## SmartLifecycle STARTTTTTTTTTTTTTTTT");
        running.set(true);
//        testhaha();
    }

    @Override
    public void stop() {
        log.info("############## SmartLifecycle STOPPPPPPPPPPPPPPPPPP");
        running.set(false);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("############## SmartLifecycle STOPPPPPPPPPPPPPPPPPP end");
    }

    @Override
    public boolean isRunning() {
        return running.get();
    }

    @Override
    public int getPhase() {
        return 200;
    }
}
