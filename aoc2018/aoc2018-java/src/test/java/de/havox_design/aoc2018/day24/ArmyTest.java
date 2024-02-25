package de.havox_design.aoc2018.day24;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class ArmyTest {
    @Test
    void testContracts() {
        EqualsVerifier
                .forClass(Army.class)
                .withIgnoredFields("groups")
                .verify();
    }
}
