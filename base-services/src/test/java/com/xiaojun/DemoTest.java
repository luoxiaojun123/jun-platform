package com.xiaojun;

import com.xiaojun.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Field;
import java.util.*;

/**
 * @author xiaojun
 * @date 2020/6/20 16:50
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class DemoTest {


    @Test
    public void test() {

        Person person = new Person();
        person.setName("小");
        person.setSex("男");

        Map<String, RuleDto> map = getReflexRecord(person, BusinessRuleTypeEnum.CUSTOMER, "YW001", "SH001");
        map.forEach((k, v) -> {
            RuleEnum ruleEnum = RuleEnum.getRuleEnumByCode(k);
            if (Objects.nonNull(ruleEnum)) {
                System.out.println("审批字段" + k);
                ApprovalDto approvalDto = RuleEnum.getStatusByRule(ruleEnum, v.getValue());
                System.out.println("审批状态：" + approvalDto.toString());

                RuleDto rule = RuleDto.builder()
                        .applyId(v.getApplyId())
                        .businessRuleType(v.getBusinessRuleType())
                        .businessId(v.getBusinessId())
                        .code(ruleEnum.getCode())
                        .desc(ruleEnum.getDesc())
                        .approvalType(approvalDto.getApprovalType())
                        .status(approvalDto.getStatus())
                        .build();
                System.out.println(rule.toString());
            }
        });

    }

    public static Map<String, RuleDto> getReflexRecord(Person person, BusinessRuleTypeEnum businessRuleTypeEnum, String businessId, String applyId) {
        Map<String, RuleDto> map = new HashMap<>();

        Class<? extends Person> aClass = person.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            String fieldName = declaredField.getName();
            Object fieldValue = null;
            try {
                fieldValue = declaredField.get(person);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            String value = String.valueOf(fieldValue);

            RuleDto ruleDto = RuleDto.builder()
                    .value(value)
                    .businessRuleType(businessRuleTypeEnum.getCode())
                    .businessId(businessId)
                    .applyId(applyId)
                    .code(fieldName)
                    .build();
            map.put(fieldName, ruleDto);
        }

        return map;
    }
}
