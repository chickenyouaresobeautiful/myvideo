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
 * @TableName play_history
 */
@TableName(value = "play_history")
@Data
public class PlayHistoryEntity implements Serializable {
    /**
     * 播放记录ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户ID（外键）
     */
    private Long userId;

    /**
     * 视频ID（外键）
     */
    private Long videoId;

    /**
     * 最后观看时间
     */
    private Date lastWatchedAt;

    /**
     * 播放进度（以秒为单位）
     */
    private Integer progress;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}