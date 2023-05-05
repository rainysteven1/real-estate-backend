package com.rainy.service_house.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.rainy.service_house.entity.House;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.rainy.service_house.entity.HouseListVO;
import com.rainy.service_house.entity.HouseQuery;
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

    House listById(@Param("houseId") Long id);

    IPage<HouseListVO> listByHouseQuery(Page<HouseListVO> page, @Param("query") HouseQuery houseQuery);

    IPage<HouseListVO> listByHouseUserQuery(Page<HouseListVO> page, @Param("query") HouseUserQuery houseUserQuery);

}
