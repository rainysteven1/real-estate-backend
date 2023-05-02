package com.rainy.service_user.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Map;

@Slf4j
@Component
public class MailUtil {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String from;

    @Async
    public void sendTemplateMail(String to, String subject, String mailTemplate, Map<String, Object> dataMap) {
        Context context = new Context();
        //设置传入模板页面的值   从dataMap中获取
        for (Map.Entry<String, Object> entry : dataMap.entrySet()) {
            context.setVariable(entry.getKey(), entry.getValue());
        }
        String templateContent = templateEngine.process(mailTemplate, context);

        try {
            //准备发送邮件
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            //设置发件人
            helper.setFrom(from);
            //设置收件人
            helper.setTo(to);
            //设置标题
            helper.setSubject(subject);
            //设置内容
            helper.setText(templateContent, true);
            mailSender.send(message);
            log.info("邮件已送达" + to);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("邮件未送达" + to);
        }
    }
}
