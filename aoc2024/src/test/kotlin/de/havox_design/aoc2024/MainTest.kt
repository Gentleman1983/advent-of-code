package de.havox_design.aoc2024

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class MainTest {
    @ParameterizedTest
    @MethodSource("getDataForTestMainClass")
    fun testMainClass(args: Array<String>) {
        MainClass.main(args)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestMainClass(): Stream<Arguments> =
            Stream.of(
                Arguments.of(arrayOf("day01")),
                Arguments.of(arrayOf("day02")),
                Arguments.of(arrayOf("day03")),
                Arguments.of(arrayOf("day04")),
                Arguments.of(arrayOf("day05")),
                Arguments.of(arrayOf("day06")),
                Arguments.of(arrayOf("day07")),
                Arguments.of(arrayOf("day08")),
                Arguments.of(arrayOf("day09")),
                Arguments.of(arrayOf("day10")),
                Arguments.of(arrayOf("day11")),
                Arguments.of(arrayOf("day12")),
                Arguments.of(arrayOf("day13")),
                Arguments.of(arrayOf("day14")),
                Arguments.of(arrayOf("day15")),
                Arguments.of(arrayOf("day16")),
                Arguments.of(arrayOf("day17")),
                Arguments.of(arrayOf("day18")),
                Arguments.of(arrayOf("day19")),
                Arguments.of(arrayOf("day20")),
                Arguments.of(arrayOf("day21")),
                Arguments.of(arrayOf("day22")),
                Arguments.of(arrayOf("day23")),
                Arguments.of(arrayOf("day24")),
                Arguments.of(arrayOf("day25"))
            )
    }
}