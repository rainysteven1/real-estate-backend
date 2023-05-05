package com.rainy.service_house.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("tb_house_files")
@ApiModel(value = "HouseFiles对象", description = "")
public class HouseFiles implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("房屋id")
    @TableField("house_id")
    private Long houseId;

    @ApiModelProperty("上传图片存储URL")
    @TableField("imageURL")
    private String imageURL;

    @ApiModelProperty("true-房屋图片，false-户型图片")
    @TableField("type")
    private Boolean type;

    @ApiModelProperty("创建时间")
    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @ApiModelProperty("创建时间")
    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modified;

    @ApiModelProperty("逻辑删除1已删除,0未删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
