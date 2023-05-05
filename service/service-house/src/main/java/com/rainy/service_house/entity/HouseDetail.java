package com.rainy.service_house.entity;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "House对象带图像", description = "")
public class HouseDetail extends House {

    private List<HouseFiles> images;

}
