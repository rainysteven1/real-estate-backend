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
public class HouseUserQuery extends PageQuery {

    @ApiModelProperty("true-售卖，false-收藏")
    private Boolean type;

    @ApiModelProperty("用户ID")
    private String userId;

    @ApiModelProperty("true-已删除,false-未删除")
    private Boolean deleted;
}
