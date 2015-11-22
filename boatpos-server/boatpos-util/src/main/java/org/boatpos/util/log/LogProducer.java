package org.boatpos.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * Producer for {@link Logger}.
 */
@ApplicationScoped
public class LogProducer {

    /**
     * For CDI-usage.
     *
     * @param injectionPoint
     * @return
     */
    @Produces
    @SLF4J
    public Logger produce(InjectionPoint injectionPoint) {
        return LoggerFactory.getLogger(injectionPoint.getMember().getDeclaringClass());
    }

    /**
     * For non-CDI-usage.
     *
     * @param type
     * @return
     */
    public Logger produce(Class<?> type) {
        return LoggerFactory.getLogger(type);
    }
}
