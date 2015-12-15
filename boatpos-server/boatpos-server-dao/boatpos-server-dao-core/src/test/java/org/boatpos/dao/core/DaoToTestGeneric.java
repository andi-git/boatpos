package org.boatpos.dao.core;

import org.boatpos.model.Boat;

import javax.enterprise.context.Dependent;
import javax.persistence.TypedQuery;
import java.util.Collection;
import java.util.Optional;

@Dependent
public class DaoToTestGeneric extends GenericDaoCore<Boat> {

    @Override
    public Class<Boat> getType() {
        return Boat.class;
    }

    @Override
    protected <T> Optional<T> getSingleResult(Collection<T> collection) {
        return super.getSingleResult(collection);
    }

    @Override
    public TypedQuery<Boat> createTypedNamedQuery(String queryName) {
        return super.createTypedNamedQuery(queryName);
    }
}
