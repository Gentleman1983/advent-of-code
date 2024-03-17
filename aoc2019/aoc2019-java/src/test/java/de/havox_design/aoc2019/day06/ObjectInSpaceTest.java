package de.havox_design.aoc2019.day06;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ObjectInSpaceTest {
    @Test
    void testContracts() {
        EqualsVerifier
                .forClass(ObjectInSpace.class)
                .withPrefabValues(ObjectInSpace.class, new ObjectInSpace("foo"), new ObjectInSpace("bar"))
                .withIgnoredFields("centerObject")
                .verify();
    }
}
