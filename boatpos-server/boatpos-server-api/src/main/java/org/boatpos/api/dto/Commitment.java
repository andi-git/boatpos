package org.boatpos.api.dto;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * Representation of a commitment.
 */
@SuppressWarnings("unused")
public class Commitment extends AbstractDtoBasedOnEntity {

    /**
     * The name of the commitment.
     */
    @NotNull
    @Size(min = 3)
    @Expose
    private String name;

    /**
     * Check if a paper is needed.
     */
    @Expose
    private boolean paper;

    /**
     * The priority of this {@link Commitment}.
     */
    @NotNull
    @Min(0)
    private Integer priority;

    public Commitment() {
    }

    public Commitment(Long id, Integer version, String name, boolean paper, Integer priority) {
        super(id, version);
        this.name = name;
        this.paper = paper;
        this.priority = priority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPaper() {
        return paper;
    }

    public void setPaper(boolean paper) {
        this.paper = paper;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }
}
