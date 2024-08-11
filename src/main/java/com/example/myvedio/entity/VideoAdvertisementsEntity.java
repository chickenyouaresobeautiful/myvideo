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
 * @TableName video_advertisements
 */
@TableName(value = "video_advertisements")
@Data
public class VideoAdvertisementsEntity implements Serializable {
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
     * 广告ID（外键）
     */
    private Long advertisementId;

    /**
     * 创建时间
     */
    private Date createdAt;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}