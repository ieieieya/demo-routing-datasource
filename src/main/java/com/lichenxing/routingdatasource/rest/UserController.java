package com.lichenxing.routingdatasource.rest;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * UserController
 *
 * @author Chenxing Li
 * @date 30/08/2017 17:50
 */
@Slf4j
@RestController
public class UserController {

    @RequestMapping(path = "/v5/user")
    public ResponseEntity<Object> getUser(@RequestParam(name = "username", required = false) String username) {
        log.info("GET /v5/user username:{}", username);
        Map<String, Object> map = new HashMap<>();
        map.put("user_id", UUID.randomUUID().toString());
        map.put("username", username);
        map.put("age", RandomUtils.nextInt(30));
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
