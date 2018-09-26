package com.xiaojun.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 * @author long.luo
 * @date 2018/9/26 13:36
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class MyJob2 implements Job {

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();

        log.info("key:{},定时任务======2======执行，时间:{}", jobKey.toString(), new Date());
    }
}
