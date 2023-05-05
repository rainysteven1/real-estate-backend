package com.rainy.service_house.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class HouseListVO {
    @ApiModelProperty("房产ID")
    private Long id;

    @ApiModelProperty("标题")
    private String title;

    @ApiModelProperty("房产照片URL")
    private String preview;

    @ApiModelProperty("创建时间")
    private LocalDateTime created;
}
