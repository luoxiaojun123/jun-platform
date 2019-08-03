package com.xiaojun.config;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;

/**
 * @author long.luo
 * @date 2019/3/11 14:06
 */
public class ExpirationMessagePostProcessor implements MessagePostProcessor {

    private Long ttl;

    public ExpirationMessagePostProcessor(Long ttl) {
        this.ttl = ttl;
    }

    @Override
    public Message postProcessMessage(Message message) throws AmqpException {
        message.getMessageProperties().setExpiration(ttl.toString());
        return message;
    }
}
