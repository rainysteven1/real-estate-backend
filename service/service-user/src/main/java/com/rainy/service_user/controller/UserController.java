package com.rainy.service_user.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.service_user.entity.User;
import com.rainy.service_user.exception.CustomException;
import com.rainy.service_user.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rainy.commonutils.constants.ResultCode;

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
}
