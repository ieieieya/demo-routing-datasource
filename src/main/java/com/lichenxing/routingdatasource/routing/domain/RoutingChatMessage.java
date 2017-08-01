package com.lichenxing.routingdatasource.routing.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * ChatMessage
 *
 * @author Chenxing Li
 * @date 18/07/2017 20:45
 */
@Data
@Table(name = "chat_message")
@Entity
public class RoutingChatMessage {

    @Id
    @Column(name = "msgId")
    private String msgId;

    @Column(name = "tenantId")
    private Integer tenantId;

    @Column(name = "body")
    @Convert(converter = BodyMessageAttributeConverter.class)
    private BodyMessage body;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
    @Column(name = "updatedAt")
    private Date updatedAt;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSZ", timezone = "GMT+8")
    @Column(name = "createdAt")
    private Date createdAt;

}
