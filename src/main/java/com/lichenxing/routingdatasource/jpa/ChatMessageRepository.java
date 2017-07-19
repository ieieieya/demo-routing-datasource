package com.lichenxing.routingdatasource.jpa;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ChatMessageRepository
 *
 * @author Chenxing Li
 * @date 18/07/2017 20:49
 */
public interface ChatMessageRepository extends CrudRepository<ChatMessage, String> {

    List<ChatMessage> findByTenantIdOrderByCreatedAtDesc(Integer tenantId);

}
