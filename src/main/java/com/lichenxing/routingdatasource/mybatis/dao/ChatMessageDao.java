package com.lichenxing.routingdatasource.mybatis.dao;

import com.lichenxing.routingdatasource.annotation.ShardInfo;
import com.lichenxing.routingdatasource.annotation.ShardOn;
import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.mybatis.DbContext;
import com.lichenxing.routingdatasource.mybatis.mapper.ChatMessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * ChatMessageDao
 *
 * @author Chenxing Li
 * @date 2017/12/27 14:40
 */
@ShardInfo
@Component
public class ChatMessageDao {

    @Autowired
    private ChatMessageMapper chatMessageMapper;

    public ChatMessage findById(@ShardOn Integer tenantId, String msgId) {
        return chatMessageMapper.findById(DbContext.getDbIndex(), msgId);
    }


}
