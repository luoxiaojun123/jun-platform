package com.xiaojun.config;

import com.xiaojun.mapper.CronConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author long.luo
 * @date 2018/9/26 9:57
 */
@Component
@Slf4j
public class ScheduleRefreshDatabase {

    @Autowired
    private CronConfigMapper cronConfigMapper;
    @Autowired
    private CronTrigger cronTrigger;
    @Autowired
    @Qualifier("schedulerTask")
    private Scheduler scheduler;

    @Scheduled(fixedRate = 5000)
    public void scheduleUpdateCronTrigger() throws SchedulerException {
        CronTrigger trigger = (CronTrigger) scheduler.getTrigger(cronTrigger.getKey());
        String currentCron = trigger.getCronExpression();
        String searchCron = cronConfigMapper.selectById(3).getCron();
        log.info("currentCron:{}",currentCron);
        log.info("searchCron:{}",searchCron);
        if (currentCron.equals(searchCron)){
            return;
        }
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(searchCron);
        trigger = trigger.getTriggerBuilder().withIdentity(cronTrigger.getKey()).withSchedule(scheduleBuilder).build();
        scheduler.rescheduleJob(cronTrigger.getKey(),trigger);
    }
}
