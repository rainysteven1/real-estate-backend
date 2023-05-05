package com.rainy.commonutils.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class PageQuery {
    @ApiModelProperty("页数据条数")
    private Integer pageSize;

    @ApiModelProperty("当前为第几页")
    private Integer pageNum;
}
