package com.lichenxing.routingdatasource.rest;

import com.lichenxing.routingdatasource.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * TestController
 *
 * @author Chenxing Li
 * @date 29/08/2017 15:09
 */
@Slf4j
@RestController
public class TestController extends AbstractController {

    @Autowired
    private TestService testService;

    @RequestMapping(path = "/v5/test_instance")
    public String test(@RequestParam(name = "instance_id", required = false) String instanceId) throws IOException {
        log.info("GET /v5/test_instance instance_id:{}", instanceId);
        testService.test(instanceId);
        return "OK";
    }

    @RequestMapping(path = "/v5/test_get_user")
    public String testGetUser(@RequestParam(name = "username", required = false) String username) throws IOException {
        log.info("GET /v5/test_get_user username:{}", username);
        testService.getUser(username);
        return "OK";
    }

}
