package de.havox_design.aoc2018.day15;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class GameMapTest {
    @Test
    void testContracts() {
        EqualsVerifier
                .forClass(GameMap.class)
                .verify();
    }
}
