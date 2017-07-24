package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.annotation.RoutingTransactional;
import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import com.lichenxing.routingdatasource.utils.JSONUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

        RoutingChatMessage routingChatMessage = routingChatMessageService.save(tenantId, body);
        log.info("RoutingChatMessage saved {}", JSONUtil.mapToJsonString(routingChatMessage));
    }

    @Transactional
    public void save2(Integer tenantId, String body) {
        log.info("saving tenantId:{} body:{}", tenantId, body);

        RoutingChatMessage routingChatMessage = routingChatMessageService.save(tenantId, body);
        log.info("RoutingChatMessage saved {}", JSONUtil.mapToJsonString(routingChatMessage));

        ChatMessage chatMessage = chatMessageService.saveChatMessage(tenantId, body);
        log.info("ChatMessage saved {}", JSONUtil.mapToJsonString(chatMessage));

    }


    /**
     * 进入方法save3之后，用transactionManager开始事务tx1，并且获取数据库链接
     * STEP2 在tx1中，保存chatMessage
     *
     * STEP3 根据shardOn，设置routing key
     * 用routingTransactionManager开启事务tx2，并且获取数据库链接
     *
     * tx2事务结束，commit
     *
     * tx1事务结束，commit
     * @param tenantId
     * @param body
     */
    @Transactional
    public void save3(Integer tenantId, String body) {
        log.info("STEP1 save3 start tenantId:{} body:{}", tenantId, body);

        log.info("STEP2 saving to origin tenantId:{} body:{}", tenantId, body);
        chatMessageService.saveChatMessage(tenantId, body + "chatchatchat");
        log.info("STEP3 saving to routing tenantId:{} body:{}", tenantId, body);
        routingChatMessageService.saveRoutingChatMessage(tenantId, body + "_1", body + "_2", body + "_3");

        log.info("STEP4 save3 end tenantId:{} body:{}", tenantId, body);
    }

}
