package org.boatpos.repository.api;

import javax.inject.Qualifier;
import javax.persistence.EntityManager;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * The {@link Qualifier} for the current {@link EntityManager}.
 */
@Qualifier
@Retention(RUNTIME)
@Target({
    TYPE,
    METHOD,
    PARAMETER,
    FIELD
})
@Documented
public @interface BoatPosDB {

}
