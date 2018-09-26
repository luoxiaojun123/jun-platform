package com.xiaojun.scheduler;

import org.quartz.Scheduler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author long.luo
 * @date 2018/9/26 11:29
 */
@Configuration
public class QuartzConfig {
    @Autowired
    private JobFactory jobFactory;

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        schedulerFactoryBean.setJobFactory(jobFactory);
        schedulerFactoryBean.setStartupDelay(20);
        //schedulerFactoryBean.setQuartzProperties(); 用于quartz集群,加载quartz数据源配置
        return schedulerFactoryBean;
    }

    @Bean("scheduler")
    public Scheduler scheduler(){
        return  schedulerFactoryBean().getScheduler();
    }
}
