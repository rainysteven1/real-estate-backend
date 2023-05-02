package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainy.commonutils.entity.ResultMsg;
import com.rainy.commonutils.utils.HashUtil;
import com.rainy.service_user.entity.User;
import com.rainy.service_user.mapper.UserMapper;
import com.rainy.service_user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Autowired
    private UserMapper userMapper;

    private ResultMsg validate(User account, String confirmPassword) {
        if (StringUtils.isBlank(account.getEmail()) && StringUtils.isBlank(account.getPassword()) &&
                StringUtils.isBlank(confirmPassword)) {
            return ResultMsg.errorMsg("输入内容不能为空");
        }
        if (!account.getPassword().equals(confirmPassword)) {
            return ResultMsg.errorMsg("两次输入的密码不同");
        }
        if (account.getPassword().length() < 8 || account.getPassword().length() >= 16) {
            return ResultMsg.errorMsg("密码位数应为8-16位");
        }
        return ResultMsg.successMsg("");
    }

    @Override
    public String addAccount(User account, String confirmPassword) {
        ResultMsg msg = validate(account, confirmPassword);
        if (!msg.isSuccess()) {
            return msg.getErrorMsg();
        }
        account.setPassword(HashUtil.encryPassword(account.getPassword()));
        account.setName(account.getEmail()).setAvatar("");


        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("email", account.getEmail());
        List<User> userList = userMapper.selectList(wrapper);
        if (userList.size() != 0) {
            return "邮箱已经存在";
        }
        userMapper.insert(account);
        return "created";
    }
}
