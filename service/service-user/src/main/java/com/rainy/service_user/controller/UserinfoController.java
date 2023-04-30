package com.rainy.service_user.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.commonutils.util.UUIDUtil;
import com.rainy.service_user.entity.Userinfo;
import com.rainy.service_user.service.UserinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author rainy
 * @since 2023-04-29
 */
@Api(tags = "用户信息管理")
@RestController
@RequestMapping("/api/service_user/userinfo")
public class UserinfoController {
    @Autowired
    private UserinfoService userinfoService;

    @PostMapping(value = "/register")
    public R register(Userinfo userinfo) {
        userinfo.setStatus((byte) 0);
        String code = UUIDUtil.getUUID();
        userinfo.setCode(code);
        userinfoService.register(userinfo);
        return R.success();
    }
}
