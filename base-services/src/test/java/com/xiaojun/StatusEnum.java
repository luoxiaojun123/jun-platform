package com.xiaojun;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaojun
 * @date 2020/6/20 20:10
 */
public enum StatusEnum {

    T("1"),
    N("2"),
    D("3");

    @Getter
    @Setter
    private String value;

    StatusEnum(String value) {
        this.value = value;
    }
}
