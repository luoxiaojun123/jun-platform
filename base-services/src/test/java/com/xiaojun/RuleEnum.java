package com.xiaojun;

import com.xiaojun.exception.ServerErrorException;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaojun
 * @date 2020/6/20 16:56
 */
public enum RuleEnum {

    RULE_SEX("sex", "性别规则"),
    RULE_NAME("name", "姓名规则");

    @Getter
    @Setter
    private String code;
    @Getter
    @Setter
    private String desc;

    RuleEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }


    public static RuleEnum getRuleEnumByCode(String code) {
        for (RuleEnum r : RuleEnum.values()) {
            if (r.getCode().equals(code)) {
                return r;
            }
        }
        return null;
    }

    public static ApprovalDto getStatusByRule(RuleEnum ruleEnum, String value) {
        switch (ruleEnum) {
            case RULE_SEX:
                return SexEnum.getStatusByValue(value);
            case RULE_NAME:
                return NameEnum.getStatusByValue(value);
        }
        throw new ServerErrorException("系统审批异常");
    }
}
