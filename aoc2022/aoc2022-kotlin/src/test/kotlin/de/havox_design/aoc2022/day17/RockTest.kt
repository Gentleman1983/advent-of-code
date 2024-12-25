package de.havox_design.aoc2022.day17

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.model.positions.Position2d
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class RockTest {
    @ParameterizedTest
    @MethodSource("getDataForTestRocks")
    fun testRocks(rock: Rock, expectedWidth: Long, expectedHeigth: Long, expectedBlockedElements: Set<Position2d<Long>>) =
        assertAll(
            { rock.dimensionX.shouldBe(expectedWidth) },
            { rock.dimensionY.shouldBe(expectedHeigth) },
            { rock.getBlockedPositions().shouldBe(expectedBlockedElements) }
        )

    companion object {
        @JvmStatic
        private fun getDataForTestRocks(): Stream<Arguments> =
            Stream.of(
                Arguments.of(Rock.ARROW, 3, 3, getBlockedFieldsForString("..#\n..#\n###")),
                Arguments.of(Rock.BOX, 2, 2, getBlockedFieldsForString("##\n##")),
                Arguments.of(Rock.HORIZONTAL_LINE, 4, 1, getBlockedFieldsForString("####")),
                Arguments.of(Rock.PLUS, 3, 3, getBlockedFieldsForString(".#.\n###\n.#.")),
                Arguments.of(Rock.VERTICAL_LINE, 1, 4, getBlockedFieldsForString("#\n#\n#\n#"))
            )

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
