package de.havox_design.aoc2022.day19

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class NotEnoughMineralsTest {
    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Int) =
        NotEnoughMinerals(filename).processPart1().shouldBe(expectedResult)

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Int) =
        NotEnoughMinerals(filename).processPart2(2).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestReadFile")
    fun testReadFile(filename: String, expectedResult: List<Blueprint>) =
        NotEnoughMinerals(filename).blueprints.shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day19/day19Sample.txt", 33)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day19/day19Sample.txt", 56)
            )

        @JvmStatic
        private fun getDataForTestReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day19/day19Sample.txt",
                    listOf(
                        Blueprint(
                            id = 1,
                            costOreRobot = RobotCurrency(ore = 4),
                            costClayRobot = RobotCurrency(ore = 2),
                            costObsidianRobot = RobotCurrency(ore = 3, clay = 14),
                            costGeodeRobot = RobotCurrency(ore = 2, obsidian = 7)
                        ),
                        Blueprint(
                            id = 2,
                            costOreRobot = RobotCurrency(ore = 2),
                            costClayRobot = RobotCurrency(ore = 3),
                            costObsidianRobot = RobotCurrency(ore = 3, clay = 8),
                            costGeodeRobot = RobotCurrency(ore = 3, obsidian = 12)
                        )
                    )
                )
            )
    }
}
