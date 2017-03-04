package org.regkas.service.core.email;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class MailSenderFactory {

    @Inject
    private MailSender defaultMailSender;

    private MailSender currentMailSender;

    @PostConstruct
    public void reset() {
        currentMailSender = defaultMailSender;
    }

    public void setMailSender(MailSender mailSender) {
        currentMailSender = mailSender;
    }

    public MailSender getMailSender() {
        return currentMailSender;
    }
}
