package org.boatpos.common.domain.core;

import org.boatpos.common.util.qualifiers.Current;

import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NonUniqueResultException;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@Dependent
public class JPAHelper {

    @Inject
    @Current
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public <T> Optional<T> getSingleResult(final Collection<T> collection) {
        if (collection.size() == 0) {
            return Optional.empty();
        } else if (collection.size() == 1) {
            return Optional.ofNullable(collection.iterator().next());
        } else {
            String message = "multiple entities of " + collection.iterator().next().getClass().getName() + " available: ";
            for (T c : collection) {
                message += c.getClass().getName() + ", ";
            }
            throw new NonUniqueResultException(message);
        }
    }

    public <T> TypedQuery<T> createNamedQuery(final String queryName, final Class<T> type) {
        return getEntityManager().createNamedQuery(queryName, type);
    }
}
