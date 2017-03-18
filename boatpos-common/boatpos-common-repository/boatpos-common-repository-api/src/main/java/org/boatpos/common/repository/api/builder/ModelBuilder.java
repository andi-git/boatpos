package org.boatpos.common.repository.api.builder;

import org.boatpos.common.repository.api.model.Model;

public interface ModelBuilder<MODEL extends Model> {

    MODEL build();
}
