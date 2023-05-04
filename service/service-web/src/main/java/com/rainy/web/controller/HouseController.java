package com.rainy.web.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.service_house.entity.House;
import com.rainy.service_house.service.HouseService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


    @GetMapping("/recommendList")
    public R houseList(@Param(value = "pageSize") Integer pageSize,
                       @Param(value = "pageNum") Integer pageNum) {
        System.out.println(pageSize);
        System.out.println(pageNum);
        return R.ok();
    }

    @GetMapping("/detail")
    public R houseDetail(Long id) {
        return R.ok();
    }
    

}

