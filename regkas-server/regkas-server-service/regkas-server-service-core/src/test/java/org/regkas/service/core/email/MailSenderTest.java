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
        // a really stupid test for code-coverage...
        assertEquals("ERROR", mailSender.send("test-mail", "a simple test"));
    }
}
