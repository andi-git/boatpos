package org.boatpos.common.domain.api.model;

public interface Model<MODEL extends Model, ENTITY> {

    MODEL persist();

    void delete();

    ENTITY asEntity();
}
