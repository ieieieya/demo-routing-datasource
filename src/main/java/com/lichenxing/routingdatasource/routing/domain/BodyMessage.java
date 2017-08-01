package com.lichenxing.routingdatasource.routing.domain;

import lombok.Data;

/**
 * BodyMessage
 *
 * @author Chenxing Li
 * @date 24/07/2017 16:45
 */
@Data
public class BodyMessage  {

    private String body;

    public BodyMessage() {
        super();
    }

    public BodyMessage(String body) {
        super();
        this.body = body;
    }

}
