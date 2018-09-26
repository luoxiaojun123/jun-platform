package com.xiaojun.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author long.luo
 * @date 2018/9/26 10:57
 */
@Component
@Slf4j
public class MyScheduler {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建任务
     *
     * @param cron
     * @param group
     * @param jobId
     * @param jobClassName
     * @throws Exception
     */
    public void startJob(String cron, String group, String jobId, String jobClassName) throws Exception {
        Class jobClass = Class.forName(jobClassName);
        // 创建jobDetail实例，绑定Job实现类
        // 指明job的名称，所在组的名称，以及绑定job类
        //设置Job的名字和组
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobId, group).build();
        jobDetail.getJobDataMap().put("name", "MyName");

        // corn表达式  每2秒执行一次
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        // 设置定时任务的时间触发规则
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobId, group).withSchedule(cronScheduleBuilder).build();

        // 把作业和触发器注册到任务调度中
        scheduler.scheduleJob(jobDetail, cronTrigger);
    }

    /**
     * 修改定时任务时间
     *
     * @param triggerName
     * @param triggerGroupName
     * @param cron
     */
    public void modifyJobTime(String triggerName, String triggerGroupName, String cron) throws Exception {
        TriggerKey triggerKey = new TriggerKey(triggerName, triggerGroupName);
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(triggerKey);
        if (trigger == null) {
            return;
        }

        log.info("触发器状态:{}", scheduler.getTriggerState(triggerKey));

        String oldCron = trigger.getCronExpression();
        if (oldCron.equals(cron)) {
            return;
        }

        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        // 按照新的cronExpression表达式重新构建trigger
        CronTrigger cronTrigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(cronScheduleBuilder).build();
        // 按新的trigger重新设置job执行
        scheduler.rescheduleJob(triggerKey, cronTrigger);
    }

    /**
     * 暂停一个任务
     *
     * @param triggerName
     * @param triggerGroupName
     */
    public void pauseJob(String triggerName, String triggerGroupName) throws SchedulerException {

        JobKey jobKey = new JobKey(triggerName, triggerGroupName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.pauseJob(jobKey);
    }

    /**
     * 删除一个任务
     *
     * @param triggerName
     * @param triggerGroupName
     */
    public void deleteJob(String triggerName, String triggerGroupName) throws SchedulerException {

        JobKey jobKey = new JobKey(triggerName, triggerGroupName);
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            return;
        }
        scheduler.deleteJob(jobKey);
    }

    /****
     * 恢复一个任务
     * @param triggerName
     * @param triggerGroupName
     */
    public void resumeJob(String triggerName, String triggerGroupName) {
        try {
            JobKey jobKey = new JobKey(triggerName, triggerGroupName);
            JobDetail jobDetail = scheduler.getJobDetail(jobKey);
            if (jobDetail == null) {
                return;
            }
            scheduler.resumeJob(jobKey);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    /**
     * 开启定时任务
     */
    public void startAllJob() throws SchedulerException {
        scheduler.start();
    }

    /***
     * 立即执行定时任务
     */
    public void doJob(String triggerName, String triggerGroupName) throws SchedulerException {
        JobKey jobKey = JobKey.jobKey(triggerName, triggerGroupName);
        scheduler.triggerJob(jobKey);
    }

    /**
     * 关闭定时任务
     *
     * @throws SchedulerException
     */
    public void shutdown() throws SchedulerException {
        scheduler.shutdown();
    }
}
