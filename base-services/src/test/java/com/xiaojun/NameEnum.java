package com.xiaojun;

import lombok.Getter;
import lombok.Setter;

/**
 * @author xiaojun
 * @date 2020/6/20 19:59
 */
public enum NameEnum {

    T("luo", ApprovalTypeEnum.T.getValue(), StatusEnum.T.getValue()),
    N("xiao", ApprovalTypeEnum.T.getValue(), StatusEnum.N.getValue()),
    D("jun", ApprovalTypeEnum.N.getValue(), null);

    @Getter
    @Setter
    private String value;

    @Getter
    @Setter
    private String approvalType;

    @Getter
    @Setter
    private String status;

    NameEnum(String value, String approvalType, String status) {
        this.value = value;
        this.approvalType = approvalType;
        this.status = status;
    }

    public static ApprovalDto getStatusByValue(String value) {
        for (NameEnum r : NameEnum.values()) {
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
