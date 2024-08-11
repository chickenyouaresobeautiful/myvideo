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
 * @TableName comments
 */
@TableName(value = "comments")
@Data
public class CommentsEntity implements Serializable {
    /**
     * 评论ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 视频ID（外键）
     */
    private Long videoId;

    /**
     * 用户ID（外键）
     */
    private Long userId;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}