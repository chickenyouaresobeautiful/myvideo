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
 * @TableName videos
 */
@TableName(value = "videos")
@Data
public class VideosEntity implements Serializable {
    /**
     * 视频ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 上传视频的用户ID（外键）
     */
    private Long userId;

    /**
     * 视频标题
     */
    private String title;

    /**
     * 视频描述
     */
    private String description;

    /**
     * 视频文件的URL
     */
    private String url;

    /**
     * 视频缩略图URL
     */
    private String thumbnailUrl;

    /**
     * 视频时长（秒）
     */
    private Integer duration;

    /**
     * 观看次数
     */
    private Long views;

    /**
     * 上传时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 隐私设置（公开、私密等）
     */
    private Object privacy;

    /**
     * 视频分类ID（外键）
     */
    private Long categoryId;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}