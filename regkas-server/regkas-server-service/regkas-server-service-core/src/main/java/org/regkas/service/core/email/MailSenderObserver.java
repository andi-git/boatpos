package org.regkas.service.core.email;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;

@ApplicationScoped
public class MailSenderObserver {

    @Inject
    private MailSenderFactory mailSenderFactory;

    public void observeMailEvent(@Observes SendMailEvent sendMailEvent) {
        mailSenderFactory.getMailSender().send(sendMailEvent.getSubject(), sendMailEvent.getContent());
    }
}
