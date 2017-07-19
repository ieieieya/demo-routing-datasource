package com.lichenxing.routingdatasource.rest;

import com.lichenxing.routingdatasource.service.HybridService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * HybridController
 *
 * @author Chenxing Li
 * @date 19/07/2017 12:42
 */
@Slf4j
@RestController
public class HybridController {

    @Autowired
    private HybridService hybridService;

    @Data
    public static class MessageRequestBody {

        private String body;

    }

    @RequestMapping(value = "/v5/{tenantId}/hybrid-save", method = RequestMethod.POST)
    public Object hybridSave(@PathVariable("tenantId") Integer tenantId,
                           @RequestBody MessageRequestBody body) {
        hybridService.save(tenantId, body.getBody());
        return "OK";
    }

    @RequestMapping(value = "/v5/{tenantId}/hybrid-save2", method = RequestMethod.POST)
    public Object hybridSave2(@PathVariable("tenantId") Integer tenantId,
                             @RequestBody MessageRequestBody body) {
        hybridService.save2(tenantId, body.getBody());
        return "OK";
    }

    @RequestMapping(value = "/v5/{tenantId}/hybrid-save3", method = RequestMethod.POST)
    public Object hybridSave3(@PathVariable("tenantId") Integer tenantId,
                              @RequestBody MessageRequestBody body) {
        hybridService.save3(tenantId, body.getBody());
        return "OK";
    }
}
