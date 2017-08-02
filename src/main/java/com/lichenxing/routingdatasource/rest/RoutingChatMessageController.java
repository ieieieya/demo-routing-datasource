package com.lichenxing.routingdatasource.rest;

import com.lichenxing.routingdatasource.routing.domain.BodyMessage;
import com.lichenxing.routingdatasource.service.RoutingChatMessageService;
import com.lichenxing.routingdatasource.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * RoutingChatMessageController
 *
 * @author Chenxing Li
 * @date 19/07/2017 00:22
 */
@Slf4j
@RestController
public class RoutingChatMessageController {

    @Autowired
    private RoutingChatMessageService routingChatMessageService;

    @RequestMapping(value = "/v5/{tenantId}/routing-messages", method = RequestMethod.GET)
    public Object getMessages(@PathVariable("tenantId") Integer tenantId) {
        log.info("GET /v5/{}/routing-messages", tenantId);
        return routingChatMessageService.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    @RequestMapping(value = "/v5/{tenantId}/routing-messages", method = RequestMethod.POST)
    public Object saveMessages(@PathVariable("tenantId") Integer tenantId, @RequestBody BodyMessage chatMessage) {
        log.info("POST /v5/{}/routing-messages body:{}", tenantId, JSONUtil.mapToJsonString(chatMessage));
        return routingChatMessageService.save(tenantId, chatMessage.getBody());
    }

    @RequestMapping(value = "/v5/{tenantId}/routing-messages/{msgId}", method = RequestMethod.PUT)
    public Object updateMessages(@PathVariable("tenantId") Integer tenantId,
                                 @PathVariable("msgId") String msgId,
                                 @RequestBody BodyMessage updateBody) {
        log.info("PUT /v5/{}/routing-messages/{} body:{}", tenantId, msgId, JSONUtil.mapToJsonString(updateBody));
        return routingChatMessageService.update(tenantId, msgId, updateBody.getBody());
    }

    @RequestMapping(value = "/v5/{tenantId}/routing-messages/{msgId}", method = RequestMethod.DELETE)
    public Object deleteMessages(@PathVariable("tenantId") Integer tenantId,
                                 @PathVariable("msgId") String msgId) {
        log.info("DELETE /v5/{}/routing-messages/{}", tenantId, msgId);
        routingChatMessageService.deleteRoutingChatMessage(tenantId, msgId);
        return "OK";
    }

    @RequestMapping(value = "/v5/test1", method = RequestMethod.GET)
    public Object test1() {
        log.info("GET /v5/test1");
        routingChatMessageService.test1();
        return "OK";
    }

    @RequestMapping(value = "/v5/test2", method = RequestMethod.GET)
    public Object test2() {
        log.info("GET /v5/test2");
        routingChatMessageService.test2(1);
        return "OK";
    }

    @RequestMapping(value = "/v5/test5", method = RequestMethod.GET)
    public Object test5() {
        log.info("GET /v5/test5");
        routingChatMessageService.test5(2);
        return "OK";
    }

}
