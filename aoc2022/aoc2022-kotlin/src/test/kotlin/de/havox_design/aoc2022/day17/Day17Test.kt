package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day17Test {

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, expectedResult: Long) =
        PyroclasticFlow(filename).processPart1().shouldBe(expectedResult)

    @Disabled
    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, expectedResult: Long) =
        PyroclasticFlow(filename).processPart1(1000000000000).shouldBe(expectedResult)

    @ParameterizedTest
    @MethodSource("getDataForTestRocks")
    fun testRocks(rock: Rock, expectedWidth: Long, expectedHeigth: Long, expectedBlockedElements: Set<Position2d<Long>>) =
        assertAll(
            { rock.dimensionX.shouldBe(expectedWidth) },
            { rock.dimensionY.shouldBe(expectedHeigth) },
            { rock.getBlockedPositions().shouldBe(expectedBlockedElements) }
        )

    @ParameterizedTest
    @MethodSource("getDataForTestJets")
    fun testJets(code: String, expectedJet: Jet) =
        Jet.getJetForCode(code).shouldBe(expectedJet)

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, expectedJets: List<Jet>) =
        PyroclasticFlow(filename).jetPattern.shouldBe(expectedJets)

    @ParameterizedTest
    @MethodSource("getDataForTestRockSpawning")
    fun testRockSpawning(expectedPosition: Position2d<Long>) =
        Chamber().getStartPositionForRock().shouldBe(expectedPosition)

    @ParameterizedTest
    @MethodSource("getDataForTestStatusAfterStone")
    fun testStatusAfterStone(filename: String, stones: Long, expectedBlockers: Set<Position2d<Long>>) {
        val objectUnderTest = PyroclasticFlow(filename)
        objectUnderTest.processPart1(stones)

        objectUnderTest.chamber.obstacles.shouldContainAll(expectedBlockers)
    }

    companion object {
        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day17/day17Sample.txt", 3068)
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day17/day17Sample.txt", 1514285714288)
            )

        @JvmStatic
        private fun getDataForTestRocks(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Rock.ARROW, 3, 3, getBlockedFieldsForString("..#\n..#\n###")),
                Arguments.of(Rock.BOX, 2, 2, getBlockedFieldsForString("##\n##")),
                Arguments.of(Rock.HORIZONTAL_LINE, 4, 1, getBlockedFieldsForString("####")),
                Arguments.of(Rock.PLUS, 3, 3, getBlockedFieldsForString(".#.\n###\n.#.")),
                Arguments.of(Rock.VERTICAL_LINE, 1, 4, getBlockedFieldsForString("#\n#\n#\n#"))
            )

        @JvmStatic
        private fun getDataForTestJets(): Stream<Arguments> =
            Stream.of(
                Arguments.of("<", Jet.LEFT),
                Arguments.of(">", Jet.RIGHT),
                Arguments.of("v", Jet.DOWN),
                Arguments.of("foo", Jet.UNKNOWN),
                Arguments.of("bar", Jet.UNKNOWN),
                Arguments.of(".", Jet.UNKNOWN)
            )

        @JvmStatic
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of("de/havox_design/aoc2022/day17/day17Sample.txt", toJetList(">>><<><>><<<>><>>><<<>>><<<><<<>><>><<>>"))
            )

        @JvmStatic
        private fun getDataForTestRockSpawning(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Position2d(2L, 3L))
            )

        @JvmStatic
        private fun getDataForTestStatusAfterStone(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    1,
                    getBlockedFieldsForString("..####.")
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    2,
                    getBlockedFieldsForString(
                        "...#...\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    3,
                    getBlockedFieldsForString(
                        "..#....\n" +
                                "..#....\n" +
                                "####...\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    4,
                    getBlockedFieldsForString(
                        "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    5,
                    getBlockedFieldsForString(
                        "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    6,
                    getBlockedFieldsForString(
                        ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    7,
                    getBlockedFieldsForString(
                        "..#....\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    8,
                    getBlockedFieldsForString(
                        ".....#.\n" +
                                ".....#.\n" +
                                "..####.\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    9,
                    getBlockedFieldsForString(
                        "....#..\n" +
                                "....#..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "..####.\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                ),
                Arguments.of(
                    "de/havox_design/aoc2022/day17/day17Sample.txt",
                    10,
                    getBlockedFieldsForString(
                        "....#..\n" +
                                "....#..\n" +
                                "....##.\n" +
                                "##..##.\n" +
                                "######.\n" +
                                ".###...\n" +
                                "..#....\n" +
                                ".####..\n" +
                                "....##.\n" +
                                "....##.\n" +
                                "....#..\n" +
                                "..#.#..\n" +
                                "..#.#..\n" +
                                "#####..\n" +
                                "..###..\n" +
                                "...#...\n" +
                                "..####."
                    )
                )
            )

        private fun toJetList(data: String): List<Jet> {
            val jets = emptyList<Jet>().toMutableList()

            for (index in data.indices) {
                jets += Jet.getJetForCode(data.substring(index, index + 1))
            }

            return jets
        }

        private fun getBlockedFieldsForString(data: String): Set<Position2d<Long>> {
            val result: MutableSet<Position2d<Long>> = emptySet<Position2d<Long>>().toMutableSet()

            val rows = data.split("\n")

            for (rowIndex in rows.indices) {
                val row = rows[rowIndex]

                for (colIndex in row.indices) {
                    val letter = row[colIndex]

                    if (letter == '#') {
                        result += Position2d(colIndex.toLong(), (rows.size - rowIndex - 1).toLong())
                    }
                }
            }

            return result
        }
    }
}
