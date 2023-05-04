package com.rainy.service_user.service;

import com.rainy.service_user.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author rainy
 * @since 2023-05-02
 */
public interface UserService extends IService<User> {
    void addAccount(User account, String confirmPassword);

    void enable(String key);

    String auth(String input, String password);

    User showProfile(String id);

    User updateProfile(String id, User account, MultipartFile file);
}
