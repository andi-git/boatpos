package org.boatpos.common.repository.api.model;

public interface Model<MODEL extends Model, ENTITY> {

    MODEL persist();

    void delete();

    ENTITY asEntity();
}
