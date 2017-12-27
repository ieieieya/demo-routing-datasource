package com.lichenxing.routingdatasource.mybatis.mapper;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ChatMessageMapper
 *
 * @author Chenxing Li
 * @date 2017/12/27 12:10
 */
@Mapper
public interface ChatMessageMapper {

    @Select("SELECT * FROM chat_message${dbIndex} WHERE msgId = #{msgId}")
    ChatMessage findById(@Param("dbIndex") String dbIndex, @Param("msgId") String msgId);
}
