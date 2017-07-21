package com.lichenxing.routingdatasource.routing.jpa;

import com.lichenxing.routingdatasource.routing.domain.RoutingChatMessage;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * RoutingChatMessageRepository
 *
 * 必须从RoutingChatMessageService调用，否则数据源不正确
 *
 * @author Chenxing Li
 * @date 18/07/2017 20:49
 */
public interface RoutingChatMessageRepository extends CrudRepository<RoutingChatMessage, String>, JpaSpecificationExecutor<RoutingChatMessage> {

    List<RoutingChatMessage> findByTenantIdOrderByCreatedAtDesc(Integer tenantId);

    RoutingChatMessage findByTenantIdAndMsgId(Integer tenantId, String msgId);

    void deleteByTenantIdAndMsgId(Integer tenantId, String msgId);

}
