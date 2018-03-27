package org.boatpos.service.core.mail;

import org.boatpos.service.api.mail.MailSender;

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
