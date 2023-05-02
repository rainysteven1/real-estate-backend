package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rainy.service_user.entity.Userinfo;
import com.rainy.service_user.mapper.UserinfoMapper;
import com.rainy.service_user.service.UserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.rainy.service_user.utils.MailUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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
    private MailUtil mailUtil;

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
        String subject = "房产平台的激活邮件";
        // 上面的激活码发送到用户注册邮箱
        String link = "http://127.0.0.1:8082/api/server_user/checkCode?code=" + code;
        String mailTemplate = "registerTemplate";
        Map<String, Object> dataMap = new HashMap<>();
        dataMap.put("mail", userinfo.getMail());
        dataMap.put("createTime", new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss").format(new Date()));
        dataMap.put("link", link);
        log.info(link);
        // 发送激活邮件
        mailUtil.sendTemplateMail(userinfo.getMail(), subject, mailTemplate, dataMap);
        return "created";
    }

    @Override
    public Userinfo checkCode(String code) {
        QueryWrapper<Userinfo> wrapper = new QueryWrapper<>();
        wrapper.eq("code", code);
        return userinfoMapper.selectOne(wrapper);
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
