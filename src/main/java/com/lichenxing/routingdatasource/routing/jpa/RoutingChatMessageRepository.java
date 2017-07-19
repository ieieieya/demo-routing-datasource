package com.lichenxing.routingdatasource.routing.jpa;

import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * ChatMessageRepository
 *
 * @author Chenxing Li
 * @date 18/07/2017 20:49
 */
public interface RoutingChatMessageRepository extends CrudRepository<RoutingChatMessage, String> {

    List<RoutingChatMessage> findByTenantIdOrderByCreatedAtDesc(@ShardOn Integer tenantId);

}
