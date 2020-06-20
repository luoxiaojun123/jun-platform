package com.xiaojun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaojun
 * @date 2020/6/21 0:02
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RuleDto {

    /**
     * 审批值
     */
    private String value;

    /**
     * 申请id
     */
    private String applyId;
    /**
     * 业务类型
     */
    private String businessRuleType;
    /**
     * 业务id
     */
    private String businessId;

    /**
     * 规则字段
     */
    private String code;

    /**
     * 规则字段描述
     */
    private String desc;

    /**
     * 审批类型
     */
    private String approvalType;

    /**
     * 审核结果
     */
    private String status;

    /**
     * 审批意见
     */
    private String approvalDesc;
}
