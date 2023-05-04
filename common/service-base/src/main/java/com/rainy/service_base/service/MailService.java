package com.rainy.service_base.service;

import java.util.Map;

public interface MailService {

    void sendHtmlMail(String to, String subject, String emailTemplate, Map<String, Object> dataMap);
}
