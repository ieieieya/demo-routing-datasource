package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.annotation.RoutingTransactional;
import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.routing.domain.BodyMessage;
import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import com.lichenxing.routingdatasource.routing.jpa.RoutingChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
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

//    @RoutingTransactional
    public List<RoutingChatMessage> findByTenantIdOrderByCreatedAtDesc(@ShardOn Integer tenantId) {
        return routingChatMessageRepository.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }

    @RoutingTransactional
    public List<RoutingChatMessage> saveRoutingChatMessage(@ShardOn Integer tenantId, String ... bodies) {
        List<RoutingChatMessage> resultList = new LinkedList<>();
        for (String body : bodies) {
            RoutingChatMessage routingChatMessage = new RoutingChatMessage();
            routingChatMessage.setMsgId(UUID.randomUUID().toString());
            routingChatMessage.setTenantId(tenantId);
            routingChatMessage.setBody(new BodyMessage(body));
            routingChatMessage.setCreatedAt(new Date());
            routingChatMessage.setUpdatedAt(new Date());
            routingChatMessageRepository.save(routingChatMessage);
            resultList.add(routingChatMessage);
        }
        return resultList;
    }

    @RoutingTransactional
    public void deleteRoutingChatMessage(@ShardOn Integer tenantId, String msgId) {
        routingChatMessageRepository.deleteByTenantIdAndMsgId(tenantId, msgId);
    }

    @RoutingTransactional
    public RoutingChatMessage save(@ShardOn Integer tenantId, String body) {
        RoutingChatMessage routingChatMessage = new RoutingChatMessage();
        routingChatMessage.setMsgId(UUID.randomUUID().toString());
        routingChatMessage.setTenantId(tenantId);
        routingChatMessage.setBody(new BodyMessage(body));
        routingChatMessage.setCreatedAt(new Date());
        routingChatMessage.setUpdatedAt(new Date());
        routingChatMessageRepository.save(routingChatMessage);
        return routingChatMessage;
    }

    @RoutingTransactional
    public RoutingChatMessage update(@ShardOn Integer tenantId, String msgId, String body) {
        RoutingChatMessage routingChatMessage = routingChatMessageRepository.findByTenantIdAndMsgId(tenantId, msgId);
        if (routingChatMessage == null) {
            return null;
        }
        routingChatMessage.setBody(new BodyMessage(body));
        routingChatMessage.setUpdatedAt(new Date());
        routingChatMessageRepository.save(routingChatMessage);
        return routingChatMessage;
    }


}
