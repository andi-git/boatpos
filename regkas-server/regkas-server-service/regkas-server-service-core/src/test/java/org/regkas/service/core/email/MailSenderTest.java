package org.regkas.service.core.email;

import static org.junit.Assert.assertEquals;

import javax.inject.Inject;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(Arquillian.class)
public class MailSenderTest {

    @Inject
    private MailSender mailSender;

    @Test
    public void testSend() throws Exception {
        System.setProperty("boatpos.email.host", "my.host.com");
        System.setProperty("boatpos.email.port", "25");
        System.setProperty("boatpos.email.username", "username");
        System.setProperty("boatpos.email.password", "password");
        System.setProperty("boatpos.email.mailFrom", "mail@from.com");
        System.setProperty("boatpos.email.mailTo", "mail@to.com");
        // a really stupid test for code-coverage...
        assertEquals("ERROR", mailSender.send("test-mail", "a simple test"));
        System.clearProperty("boatpos.email.host");
        System.clearProperty("boatpos.email.port");
        System.clearProperty("boatpos.email.username");
        System.clearProperty("boatpos.email.password");
        System.clearProperty("boatpos.email.mailFrom");
        System.clearProperty("boatpos.email.mailTo");
    }
}
