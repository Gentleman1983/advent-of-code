package de.havox_design.aoc2018.day15;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

public record GameMap(Field[][] field, List<Unit> units) {
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        EqualsBuilder builder = new EqualsBuilder();

        builder.append(this.field, ((GameMap) other).field);
        builder.append(this.units, ((GameMap) other).units);

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(this.field);
        builder.append(this.units);

        return builder.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);

        builder.append("field", this.field);
        builder.append("units", this.units);

        return builder.build();
    }
}
