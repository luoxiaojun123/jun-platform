package com.xiaojun.api.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author long.luo
 * @date 2018/9/13 14:04
 */
@Configuration
public class FeginConfig {

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
