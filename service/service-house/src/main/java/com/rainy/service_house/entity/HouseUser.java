package com.rainy.service_house.entity;

import com.baomidou.mybatisplus.annotation.*;

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
@TableName("tb_house_user")
@ApiModel(value = "HouseUser对象", description = "")
public class HouseUser implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("房屋id")
    @TableField("house_id")
    private Long houseId;

    @ApiModelProperty("用户id")
    @TableField("user_id")
    private String userId;

    @ApiModelProperty("true-售卖，false-收藏")
    @TableField("type")
    private Boolean type;

    @ApiModelProperty("创建时间")
    @TableField(value = "created", fill = FieldFill.INSERT)
    private LocalDateTime created;

    @ApiModelProperty("修改时间")
    @TableField(value = "modified", fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime modified;

    @ApiModelProperty("逻辑删除1已删除,0未删除")
    @TableField("deleted")
    @TableLogic
    private Boolean deleted;


}
