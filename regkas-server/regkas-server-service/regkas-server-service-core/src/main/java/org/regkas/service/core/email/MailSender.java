package org.regkas.service.core.email;

public interface MailSender {

    String send(String subject, String content);
}
