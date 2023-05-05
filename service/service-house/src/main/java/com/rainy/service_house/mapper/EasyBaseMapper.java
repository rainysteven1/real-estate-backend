package com.rainy.service_house.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

public interface EasyBaseMapper<T> extends BaseMapper<T> {
    Integer insertBatchSomeColumn(List<T> entityList);
}
