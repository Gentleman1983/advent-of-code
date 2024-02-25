package de.havox_design.aoc2018.day24;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.util.List;

public record Army(String name, List<Group> groups) {
    public MutableArmy asMutableArmy() {
        return new MutableArmy(
                name,
                groups
                        .stream()
                        .map(Group::asMutableGroup)
                        .toList()
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Army army = (Army) o;

        return new EqualsBuilder()
                .append(name, army.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }
}
