package com.xiaojun;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaojun
 * @date 2020/6/21 0:20
 */
public enum ApprovalTypeEnum {

    T("1"),
    N("2");

    @Getter
    @Setter
    private String value;

    ApprovalTypeEnum(String value) {
        this.value = value;
    }
}
