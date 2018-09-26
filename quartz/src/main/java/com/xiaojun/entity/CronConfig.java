package com.xiaojun.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author long.luo
 * @date 2018/9/20 13:42
 */
@Data
@TableName("cron_config")
public class CronConfig {

    /**
     * 主键id
     */
    @TableId
    private Integer id;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务执行表达式
     */
    private String cron;
    /**
     * 任务状态
     */
    private Integer status;
    /**
     * 备注
     */
    private String remark;
}
