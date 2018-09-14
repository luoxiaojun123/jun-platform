package xiaojun;

import com.xiaojun.ConsumeApplication;
import com.xiaojun.api.ProvideClient;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * fegin测试类
 *
 * @author xiaojun
 * @date 2018-08-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ConsumeApplication.class)
@Slf4j
public class ConsumeTest {

    @Autowired
    public ProvideClient provideClient;

    @Test
    public void testInfo() throws Exception {
        System.out.println(provideClient.info("wo"));
        Thread.sleep(5 * 1000);
    }
}
