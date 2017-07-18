package com.lichenxing.routingdatasource.domain;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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
public class ChatMessage {

    @Id
    @Column(name = "msgId")
    private String msgId;

    @Column(name = "tenantId")
    private Integer tenantId;

    @Column(name = "body")
    private String body;

    @Column(name = "updatedAt")
    private Date updatedAt;

    @Column(name = "createdAt")
    private Date createdAt;

}
