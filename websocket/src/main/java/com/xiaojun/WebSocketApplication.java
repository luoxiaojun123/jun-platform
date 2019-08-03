package com.xiaojun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ConcurrentTaskScheduler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

import java.util.concurrent.Executors;

import static java.util.concurrent.Executors.*;

/**
 * @author long.luo
 * @date 2019/3/1 10:05
 */
@SpringBootApplication
@EnableScheduling
@EnableWebSocket
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class, args);
    }

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {
        return new ServerEndpointExporter();
    }

    /**
     * @EnableWebSocket with no further configuration might create a no op scheduler which will conflict with any other use of a task scheduler
     * @return
     */
    @Bean
    public TaskScheduler taskScheduler() {
        return new ConcurrentTaskScheduler(newSingleThreadScheduledExecutor());
    }
}
