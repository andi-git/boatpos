package org.regkas.service.core.email;

import javax.enterprise.inject.Alternative;

@Alternative
public class MailSenderMock implements MailSender {

    private boolean sendCalled = false;

    @Override
    public String send(String subject, String content) {
        sendCalled = true;
        return null;
    }

    public void reset() {
        sendCalled = false;
    }

    public boolean isSendCalled() {
        return sendCalled;
    }
}
