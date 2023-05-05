package com.rainy.service_house.service;

import com.rainy.service_house.entity.House;
import com.baomidou.mybatisplus.extension.service.IService;
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
}
