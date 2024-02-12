package de.havox_design.aoc2018.day13;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Set;

public record Mine(Path[][] paths, Set<Cart> carts) {
    @Override
    public boolean equals(final Object other) {
        if (this == other) {
            return true;
        }

        if (other == null || getClass() != other.getClass()) {
            return false;
        }

        EqualsBuilder builder = new EqualsBuilder();

        builder.append(this.paths, ((Mine) other).paths);
        builder.append(this.carts, ((Mine) other).carts);

        return builder.isEquals();
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();

        builder.append(this.paths);
        builder.append(this.carts);

        return builder.toHashCode();
    }

    @Override
    public String toString() {
        ToStringBuilder builder = new ToStringBuilder(this, ToStringStyle.DEFAULT_STYLE);

        builder.append("paths", this.paths);
        builder.append("carts", this.carts);

        return builder.build();
    }
}
