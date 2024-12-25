package de.havox_design.aoc2022.day11

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Test

class MonkeyTest {
    @Test
    fun verifyEqualsContractOnGridClass() {
        EqualsVerifier.forClass(Monkey.Companion::class.java).suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT).verify()
    }
}
