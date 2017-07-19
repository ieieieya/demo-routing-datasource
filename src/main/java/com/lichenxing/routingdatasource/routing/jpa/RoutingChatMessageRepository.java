package com.lichenxing.routingdatasource.routing.jpa;

import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ChatMessageRepository
 *
 * Override原有的save delete，参数增加ShardOn Annotation
 * 其余CrudRepository中携带的方法，只有Override了之后才可以使用，否则会有问题
 *
 * @author Chenxing Li
 * @date 18/07/2017 20:49
 */
public interface RoutingChatMessageRepository extends CrudRepository<RoutingChatMessage, String> {

    List<RoutingChatMessage> findByTenantIdOrderByCreatedAtDesc(@ShardOn Integer tenantId);

    RoutingChatMessage findByTenantIdAndMsgId(@ShardOn Integer tenantId, String msgId);

    @Override
    @Transactional
    RoutingChatMessage save(@ShardOn("tenantId") RoutingChatMessage chatMessage);

    @Transactional
    void deleteByTenantIdAndMsgId(@ShardOn Integer tenantId, String msgId);

    @Override
    @Transactional
    void delete(@ShardOn("tenantId") RoutingChatMessage chatMessage);
}
