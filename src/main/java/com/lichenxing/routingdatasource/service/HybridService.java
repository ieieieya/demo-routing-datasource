package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.jpa.ChatMessageRepository;
import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import com.lichenxing.routingdatasource.routing.jpa.RoutingChatMessageRepository;
import com.lichenxing.routingdatasource.service.ChatMessageService;
import com.lichenxing.routingdatasource.service.RoutingChatMessageService;
import com.lichenxing.routingdatasource.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * HybridService
 *
 * 测试在主库和分库同时使用时候的的事务支持
 *
 * @author Chenxing Li
 * @date 19/07/2017 12:16
 */
@Slf4j
@Service
public class HybridService {

    private ChatMessageService chatMessageService;
    private RoutingChatMessageService routingChatMessageService;


    @Autowired
    public HybridService(ChatMessageService chatMessageService, RoutingChatMessageService routingChatMessageService) {
        this.chatMessageService = chatMessageService;
        this.routingChatMessageService = routingChatMessageService;
    }

    @Transactional
    public void save(Integer tenantId, String body) {
        log.info("saving tenantId:{} body:{}", tenantId, body);

        ChatMessage chatMessage = chatMessageService.saveChatMessage(tenantId, body);
        log.info("ChatMessage saved {}", JSONUtil.mapToJsonString(chatMessage));

        RoutingChatMessage routingChatMessage = routingChatMessageService.saveRoutingChatMessage(tenantId, body);
        log.info("RoutingChatMessage saved {}", JSONUtil.mapToJsonString(routingChatMessage));
    }

    @Transactional
    public void save2(Integer tenantId, String body) {
        log.info("saving tenantId:{} body:{}", tenantId, body);

        RoutingChatMessage routingChatMessage = routingChatMessageService.saveRoutingChatMessage(tenantId, body);
        log.info("RoutingChatMessage saved {}", JSONUtil.mapToJsonString(routingChatMessage));

        ChatMessage chatMessage = chatMessageService.saveChatMessage(tenantId, body);
        log.info("ChatMessage saved {}", JSONUtil.mapToJsonString(chatMessage));

    }

    @Transactional(transactionManager = "routingTransactionManager")
    public void save3(Integer tenantId, String body) {
        log.info("saving tenantId:{} body:{}", tenantId, body);

        RoutingChatMessage routingChatMessage = routingChatMessageService.saveRoutingChatMessage(tenantId, body);
        log.info("RoutingChatMessage 1 saved {}", JSONUtil.mapToJsonString(routingChatMessage));

        RoutingChatMessage routingChatMessage2 = routingChatMessageService.saveRoutingChatMessage(tenantId + 1, body);
        log.info("RoutingChatMessage 2 saved {}", JSONUtil.mapToJsonString(routingChatMessage2));



    }

}
