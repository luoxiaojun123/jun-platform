package com.xiaojun;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaojun
 * @date 2020/6/20 18:45
 */
public enum SexEnum {

    T("男", ApprovalTypeEnum.T.getValue(), StatusEnum.T.getValue()),
    N("女", ApprovalTypeEnum.T.getValue(), StatusEnum.N.getValue()),
    D("未知", ApprovalTypeEnum.N.getValue(), null);

    @Getter
    @Setter
    private String value;
    @Getter
    @Setter
    private String approvalType;
    @Getter
    @Setter
    private String status;

    SexEnum(String value, String approvalType, String status) {
        this.value = value;
        this.approvalType = approvalType;
        this.status = status;
    }

    public static ApprovalDto getStatusByValue(String value) {
        for (SexEnum r : SexEnum.values()) {
            if (r.value.equals(value)) {
                return ApprovalDto.builder()
                        .approvalType(r.getApprovalType())
                        .status(r.getStatus())
                        .build();
            }
        }
        return ApprovalDto.builder()
                .approvalType(ApprovalTypeEnum.N.getValue())
                .build();
    }
}
