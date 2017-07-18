package com.lichenxing.routingdatasource.rest;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.jpa.ChatMessageRepository;
import com.lichenxing.routingdatasource.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.Date;
import java.util.UUID;

/**
 * ChatMessageController
 *
 * @author Chenxing Li
 * @date 18/07/2017 20:50
 */
@Slf4j
@RestController
public class ChatMessageController {

    @Autowired
    private ChatMessageRepository chatMessageRepository;

    @RequestMapping(value = "/v5/{tenantId}/messages", method = RequestMethod.GET)
    public Object getMessages(@PathVariable("tenantId") Integer tenantId) {
        log.info("GET /v5/{}/messages", tenantId);
        return chatMessageRepository.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    @RequestMapping(value = "/v5/{tenantId}/messages", method = RequestMethod.POST)
    public Object saveMessages(@PathVariable("tenantId") Integer tenantId, @RequestBody ChatMessage chatMessage) {
        log.info("POST /v5/{}/messages body:{}", tenantId, JSONUtil.mapToJsonString(chatMessage));
        chatMessage.setMsgId(UUID.randomUUID().toString());
        chatMessage.setTenantId(tenantId);
        chatMessage.setCreatedAt(new Date());
        chatMessage.setUpdatedAt(new Date());
        chatMessageRepository.save(chatMessage);
        return chatMessage;
    }
}
