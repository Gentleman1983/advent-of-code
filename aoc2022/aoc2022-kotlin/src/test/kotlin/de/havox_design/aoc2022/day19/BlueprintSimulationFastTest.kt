package de.havox_design.aoc2022.day19

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class BlueprintSimulationFastTest {
    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestBlueprintSimulation")
    fun testBlueprintSimulationFast(filename: String, blueprintId: Int, minutes: Int, expectedQualityLevel: Int) =
        BlueprintSimulationFast(
            blueprint = NotEnoughMinerals(filename)
                .blueprints
                .first { blueprint -> blueprint.id == blueprintId },
            minutes = minutes
        )
            .simulateBlueprint()
            .shouldBe(expectedQualityLevel)

    companion object {
        @JvmStatic
        private fun getDataForTestBlueprintSimulation(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day19/day19Sample.txt",
                    1,
                    24,
                    9
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day19/day19Sample.txt",
                    2,
                    24,
                    24
                )
            )
    }
}
