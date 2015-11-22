package org.boatpos.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

/**
 * Basic class for all entities with an {@link #id} and a {@link #version}.
 */
public abstract class AbstractEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Version
    private Integer version;

}
