package com.rainy.service_user.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author rainy
 * @since 2023-05-02
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_userinfo")
@ApiModel(value = "Userinfo对象", description = "")
public class Userinfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户名")
    @TableField("username")
    private String username;

    @ApiModelProperty("密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("用户邮箱")
    @TableField("mail")
    private String mail;

    @ApiModelProperty("用户头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("创建时间")
    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

    @ApiModelProperty("修改时间")
    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    private Date modified;

    @ApiModelProperty("是否激活1已激活,0未激活")
    @TableField("status")
    private Boolean status;

    @ApiModelProperty("校验码 用户传来的code跟我们发送的code一致，则更改状态为“1”来激活用户")
    @TableField("code")
    private String code;

    @ApiModelProperty("逻辑删除1已删除,0未删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
