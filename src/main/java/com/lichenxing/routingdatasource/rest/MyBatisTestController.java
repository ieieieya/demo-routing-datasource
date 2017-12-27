package com.lichenxing.routingdatasource.rest;

import com.lichenxing.routingdatasource.domain.ChatMessage;
import com.lichenxing.routingdatasource.mybatis.dao.ChatMessageDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * MyBatisTestController
 *
 * @author Chenxing Li
 * @date 2017/12/27 12:52
 */
@Slf4j
@RestController
public class MyBatisTestController {

    @Autowired
    private ChatMessageDao chatMessageDao;

    @RequestMapping(path = "/v5/mybatis/t1", method = RequestMethod.GET)
    public Object t1(@RequestParam(name = "tenantId", required = false, defaultValue = "1410") Integer tenantId,
                     @RequestParam(name = "msgId", required = false, defaultValue = "07a32d5a-2ef6-425d-b289-b2af3dc3a794") String msgId) {
        ChatMessage chatMessage = chatMessageDao.findById(tenantId, msgId);
        return chatMessage;
    }
}
