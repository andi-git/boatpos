package org.boatpos.common.util.log;

import org.jboss.arquillian.junit.Arquillian;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.util.Arrays;

@RunWith(Arquillian.class)
public class LogWrapperTest {

    @Inject
    @SLF4J
    private LogWrapper logWrapper;

    private LogWrapper logWrapper2 = new LogWrapper(this.getClass());

    @Test
    public void testLog() throws Exception {
        logWrapper.log().info("test");
    }

    @Test
    public void testDebug() throws Exception {
        logWrapper.debug("test {}", () -> "debug");
        logWrapper.debug("test {} {}", Arrays.asList(() -> "debug", () -> "debug2"));
        logWrapper.debug("test {}", "debug");
    }

    @Test
    public void testInfo() throws Exception {
        logWrapper.info("test {}", () -> "info");
        logWrapper.info("test {} {}", Arrays.asList(() -> "info", () -> "info2"));
        logWrapper.info("test {}", "info");
    }

    @Test
    public void testWarn() throws Exception {
        logWrapper.warn("test {}", () -> "warn");
        logWrapper.warn("test {} {}", Arrays.asList(() -> "warn", () -> "warn2"));
        logWrapper.warn("test {}", "warn");
    }

    @Test
    public void testError() throws Exception {
        logWrapper.error("test {}", () -> "error");
        logWrapper.error("test {} {}", Arrays.asList(() -> "error", () -> "error2"));
        logWrapper.error("test {}", "error");
        logWrapper.error("test", new RuntimeException());
        logWrapper.error(new RuntimeException());
    }
}