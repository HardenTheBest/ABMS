package com.zjut.abms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * @TableName agency
 */
@TableName(value = "agency")
@Data
public class Agency implements Serializable {
    @TableId(value = "agency_id", type = IdType.AUTO)
    private Integer id;
    @TableField(value = "agency_username")
    private String username;
    @TableField(value = "agency_password")
    private String password;

    private static final long serialVersionUID = 1L;
}