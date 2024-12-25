package de.havox_design.aoc2022.day10

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class CathodeRayTubeTest {
    @ParameterizedTest
    @MethodSource("getDataForReadFile")
    fun testReadFile(filename: String, expectedInstructions: List<Instruction>) {
        val objectUnderTest = CathodeRayTube(filename)

        assertAll(
            { objectUnderTest.instructions.shouldContainAll(expectedInstructions) },
            { objectUnderTest.instructions.shouldBe(expectedInstructions) }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForProcessPart1")
    fun testProcessPart1(filename: String, expectedSignalStrength: Int) =
        CathodeRayTube(filename).processPart1().shouldBe(expectedSignalStrength)

    @ParameterizedTest
    @MethodSource("getDataForProcessPart2")
    fun testProcessPart2(filename: String, expectedOutput: String) =
        CathodeRayTube(filename).processPart2().shouldBe(expectedOutput)

    companion object {
        @JvmStatic
        private fun getDataForReadFile(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day10/day10Sample1.txt",
                    listOf(
                        Instruction(Order.NOOP),
                        Instruction(Order.ADDX, 3),
                        Instruction(Order.ADDX, -5)
                    )
                )
            )

        @JvmStatic
        private fun getDataForProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day10/day10Sample1.txt", 0),
                Arguments.of("de/havox_design/aoc2022/day10/day10Sample2.txt", 13140)
            )

        @JvmStatic
        private fun getDataForProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day10/day10Sample2.txt",
                    "##..##..##..##..##..##..##..##..##..##..\n" +
                            "###...###...###...###...###...###...###.\n" +
                            "####....####....####....####....####....\n" +
                            "#####.....#####.....#####.....#####.....\n" +
                            "######......######......######......####\n" +
                            "#######.......#######.......#######....."
                )
            )
    }
}
