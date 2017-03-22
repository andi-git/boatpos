package org.boatpos.common.domain.api.builder;

import org.boatpos.common.domain.api.model.Model;

public interface ModelBuilder<MODEL extends Model> {

    MODEL build();
}
