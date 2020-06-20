package com.xiaojun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xiaojun
 * @date 2020/6/21 0:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalDto {

    private String approvalType;

    private String status;
}
