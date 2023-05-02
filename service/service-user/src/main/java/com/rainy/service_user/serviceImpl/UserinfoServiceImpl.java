package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rainy.service_user.entity.Userinfo;
import com.rainy.service_user.mapper.UserinfoMapper;
import com.rainy.service_user.service.MailService;
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

    @Autowired
    private MailService mailService;

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
        String code = userinfo.getCode();
        log.warn("code:" + code);
        // 主题
        String subject = "来自房产平台的激活邮件";
        // 上面的激活码发送到用户注册邮箱
        String context = "<a href=\"http://localhost:8082/api/server_user/checkCode?code=" + code + "\">激活请点击:" + code + "</a>";
        // 发送激活邮件
        mailService.sendHtmlMail(userinfo.getMail(), subject, context);
        return "created";
    }

    @Override
    public Userinfo checkCode(String code) {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code);
        Userinfo userinfo = userinfoMapper.selectOne(wrapper);
        return userinfo;
    }

    @Override
    public void updateUserinfoStatus(Userinfo userinfo) {
        UpdateWrapper<Userinfo> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", userinfo.getId());
        userinfoMapper.update(userinfo, wrapper);
    }

    @Override
    public Userinfo login(Userinfo userinfo) {
        return null;
    }
}
