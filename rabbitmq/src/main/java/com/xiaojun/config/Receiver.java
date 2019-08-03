package com.xiaojun.config;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;

/**
 * @author long.luo
 * @date 2019/3/11 14:09
 */
@Component
@Slf4j
public class Receiver {

    public static CountDownLatch latch;

    @RabbitListener(queues = QueueConfig.DELAY_PROCESS_QUEUE_NAME)
    public void process(Message message, Channel channel) throws IOException {
        try {
            processMessage(message);
        } catch (Exception e) {
            // 如果发生了异常，则将该消息重定向到缓冲队列，会在一定延迟之后自动重做
            channel.basicPublish(QueueConfig.PER_QUEUE_TTL_EXCHANGE_NAME, QueueConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME, null,
                    "The failed message will auto retry after a certain delay".getBytes());
        }

        if (latch != null) {
            latch.countDown();
        }
    }

    /**
     * 模拟消息处理。如果当消息内容为FAIL_MESSAGE的话，则需要抛出异常
     *
     * @param message
     * @throws Exception
     */
    public void processMessage(Message message) throws Exception {
        String realMessage = new String(message.getBody());
        log.info("Received <" + realMessage + ">");
        if (Objects.equals(realMessage, MessageConstant.FAIL_MESSAGE)) {
            throw new Exception("Some exception happened");
        }
    }
}
