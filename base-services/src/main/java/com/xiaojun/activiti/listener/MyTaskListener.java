package com.xiaojun.activiti.listener;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;

import java.util.Set;

/**
 * @author xiaojun
 * @date 2018/8/19 21:49
 */
@Component
@Slf4j
public class MyTaskListener implements TaskListener {
    @Override
    public void notify(DelegateTask delegateTask) {

        //任务办理人
        String assignee = delegateTask.getAssignee();

        //任务名称
        String eventName = delegateTask.getEventName();

        //事件名称
        String name = delegateTask.getName();

        //流程实例Id
        String processInstanceId = delegateTask.getProcessInstanceId();

        //获取当前流程实例范围内的所有流程变量的名字
        Set<String> variableNames = delegateTask.getVariableNames();

        for (String key : variableNames) {
            Object value = delegateTask.getVariable(key);
            System.out.println(key + " = " + value);
        }

        log.info("一个[{}]任务被创建了，由[{}]负责办理", name, assignee);

    }
}
