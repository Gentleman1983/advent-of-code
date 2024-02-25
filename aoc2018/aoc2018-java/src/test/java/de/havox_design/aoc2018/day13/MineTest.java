package de.havox_design.aoc2018.day13;

import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

class MineTest {
    @Test
    void testContracts() {
        EqualsVerifier
                .forClass(Mine.class)
                .verify();
    }
}
