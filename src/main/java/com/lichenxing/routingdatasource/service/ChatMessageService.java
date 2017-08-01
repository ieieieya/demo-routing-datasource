package com.lichenxing.routingdatasource.service;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.jpa.ChatMessageRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * ChatMessageService
 *
 * @author Chenxing Li
 * @date 19/07/2017 12:19
 */
@Slf4j
@Service
public class ChatMessageService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatMessageService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    @Transactional
    public ChatMessage saveChatMessage(Integer tenantId, String body) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setMsgId(UUID.randomUUID().toString());
        chatMessage.setTenantId(tenantId);
        chatMessage.setBody(body);
        chatMessage.setCreatedAt(new Date());
        chatMessage.setUpdatedAt(new Date());
        chatMessageRepository.save(chatMessage);
        if (tenantId == 1411) {
            throw new IllegalArgumentException("ChatMessage test rollback");
        }
        return chatMessage;
    }

    public List<ChatMessage> findByTenantIdOrderByCreatedAtDesc(Integer tenantId) {
        return chatMessageRepository.findByTenantIdOrderByCreatedAtDesc(tenantId);
    }
}
