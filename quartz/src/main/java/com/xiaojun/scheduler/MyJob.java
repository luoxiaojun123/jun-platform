package com.xiaojun.scheduler;

import lombok.extern.slf4j.Slf4j;
import org.quartz.*;

import java.util.Date;

/**
 * @author long.luo
 * @date 2018/9/26 11:34
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class MyJob implements Job {

    private String name;

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        JobKey jobKey = context.getJobDetail().getKey();
        JobDataMap dataMap = context.getMergedJobDataMap();

        log.info("获取到的name:{}", dataMap.getString("name"));
        dataMap.put("name", new Date());

        log.info("key:{},名字:{},定时任务执行，时间:{}", jobKey.toString(), name, new Date());
    }
}
