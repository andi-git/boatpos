package org.boatpos.dao.api;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.persistence.EntityManager;

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
