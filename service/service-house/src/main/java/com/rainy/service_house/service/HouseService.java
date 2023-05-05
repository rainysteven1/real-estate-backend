package com.rainy.service_house.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.rainy.service_house.entity.House;
import com.baomidou.mybatisplus.extension.service.IService;
import com.rainy.service_house.entity.HouseOwnListVO;
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

    IPage<HouseOwnListVO> ownList(HouseUserQuery houseUserQuery);
}
