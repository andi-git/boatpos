package org.boatpos.service.core;

import org.boatpos.domain.api.model.Promotion;

import javax.enterprise.context.ApplicationScoped;

/**
 * check if a {@link Promotion} is active.
 */
@ApplicationScoped
public class PromotionChecker {

    public boolean active(Promotion promotion) {
        return true;
    }
}
