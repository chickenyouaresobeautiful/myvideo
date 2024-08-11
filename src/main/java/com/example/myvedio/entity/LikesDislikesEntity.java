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
 * @TableName likes_dislikes
 */
@TableName(value = "likes_dislikes")
@Data
public class LikesDislikesEntity implements Serializable {
    /**
     * 主键
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
     * 类型（1代表点赞，-1代表踩）
     */
    private Integer type;

    /**
     * 创建时间
     */
    private Date createdAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}