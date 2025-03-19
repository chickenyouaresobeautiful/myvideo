package com.daiweij.myvedio.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @TableName users
 */
@TableName(value = "users")
@Data
public class UsersEntity implements Serializable {
    /**
     * 用户ID (主键)
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 邮箱地址
     */
    private String email;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 注册时间
     */
    private Date createdAt;

    /**
     * 更新时间
     */
    private Date updatedAt;

    /**
     * 头像URL
     */
    private String avatar;

    /**
     * 用户角色（普通用户、管理员等）
     */
    private Object role;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}