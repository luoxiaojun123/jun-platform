package com.xiaojun;

import com.xiaojun.utils.ExcelFileUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author long.luo
 * @date 2019/3/12 9:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BaseServiceApplication.class)
@Slf4j
public class ExcelFileTest {

    @Autowired
    private ExcelFileUtil excelFileUtil;

    @Test
    public void testExcel() throws Exception {
        excelFileUtil.excel();
    }
}
