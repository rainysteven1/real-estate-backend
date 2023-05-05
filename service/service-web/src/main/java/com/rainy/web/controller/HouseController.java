package com.rainy.web.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.service_house.entity.House;
import com.rainy.service_house.entity.HouseUserQuery;
import com.rainy.service_house.service.HouseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @PostMapping("/sell")
    public R houseAdd(House house,
                      @Param(value = "houseImages") List<MultipartFile> houseImages,
                      @Param(value = "floorPlainImages") List<MultipartFile> floorPlainImages,
                      @Param(value = "userId") String userId) {
        houseService.houseAdd(house, houseImages, floorPlainImages, userId);
        return R.created();
    }

    private R ownList(String userId, Integer pageSize, Integer pageNum, Boolean type) {
        R msg = R.ok();
        HouseUserQuery query = HouseUserQuery.builder()
                .type(type)
                .deleted(false)
                .userId(userId)
                .build();
        query.setPageSize(pageSize).setPageNum(pageNum);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", houseService.ownList(query));
        msg.setData(dataMap);
        return msg;
    }

    @GetMapping("/collectList/{userId}")
    public R collectList(@PathVariable(value = "userId") String userId,
                         @Param(value = "pageSize") Integer pageSize,
                         @Param(value = "pageNum") Integer pageNum) {
        return ownList(userId, pageSize, pageNum, false);
    }

    @GetMapping("/sellList/{userId}")
    public R sellList(@PathVariable(value = "userId") String userId,
                      @Param(value = "pageSize") Integer pageSize,
                      @Param(value = "pageNum") Integer pageNum) {
        return ownList(userId, pageSize, pageNum, true);
    }


}

