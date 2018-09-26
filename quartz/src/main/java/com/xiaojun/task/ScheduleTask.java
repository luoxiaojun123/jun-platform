package com.xiaojun.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author long.luo
 * @date 2018/9/26 9:38
 */
@Component
@EnableScheduling
@Slf4j
public class ScheduleTask {

    public void sayHello() {
        log.info("Hello world, i'm the king of the world!!!");
    }
}
