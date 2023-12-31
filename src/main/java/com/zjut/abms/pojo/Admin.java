package com.zjut.abms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName admin
 */
@TableName(value = "admin")
@Data
public class Admin implements Serializable {
    @TableId(value = "admin_id", type = IdType.AUTO)
    private Integer id;
    @TableField("admin_username")
    private String username;
    @TableField("admin_password")
    private String password;

    private static final long serialVersionUID = 1L;
}