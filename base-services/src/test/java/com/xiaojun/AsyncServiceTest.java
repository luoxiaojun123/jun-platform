package com.xiaojun;

import com.xiaojun.service.AsyncService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author long.luo
 * @date 2018/9/27 14:59
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaseServiceApplication.class)
@Slf4j
public class AsyncServiceTest {

    @Autowired
    private AsyncService asyncService;

    @Test
    public void asyncServiceTest() throws Exception {
        log.info("开始执行");

        asyncService.asyncInvoke(() -> {
            this.execute();
            log.info("异步方法执行体");
        });

        log.info("执行完毕");
    }

    private void execute() {
        log.info("我执行了");
    }
}
