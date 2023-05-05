package com.rainy.service_house.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainy.service_house.entity.House;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainy.service_house.entity.HouseListVO;
import com.rainy.service_house.entity.HouseQuery;
import com.rainy.service_house.entity.HouseUserQuery;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rainy
 * @since 2023-05-04
 */
public interface HouseService extends IService<House> {

    void houseAdd(House house, List<MultipartFile> houseImages,
                  List<MultipartFile> floorPlainImages, String userID);

    IPage<HouseListVO> houseList(HouseQuery houseQuery);

    House houseList(Long id);

    IPage<HouseListVO> ownList(HouseUserQuery houseUserQuery);
}
