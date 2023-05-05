package com.rainy.web.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.service_house.entity.House;
import com.rainy.service_house.service.HouseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rainy
 * @since 2023-05-04
 */
@RestController
@RequestMapping("/api/service_house/house")
public class HouseController {

    @Autowired
    private HouseService houseService;

//    @GetMapping("/recommendList")
//    public R houseList(@Param(value = "pageSize") Integer pageSize,
//                       @Param(value = "pageNum") Integer pageNum) {
//        System.out.println(pageSize);
//        System.out.println(pageNum);
//        return R.ok();
//    }
//
//    @GetMapping("/detail")
//    public R houseDetail(Long id) {
//        return R.ok();
//    }

    @PostMapping("/add")
    public R houseAdd(House house,
                      @Param(value = "houseImages") List<MultipartFile> houseImages,
                      @Param(value = "floorPlainImages") List<MultipartFile> floorPlainImages,
                      @Param(value = "userId") String userID) {
        houseService.houseAdd(house, houseImages, floorPlainImages, userID);
        return R.created();
    }


}

