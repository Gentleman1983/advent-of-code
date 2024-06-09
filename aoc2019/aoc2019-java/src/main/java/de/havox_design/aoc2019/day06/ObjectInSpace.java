package de.havox_design.aoc2019.day06;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;
import java.util.stream.Stream;

public final class ObjectInSpace {
    public static final String CENTER_OF_MASS_NAME = "COM";

    private final String name;
    private ObjectInSpace centerObject;

    public ObjectInSpace(String name) {
        this.name = Objects.requireNonNull(name);
    }

    public Stream<ObjectInSpace> toCenterOfMass() {
        return Stream.iterate(this.getCenterObject(), Objects::nonNull, ObjectInSpace::getCenterObject);
    }

    public boolean isCenterOfMass() {
        return centerObject == null;
    }

    public String getName() {
        return name;
    }

    public ObjectInSpace getCenterObject() {
        return centerObject;
    }

    public void setCenterObject(ObjectInSpace centerObject) {
        this.centerObject = centerObject;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ObjectInSpace that = (ObjectInSpace) o;

        return new EqualsBuilder()
                .append(name, that.name)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(name)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(ToStringStyle.DEFAULT_STYLE)
                .append(name)
                .toString();
    }
}
