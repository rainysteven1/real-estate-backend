package com.rainy.service_user.serviceImpl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.google.common.base.Objects;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.RemovalListener;
import com.google.common.cache.RemovalNotification;
import com.rainy.commonutils.constants.ResultCode;
import com.rainy.service_user.entity.User;
import com.rainy.service_user.exception.CustomException;
import com.rainy.service_user.mapper.UserMapper;
import com.rainy.service_user.service.MailService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@EnableAsync
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private UserMapper userMapper;


    private final Cache<String, String> registerCache =
            CacheBuilder.newBuilder().maximumSize(100).expireAfterAccess(1, TimeUnit.MINUTES)
                    .removalListener(new RemovalListener<String, String>() {
                        @Override
                        public void onRemoval(@NotNull RemovalNotification<String, String> notification) {
                            System.out.println(3);
                            String email = notification.getValue();
                            QueryWrapper<User> wrapper = new QueryWrapper<>();
                            wrapper.eq("email", email);
                            wrapper.eq("deleted", false);
                            List<User> targetUser = userMapper.selectList(wrapper);
                            if (!targetUser.isEmpty() && !targetUser.get(0).getEnabled()) {
                                userMapper.delete(wrapper);//在删除前首先判断用户是否已经被激活，对于未激活的用户进行移除操作
                            }
                        }
                    }).build();


    public void sendMail(String to, String subject, String url) {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
        try {
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(url, true);
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
            log.error("邮件发送至" + to + "失败");
            throw new CustomException(ResultCode.ERROR_USER_REGISTER, "邮件发送失败");
        }
    }

    /**
     * 1.缓存key-email的关系 2.借助spring mail 发送邮件 3.借助异步框架进行异步操作
     *
     * @param email 用户邮箱
     */
    @Async
    public void registerNotify(String email) {
        String randomKey = RandomStringUtils.randomAlphabetic(10);
        registerCache.put(randomKey, email);
        String url = "<a href=\"http://" +
                "localhost:8080" +
                "/api/service_user/user/verify?key=" +
                randomKey + "\">点击超链接即可激活账户</a>";
        sendMail(email, "房产平台激活邮件", url);
    }

    @Override
    public void enable(String key) {
        String email = registerCache.getIfPresent(key);
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.set("enabled", true).eq("email", email).eq("deleted", false);
        userMapper.update(null, wrapper);
        registerCache.invalidate(key);
    }


}
