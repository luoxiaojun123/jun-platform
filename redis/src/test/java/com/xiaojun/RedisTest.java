package com.xiaojun;

import com.xiaojun.cache.User;
import com.xiaojun.cache.UserService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author long.luo
 * @date 2018/9/26 11:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = RedisApplication.class)
@Slf4j
public class RedisTest {

    @Autowired
    private UserService userService;

    @Test
    public void get() {
        final User user = userService.saveOrUpdate(new User(6L, "u5", "p8"));
        log.info("[saveOrUpdate] - [{}]", user);
        final User user1 = userService.get(6L);
        log.info("[get] - [{}]", user1);
        userService.delete(5L);
    }

    @Test
    public void update() throws Exception {

        for (int i = 0; i < 2; i++) {
            new Thread(() -> {
                User user = new User();
                user.setId(5L);
                userService.update(user);
                log.info("执行了一次");
            }).start();
        }

        Thread.sleep(10 * 1000);
    }
}
