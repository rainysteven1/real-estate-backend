package com.rainy.service_user.serviceImpl;

import com.rainy.commonutils.service.MailService;
import com.rainy.service_user.entity.Userinfo;
import com.rainy.service_user.mapper.UserinfoMapper;
import com.rainy.service_user.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void register(Userinfo userinfo) {
        System.out.println("hello");
//        String code = userinfo.getCode();
//        log.warn("code: " + code);
//        String subject = "房产平台激活邮件";
//        String content = "<a href=\"http://localhost:8080/service_user/userinfo/checkCode?code=" + code + "\">激活请点击:" + code + "</a>";
//        mailService.sendHtmlMail(userinfo.getMail(), subject, content);
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
