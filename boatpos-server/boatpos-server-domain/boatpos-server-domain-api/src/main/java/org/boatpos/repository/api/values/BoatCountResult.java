package org.boatpos.repository.api.values;

import com.google.common.base.Objects;
import org.boatpos.repository.api.model.Boat;

/**
 * The count of {@link Boat}s.
 */
public class BoatCountResult {

    private final String boatName;

    private final Long count;

    public BoatCountResult(String boatName, Long count) {
        this.boatName = boatName;
        this.count = count;
    }

    public String getBoatName() {
        return boatName;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BoatCountResult boatCountResult = (BoatCountResult) o;
        return Objects.equal(boatName, boatCountResult.boatName) &&
                Objects.equal(count, boatCountResult.count);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(boatName, count);
    }

    @Override
    public String toString() {
        return "BoatCount{" +
                "boatName='" + boatName + '\'' +
                ", count=" + count +
                '}';
    }
}
