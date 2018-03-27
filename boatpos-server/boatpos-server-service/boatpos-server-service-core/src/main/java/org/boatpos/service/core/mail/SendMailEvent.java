package org.boatpos.service.core.mail;

public class SendMailEvent {

    private final String subject;

    private final String content;

    public SendMailEvent(String subject, String content) {
        this.subject = subject;
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }
}
