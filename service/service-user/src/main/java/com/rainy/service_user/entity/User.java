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
import java.util.HashMap;
import java.util.Map;

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
@TableName("tb_user")
@ApiModel(value = "User对象", description = "")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("用户名")
    @TableField("name")
    private String name;

    @ApiModelProperty("md5加密后的密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("个人介绍")
    @TableField("about")
    private String about;

    @ApiModelProperty("用户邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("用户头像")
    @TableField("avatar")
    private String avatar;

    @ApiModelProperty("创建时间")
    @TableField(value = "created", fill = FieldFill.INSERT)
    private Date created;

    @ApiModelProperty("1:普通用户，2:房产经纪人")
    @TableField("type")
    private Boolean type;

    @ApiModelProperty("所属经纪机构")
    @TableField("agency_id")
    private Integer agencyId;

    @ApiModelProperty("1已激活,0未激活")
    @TableField("enabled")
    private Boolean enabled;

    @ApiModelProperty("逻辑删除1已删除,0未删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


    public Map<String, Object> toMap() {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("username", name);
        resultMap.put("about", about);
        resultMap.put("email", email);
        resultMap.put("avatar", avatar);
        return resultMap;
    }

}
