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
 * @TableName reports
 */
@TableName(value = "reports")
@Data
public class ReportsEntity implements Serializable {
    /**
     * 举报ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 举报者用户ID（外键）
     */
    private Long reportedBy;

    /**
     * 视频ID（可选，外键）
     */
    private Long videoId;

    /**
     * 评论ID（可选，外键）
     */
    private Long commentId;

    /**
     * 举报原因
     */
    private String reason;

    /**
     * 举报时间
     */
    private Date createdAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}