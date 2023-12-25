package com.zjut.abms.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName passenger
 */
@TableName(value ="passenger")
@Data
public class Passenger implements Serializable {
    @TableId(value = "pass_id",type = IdType.AUTO)
    private Integer id;
    @TableField(value = "pass_name")
    private String username;
    @TableField(value = "pass_sex")
    private String sex;
    @TableField(value = "pass_phone")
    private String phone;
    @TableField(value = "pass_password")
    private String password;
    private static final long serialVersionUID = 1L;
}