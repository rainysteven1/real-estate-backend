package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rainy.commonutils.constants.ResultCode;
import com.rainy.commonutils.entity.ResultMsg;
import com.rainy.commonutils.utils.HashUtil;
import com.rainy.service_user.entity.User;
import com.rainy.service_user.exception.CustomException;
import com.rainy.service_user.mapper.UserMapper;
import com.rainy.service_user.service.MailService;
import com.rainy.service_user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainy.servicebase.config.MinioProperties;
import com.rainy.servicebase.service.MinioService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2023-05-02
 */
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
        mailService.registerNotify(account.getEmail());
    }

    public void enable(String key) {
        mailService.enable(key);
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
