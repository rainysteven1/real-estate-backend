package com.rainy.service_user.service;

import com.rainy.service_user.entity.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rainy
 * @since 2023-04-29
 */
public interface UserinfoService extends IService<Userinfo> {

    String register(Userinfo userinfo);

    /**
     * 根据激活码code查询用户，之后再进行修改状态
     */
    Userinfo checkCode(String code);

    /**
     * 激活账户，修改用户状态为“1”
     */
    void updateUserStatus(Userinfo userinfo);

    /**
     * 登录
     */
    Userinfo login(Userinfo userinfo);
}
