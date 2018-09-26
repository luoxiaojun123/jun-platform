package com.xiaojun;

import com.xiaojun.entity.CronConfig;
import com.xiaojun.mapper.CronConfigMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * mybatis-plus
 *
 * @author xiaojun
 * @date 2018-08-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = QuartzApplication.class)
@Slf4j
public class MybatisPlusTest {

    @Autowired
    private CronConfigMapper cronConfigMapper;

    @Test
    public void testInfo() {
        CronConfig cronConfig = new CronConfig();
        cronConfig.setName("第一定时任务");
        cronConfig.setCron("0 0 * * *?");
        cronConfig.setStatus(1);
        cronConfig.setRemark("测试");
        cronConfigMapper.insert(cronConfig);
    }
}
