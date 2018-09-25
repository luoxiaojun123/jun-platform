package com.xiaojun;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author long.luo
 * @date 2018/9/20 13:28
 */
@SpringBootApplication
@MapperScan("com.xiaojun.mapper")
public class QuartzApplication {
}
