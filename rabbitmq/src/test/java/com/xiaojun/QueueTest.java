package com.xiaojun;

import com.xiaojun.config.ExpirationMessagePostProcessor;
import com.xiaojun.config.MessageConstant;
import com.xiaojun.config.QueueConfig;
import com.xiaojun.config.Receiver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

/**
 * @author long.luo
 * @date 2019/3/11 14:15
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueueTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testDelayQueuePerMessageTTL() throws InterruptedException {
        Receiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            long expiration = i * 1000;
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_MESSAGE_TTL_NAME,
                    (Object) ("Message From delay_queue_per_message_ttl with expiration " + expiration), new ExpirationMessagePostProcessor(expiration));
        }
        Receiver.latch.await();
    }

    @Test
    public void testDelayQueuePerQueueTTL() throws InterruptedException {
        Receiver.latch = new CountDownLatch(3);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_QUEUE_PER_QUEUE_TTL_NAME,
                    "Message From delay_queue_per_queue_ttl with expiration " + QueueConfig.QUEUE_EXPIRATION);
        }
        Receiver.latch.await();
    }

    @Test
    public void testFailMessage() throws InterruptedException {
        Receiver.latch = new CountDownLatch(6);
        for (int i = 1; i <= 3; i++) {
            rabbitTemplate.convertAndSend(QueueConfig.DELAY_PROCESS_QUEUE_NAME, MessageConstant.FAIL_MESSAGE);
        }
        Receiver.latch.await();
    }
}
