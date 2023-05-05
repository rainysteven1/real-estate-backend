package com.rainy.service_house.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainy.service_house.entity.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainy.service_house.entity.HouseOwnListVO;
import com.rainy.service_house.entity.HouseUserQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author rainy
 * @since 2023-05-04
 */
public interface HouseMapper extends BaseMapper<House> {


    IPage<HouseOwnListVO> listByPage(Page<House> page, @Param("huQuery") HouseUserQuery houseUserQuery);
}
