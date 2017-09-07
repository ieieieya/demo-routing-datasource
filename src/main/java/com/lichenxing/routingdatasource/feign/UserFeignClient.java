package com.lichenxing.routingdatasource.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * UserFeignClient
 *
 * @author Chenxing Li
 * @date 30/08/2017 17:51
 */
@FeignClient(name = "${spring.application.name}", url = "${user.location:}")
public interface UserFeignClient {

    @RequestMapping(path = "/v5/user")
    ResponseEntity<Object> getUser(@RequestParam(name = "username", required = false) String username);

    @RequestMapping(path = "/management/health")
    ResponseEntity<Object> health();

}
