package com.xiaojun;

import com.xiaoleilu.hutool.io.FileUtil;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 工作流测试类
 *
 * @author xiaojun
 * @date 2018-08-19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ActivitiApplication.class)
@Slf4j
public class ActivitiTest {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    /**
     * 部署流程
     */
    @Test
    public void test() {
        DeploymentBuilder builder = repositoryService.createDeployment();
        Deployment deployment = builder.addClasspathResource("processes/transfer.bpmn").deploy();
        log.info("部署完成:{}", deployment.getId());
    }

    /**
     * 查询流程定义
     */
    @Test
    public void test1() {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.processDefinitionKey("transfer");
        query.orderByProcessDefinitionVersion().desc();
        query.listPage(0, 10);
        List<ProcessDefinition> ProcessDefinitionList = query.list();
        for (ProcessDefinition pd : ProcessDefinitionList) {
            log.info("流程定义id:{}", pd.getId());
        }
    }

    /**
     * 启动流程实例
     */
    @Test
    public void test2() {
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.deploymentId("37501");
        query.orderByProcessDefinitionVersion().desc();
        List<ProcessDefinition> ProcessDefinitionList = query.list();
        for (ProcessDefinition pd : ProcessDefinitionList) {
            Map<String, Object> variables = new HashMap<>(16);
            variables.put("key", "888888");
            ProcessInstance processInstance = runtimeService.startProcessInstanceById(pd.getId(), "123458", variables);
            log.info("流程实例id:{}", processInstance.getId());
        }
    }

    /**
     * 任务分配
     */
    @Test
    public void test3() {
        taskService.setAssignee("40006", "小俊");
    }

    /**
     * 拉取个人任务
     */
    @Test
    public void test4() {
        TaskQuery taskQuery = taskService.createTaskQuery();
        taskQuery.taskAssignee("小俊");
        List<Task> taskList = taskQuery.list();
        for (Task task : taskList) {
            log.info("taskId:{},taskName:{}", task.getId(), task.getName());
        }
    }

    /**
     * 办理任务
     */
    @Test
    public void test5() {
        taskService.complete("60001");
        log.info("办理成功");
    }

    /**
     * 删除流程
     */
    @Test
    public void test6() {
        List<String> list = Arrays.asList("27501", "32501", "37501", "62501");
        for (String str : list) {
            repositoryService.deleteDeployment(str, true);
        }
        log.info("删除成功");
    }

    /**
     * 根据任务获取业务key
     */
    @Test
    public void test7() {
        //1 使用任务ID，查询对象task
        Task task = taskService.createTaskQuery().taskId("30005").singleResult();
        //2 使用任务，获取实例ID
        String processInstanceId = task.getProcessDefinitionId();
        //3 使用流程实例查询
        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processDefinitionId(processInstanceId).singleResult();
        //4 使用流程实例对象获取businessKey
        String businessKey = pi.getBusinessKey();
        log.info("businessKey:{}", businessKey);
    }

    /**
     * 查询一次部署对应的流程定义文件和对应的输入流
     */
    @Test
    public void test8() {
        String deploymentId = "27501";
        List<String> list = repositoryService.getDeploymentResourceNames(deploymentId);
        for (String name : list) {
            log.info("获取的name:{}", name);
            InputStream inputStream = repositoryService.getResourceAsStream(deploymentId, name);
            FileUtil.writeFromStream(inputStream, new File("d:\\" + name));
        }
    }

    /**
     * 获得png文件的输入流
     */
    @Test
    public void test9() {
        String processDefinitionId = "transfer:1:27504";
        InputStream pngInputStream = repositoryService.getProcessDiagram(processDefinitionId);
        FileUtil.writeFromStream(pngInputStream, new File("d:\\" + "my.png"));
    }

    /**
     * 结束流程实例
     */
    @Test
    public void test10() {
        runtimeService.deleteProcessInstance("30002", "操作失误");
    }

    /**
     * 获取流程变量
     */
    @Test
    public void test11() {
        Object object = runtimeService.getVariable("40001", "key");
        log.info("变量key:{}", object);
    }

    /**
     * 退回任务
     */
    @Test
    public void test12() {
        String taskId = "40006";
        taskService.setAssignee(taskId, null);
    }

}
