package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.RemovalListener;
import com.rainy.commonutils.constants.ResultCode;
import com.rainy.commonutils.utils.HashUtil;
import com.rainy.commonutils.utils.StringUtil;
import com.rainy.service_user.entity.User;
import com.rainy.commonutils.exception.CustomException;
import com.rainy.service_user.mapper.UserMapper;
import com.rainy.service_base.service.MailService;
import com.rainy.service_user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainy.service_base.service.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2023-05-02
 */
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    // 默认头像地址
    @Value("${minio.default-avatar}")
    private String defaultAvatar;

    @Value("${minio.folder}")
    private String folder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MailService mailService;

    @Autowired
    private MinioService minioService;

    private final Cache<String, String> registerCache =
            Caffeine.newBuilder()
                    .maximumSize(100)
                    .expireAfterAccess(1, TimeUnit.MINUTES)
                    .removalListener(new RemovalListener<String, String>() {
                        @Override
                        public void onRemoval(@Nullable String key, @Nullable String value, @NonNull RemovalCause removalCause) {
                            log.info("移除key[" + key
                                    + "],value[" + value
                                    + "],移除原因[" + removalCause + "]");
                            QueryWrapper<User> wrapper = new QueryWrapper<>();
                            wrapper.eq("email", value);
                            wrapper.eq("deleted", false);
                            List<User> targetUser = userMapper.selectList(wrapper);
                            if (!targetUser.isEmpty() && !targetUser.get(0).getEnabled()) {
                                userMapper.delete(wrapper);//在删除前首先判断用户是否已经被激活，对于未激活的用户进行移除操作
                            }
                        }

                    })
                    .build();

    private void registerValidate(User account, String confirmPassword) {
        if (StringUtils.isBlank(account.getEmail()) || StringUtils.isBlank(account.getPassword()) ||
                StringUtils.isBlank(confirmPassword)) {
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, "输入内容不能为空");
        }
        if (!account.getPassword().equals(confirmPassword)) {
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, "两次输入的密码不同");
        }
        if (account.getPassword().length() < 8 || account.getPassword().length() >= 16) {
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, "密码位数应为8-16位");
        }
    }

    /**
     * 1.插入数据库，非激活;密码加盐md5;保存头像文件到本地 2.生成key，绑定email 3.发送邮件给用户
     *
     * @param account         用户
     * @param confirmPassword 确认密码
     */
    @Override
    public void addAccount(User account, String confirmPassword) {
        registerValidate(account, confirmPassword);
        account.setPassword(HashUtil.encryPassword(account.getPassword()));
        account.setName(account.getEmail()).setAvatar(defaultAvatar);

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", account.getEmail());
        wrapper.eq("deleted", false);
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() != 0) {
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, "邮箱已存在");
        }
        userMapper.insert(account);
        registerNotify(account.getEmail());
    }


    @Override
    public String auth(String input, String password) {
        if (StringUtils.isBlank(input) || StringUtils.isBlank(password)) {
            throw new CustomException(ResultCode.ERROR_USER_LOGIN, "输入的用户名或密码不能为空");
        }
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("password", HashUtil.encryPassword(password));
        wrapper.eq(StringUtil.isEmail(input) ? "email" : "name", input);
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() == 0) {
            throw new CustomException(ResultCode.ERROR_USER_LOGIN, "输入的用户名或密码错误");
        }
        return userList.get(0).getId();
    }

    @Async
    public void registerNotify(String email) {
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey, email);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
        String subject = "房产平台--用户注册";
        String emailTemplate = "registerTemplate";
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("email", email);
        dataMap.put("createTime", sdf.format(new Date()));
        dataMap.put("key", randomKey);
        mailService.sendHtmlMail(email, subject, emailTemplate, dataMap);
    }

    @Override
    public void enable(String key) {
        String email = registerCache.getIfPresent(key);
        if (StringUtils.isBlank(email) || registerCache.getIfPresent(key) == null) {
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, "验证码无效");
        }
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("enabled", true).eq("email", email).eq("deleted", false);
        userMapper.update(null, wrapper);
        registerCache.invalidate(key);
    }

    @Override
    public User showProfile(String id) {
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("deleted", false);
        List<User> userList = userMapper.selectList(wrapper);
        return userList.get(0);
    }


    @Override
    public User updateProfile(String id, User account, MultipartFile file) {
        account.setId(id);
        try {
            if (file != null) {
                InputStream inputStream = file.getInputStream();
                String contentType = file.getContentType();
                account.setAvatar(minioService.uploadFile(inputStream, account.getId(), contentType, folder));
            }
        } catch (IOException e) {
            throw new CustomException(ResultCode.SERVER_ERROR, "上传文件失败");
        }
        userMapper.updateById(account);
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("id", id);
        wrapper.eq("deleted", false);
        List<User> userList = userMapper.selectList(wrapper);
        account.setAvatar(userList.get(0).getAvatar());
        return account;
    }
}
