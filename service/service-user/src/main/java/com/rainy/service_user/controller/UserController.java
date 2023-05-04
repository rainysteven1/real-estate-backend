package com.rainy.service_user.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.service_user.entity.User;
import com.rainy.service_user.exception.CustomException;
import com.rainy.service_user.service.UserService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.rainy.commonutils.constants.ResultCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rainy
 * @since 2023-05-02
 */
@Api(value = "用户管理")
@RestController
@RequestMapping("/api/service_user/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping(value = "/register")
    public R accountRegister(User account, String confirmPassword) {
        userService.addAccount(account, confirmPassword);
        return R.created();
    }

    @GetMapping("/verify")
    public R verify(@Param(value = "key") String key) {
        userService.enable(key);
        return R.ok();
    }


    @GetMapping("/profile/{id}")
    public R getProfile(@PathVariable(value = "id") String id) {
        R msg = R.ok();
        msg.setData(userService.showProfile(id).toMap());
        return msg;
    }

    @PutMapping("/profile/{id}")
    public R updateProfile(@PathVariable(value = "id") String id, User account
            , @Param(value = "file") MultipartFile file) {
        R msg = R.ok();
        msg.setData(userService.updateProfile(id, account, file).toMap());
        return msg;
    }
}
