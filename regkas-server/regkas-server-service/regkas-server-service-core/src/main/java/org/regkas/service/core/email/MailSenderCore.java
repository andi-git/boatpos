package org.regkas.service.core.email;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.boatpos.common.util.log.LogWrapper;
import org.boatpos.common.util.log.SLF4J;

@ApplicationScoped
public class MailSenderCore implements MailSender {

    @Inject
    @SLF4J
    private LogWrapper log;

    private String host;

    private Integer port;

    private String username;

    private String password;

    private String mailFrom;

    private String mailTo;

    @PostConstruct
    private void init() {
        host = System.getProperty("boatpos.email.host", "my.host.com");
        port = Integer.valueOf(System.getProperty("boatpos.email.port", "25"));
        username = System.getProperty("boatpos.email.username", "username");
        password = System.getProperty("boatpos.email.password", "password");
        mailFrom = System.getProperty("boatpos.email.mailFrom", "mail@from.com");
        mailTo = System.getProperty("boatpos.email.mailTo", "mail@to.com");
    }

    @Override
    public String send(String subject, String content) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(host);
            email.setSmtpPort(port);
            email.setAuthenticator(new DefaultAuthenticator(username, password));
            email.setSSLOnConnect(false);
            email.setFrom(mailFrom);
            email.setSubject(subject);
            email.setMsg(content);
            email.addTo(mailTo);
            email.setSocketTimeout(1000);
            email.setSocketConnectionTimeout(1000);
            return email.send();
        } catch (EmailException e) {
            log.error(e);
            return "ERROR";
        }
    }
}
