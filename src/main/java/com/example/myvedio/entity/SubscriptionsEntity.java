package com.example.myvedio.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName subscriptions
 */
@TableName(value = "subscriptions")
@Data
public class SubscriptionsEntity implements Serializable {
    /**
     * 订阅ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 订阅者的用户ID（外键）
     */
    private Long subscriberId;

    /**
     * 被订阅者的用户ID（外键）
     */
    private Long subscribedToId;

    /**
     * 创建时间
     */
    private Date createdAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}