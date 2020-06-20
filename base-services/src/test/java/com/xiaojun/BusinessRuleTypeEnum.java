package com.xiaojun;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaojun
 * @date 2020/6/20 23:54
 */
public enum BusinessRuleTypeEnum {

    CUSTOMER("1"),
    CONTROLLER("2"),
    BUYER("3");

    @Getter
    @Setter
    private String code;

    BusinessRuleTypeEnum(String code) {
        this.code = code;
    }
}
