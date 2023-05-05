package com.rainy.web.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.service_house.entity.House;
import com.rainy.service_house.entity.HouseQuery;
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


    @PostMapping("/list")
    public R houseList(HouseQuery houseQuery,
                       @Param(value = "pageSize") Integer pageSize,
                       @Param(value = "pageNum") Integer pageNum) {
        R msg = R.ok();
        houseQuery.setPageSize(pageSize).setPageNum(pageNum);
        houseQuery.setPageSize(pageSize).setPageNum(pageNum);
        houseQuery.setDeleted(false);
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", houseService.houseList(houseQuery));
        msg.setData(dataMap);
        return msg;
    }


    @GetMapping("/list/{id}")
    public R houseList(@PathVariable(value = "id") String id) {
        R msg = R.ok();
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("data", houseService.houseList(Long.valueOf(id)));
        msg.setData(dataMap);
        return msg;
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

    @GetMapping("/ownCollectList/{userId}")
    public R ownCollectList(@PathVariable(value = "userId") String userId,
                            @Param(value = "pageSize") Integer pageSize,
                            @Param(value = "pageNum") Integer pageNum) {
        return ownList(userId, pageSize, pageNum, false);
    }

    @GetMapping("/ownSellList/{userId}")
    public R ownSellList(@PathVariable(value = "userId") String userId,
                         @Param(value = "pageSize") Integer pageSize,
                         @Param(value = "pageNum") Integer pageNum) {
        return ownList(userId, pageSize, pageNum, true);
    }


}

