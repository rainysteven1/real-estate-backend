package com.rainy.service_user.controller;

import com.rainy.commonutils.entity.R;
import com.rainy.commonutils.util.UUIDUtil;
import com.rainy.service_user.entity.Userinfo;
import com.rainy.service_user.exception.CustomException;
import com.rainy.service_user.service.UserinfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rainy.commonutils.util.ResultCode;

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

    /**
     * 注册
     */
    @PostMapping(value = "/register")
    public R register(Userinfo userinfo) {
        userinfo.setStatus(false);
        String code = UUIDUtil.getUUID();
        userinfo.setCode(code);
        String msg = userinfoService.register(userinfo);
        if (!msg.equals("created")) {
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, msg);
        }
        return R.created();
    }

    /**
     * 校验邮箱中的code激活账户
     * 首先根据激活码code查询用户，之后再把状态修改为"1"
     */
    @GetMapping(value = "/checkCode")
    public String checkCode(String code) {
        return "";
    }

}
