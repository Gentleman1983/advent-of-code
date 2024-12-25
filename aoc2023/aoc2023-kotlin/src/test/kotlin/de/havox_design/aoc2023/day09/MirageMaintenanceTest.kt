package de.havox_design.aoc2023.day09

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MirageMaintenanceTest {
    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart1")
    fun testSolvePart1(filename: String, expectedResult: Long) =
        MirageMaintenance(filename).solvePart1().shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestSolvePart2")
    fun testSolvePart2(filename: String, expectedResult: Long) =
        MirageMaintenance(filename).solvePart2().shouldBe(expectedResult)

    companion object {
        @JvmStatic
        private fun getDataForTestSolvePart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample1.txt", 18L),
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample2.txt", 28L),
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample3.txt", 68L),
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample4.txt", 114L)
            )

        @JvmStatic
        private fun getDataForTestSolvePart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample1.txt", -3L),
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample2.txt", 0L),
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample3.txt", 5L),
                Arguments.of("de/havox_design/aoc2023/day09/day09Sample4.txt", 2L)
            )
    }
}
