package org.boatpos.service.api.mail;

public interface MailSender {

    String send(String subject, String content);
}
