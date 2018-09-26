package com.xiaojun.config;

import com.xiaojun.task.ScheduleTask;
import org.quartz.Trigger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author long.luo
 * @date 2018/9/25 17:41
 */
@Configuration
public class QuartzConfiguration {


    @Bean(name = "jobDetail")
    public MethodInvokingJobDetailFactoryBean detailFactoryBean(ScheduleTask task) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        /*
         * 是否并发执行.例如每5s执行一次任务，但是当前任务还没有执行完，就已经过了5s了，
         * 如果此处为true，则下一个任务会执行，如果此处为false，则下一个任务会等待上一个任务执行完后，再开始执行
         */
        jobDetail.setConcurrent(false);
        // 设置任务名称
        jobDetail.setName("jun");
        jobDetail.setGroup("luo");

        // 为需要执行的实体类对象
        jobDetail.setTargetObject(task);
        jobDetail.setTargetMethod("sayHello");
        return jobDetail;
    }

    @Bean(name = "jobTrigger")
    public CronTriggerFactoryBean cronJobTrigger(MethodInvokingJobDetailFactoryBean jobDetail) {
        CronTriggerFactoryBean tigger = new CronTriggerFactoryBean();
        tigger.setJobDetail(jobDetail.getObject());
        tigger.setCronExpression("0 30 20 * * ?");
        tigger.setName("jun");
        return tigger;
    }

    @Bean(name = "schedulerTask")
    public SchedulerFactoryBean schedulerFactoryBean(Trigger cronJobTrigger){
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        bean.setOverwriteExistingJobs(true);
        bean.setStartupDelay(1);
        bean.setTriggers(cronJobTrigger);
        return bean;
    }

}
