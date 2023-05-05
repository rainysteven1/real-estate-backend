package com.rainy.service_house.entity;

import com.rainy.commonutils.entity.PageQuery;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
@Builder
public class HouseQuery extends PageQuery {
    @ApiModelProperty("所属小区名")
    private String community;

    @ApiModelProperty("所属街道名")
    private String street;

    @ApiModelProperty("所属区名")
    private String district;

    @ApiModelProperty("总价")
    private String totalPrice;

    @ApiModelProperty("房型")
    private String roomNumber;

    @ApiModelProperty("面积")
    private String area;

    @ApiModelProperty("朝向")
    private String direction;

    @ApiModelProperty("装修")
    private String renovation;

    @ApiModelProperty("楼层")
    private String floor;

    @ApiModelProperty("建造日期")
    private String year;

    @ApiModelProperty("房产类型")
    private String houseType;

    @ApiModelProperty("是否上架")
    private Boolean state;

    @ApiModelProperty("逻辑删除1已删除,0未删除")
    private Boolean deleted;
}
