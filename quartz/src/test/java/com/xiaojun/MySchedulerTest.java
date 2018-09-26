package com.xiaojun;

import com.xiaojun.scheduler.MyScheduler;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author long.luo
 * @date 2018/9/26 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = QuartzApplication.class)
@Slf4j
public class MySchedulerTest {

    @Autowired
    private MyScheduler myScheduler;

    @Test
    public void test() throws Exception {
        myScheduler.startJob("0/5 * * * * ? ", "group1", "job1", "com.xiaojun.scheduler.MyJob");
        myScheduler.startJob("0/5 * * * * ? ", "group2", "job2", "com.xiaojun.scheduler.MyJob2");
        myScheduler.startAllJob();
        Thread.sleep(10 * 1000);
    }

}
