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
     * @param injectionPoint the {@link InjectionPoint} of the injection where we get the type from
     * @return a new initialized {@link Logger} for the class where it is injected
     */
    @Produces
    @SLF4J
    public Logger produce(InjectionPoint injectionPoint) {
        return produce(injectionPoint.getMember().getDeclaringClass());
    }

    /**
     * For non-CDI-usage.
     *
     * @param type the type to instantiate the logger
     * @return a new initialized {@link Logger} for the class
     */
    public Logger produce(Class<?> type) {
        return LoggerFactory.getLogger(type);
    }
}
