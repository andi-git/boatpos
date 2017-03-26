package org.regkas.service.core.email;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Alternative;

@Alternative
public class MailSenderMock implements MailSender {

    private boolean sendCalled = false;

    private final List<MailSend> mailSendList = new ArrayList<>();

    @Override
    public String send(String subject, String content) {
        sendCalled = true;
        mailSendList.add(new MailSend(subject, content));
        return null;
    }

    public void reset() {
        sendCalled = false;
    }

    public boolean isSendCalled() {
        return sendCalled;
    }

    public List<MailSend> getMailSendList() {
        return mailSendList;
    }

    public static class MailSend {

        private final String subject;
        private final String content;

        public MailSend(String subject, String content) {
            this.subject = subject;
            this.content = content;
        }

        public String getSubject() {
            return subject;
        }

        public String getContent() {
            return content;
        }

        @Override
        public String toString() {
            return subject + ": " + content;
        }
    }
}
