package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import com.lichenxing.routingdatasource.routing.jpa.RoutingChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.UUID;

/**
 * RoutingChatMessageService
 *
 * @author Chenxing Li
 * @date 19/07/2017 12:20
 */
@Slf4j
@Service
public class RoutingChatMessageService {
    private final RoutingChatMessageRepository routingChatMessageRepository;

    @Autowired
    public RoutingChatMessageService(RoutingChatMessageRepository routingChatMessageRepository) {
        this.routingChatMessageRepository = routingChatMessageRepository;
    }

    @Transactional()
    public RoutingChatMessage saveRoutingChatMessage(Integer tenantId, String body) {
        RoutingChatMessage routingChatMessage = new RoutingChatMessage();
        routingChatMessage.setMsgId(UUID.randomUUID().toString());
        routingChatMessage.setTenantId(tenantId);
        routingChatMessage.setBody(body);
        routingChatMessage.setCreatedAt(new Date());
        routingChatMessage.setUpdatedAt(new Date());
        routingChatMessageRepository.save(routingChatMessage);
        if (tenantId == 1410) {
            throw new IllegalArgumentException("RoutingChatMessage test rollback");
        }
        return routingChatMessage;
    }
}
