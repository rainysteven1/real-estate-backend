package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.rainy.commonutils.service.MailService;
import com.rainy.service_user.entity.Userinfo;
import com.rainy.service_user.exception.CustomException;
import com.rainy.service_user.mapper.UserinfoMapper;
import com.rainy.service_user.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author rainy
 * @since 2023-04-29
 */
@Slf4j
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements UserinfoService {

    @Autowired
    private UserinfoMapper userinfoMapper;

    @Override
    public String register(Userinfo userinfo) {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("mail", userinfo.getMail());
        List<Userinfo> userinfos = userinfoMapper.selectList(wrapper);
        if (userinfos.size() != 0) {
            return "该邮箱已注册";
        }
        userinfo.setUsername(userinfo.getMail()).setAvatar("");
        userinfoMapper.insert(userinfo);
        return "created";
    }

    @Override
    public Userinfo checkCode(String code) {
        return null;
    }

    @Override
    public void updateUserStatus(Userinfo userinfo) {

    }

    @Override
    public Userinfo login(Userinfo userinfo) {
        return null;
    }
}
