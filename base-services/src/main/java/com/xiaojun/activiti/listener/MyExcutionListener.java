package com.xiaojun.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.ExecutionListener;
import org.springframework.stereotype.Component;

/**
 * @author xiaojun
 * @date 2018/8/19 21:40
 */
@Component
@Slf4j
public class MyExcutionListener implements ExecutionListener {

    @Override
    public void notify(DelegateExecution execution) {
        log.info("Activiti自定义的监听器执行了");
    }
}
