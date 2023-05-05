package com.rainy.service_house.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDateTime;

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
 * @since 2023-05-04
 */
@Getter
@Setter
@Accessors(chain = true)
@TableName("tb_house")
@ApiModel(value = "House对象", description = "")
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("房产ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("标题")
    @TableField("title")
    private String title;

    @ApiModelProperty("房产照片URL")
    @TableField("preview")
    private String preview;

    @ApiModelProperty("房产百科URL")
    @TableField("detail")
    private String detail;

    @ApiModelProperty("所属小区名")
    @TableField("community")
    private String community;

    @ApiModelProperty("所属街道名")
    @TableField("street")
    private String street;

    @ApiModelProperty("所属区名")
    @TableField("district")
    private String district;

    @ApiModelProperty("总价")
    @TableField("total_price")
    private String totalPrice;

    @ApiModelProperty("每平米价格")
    @TableField("unit_price")
    private String unitPrice;

    @ApiModelProperty("房型")
    @TableField("room_number")
    private String roomNumber;

    @ApiModelProperty("面积")
    @TableField("area")
    private String area;

    @ApiModelProperty("朝向")
    @TableField("direction")
    private String direction;

    @ApiModelProperty("装修")
    @TableField("renovation")
    private String renovation;

    @ApiModelProperty("楼层")
    @TableField("floor")
    private String floor;

    @ApiModelProperty("建造日期")
    @TableField("year")
    private String year;

    @ApiModelProperty("房产类型")
    @TableField("house_type")
    private String houseType;

    @ApiModelProperty("创建时间")
    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @ApiModelProperty("是否上架")
    @TableField("state")
    private Boolean state;

    @ApiModelProperty("逻辑删除1已删除,0未删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
