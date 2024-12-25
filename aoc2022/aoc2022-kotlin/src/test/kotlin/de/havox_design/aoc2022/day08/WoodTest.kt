package de.havox_design.aoc2022.day08

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class WoodTest {
    @ParameterizedTest
    @MethodSource("getDataForTestReadSingleRowWoodByList")
    fun testReadSingleRowWoodByList(expectedTrees: List<Tree>) {
        val objectUnderTest = Wood()
        objectUnderTest.addRow(expectedTrees)

        assertAll(
            { objectUnderTest.getRows().shouldBe(1) },
            { objectUnderTest.getCols().shouldBe(expectedTrees.size) },
            {
                for (index in expectedTrees.indices) {
                    val expectedTree = expectedTrees[index]

                    objectUnderTest.getTree(0, index).shouldBe(expectedTree)
                }
            }
        )
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadSingleRowWood")
    fun testReadSingleRowWood(row: String, expectedTrees: List<Tree>) {
        val objectUnderTest = Wood()
        objectUnderTest.addRow(row)

        assertAll(
            { objectUnderTest.getRows().shouldBe(1) },
            { objectUnderTest.getCols().shouldBe(row.length) },
            {
                for (index in expectedTrees.indices) {
                    val expectedTree = expectedTrees[index]

                    objectUnderTest.getTree(0, index).shouldBe(expectedTree)
                }
            }
        )
    }

    companion object {
        @JvmStatic
        private fun getDataForTestReadSingleRowWoodByList(): Stream<Arguments> =
            Stream.of(
                Arguments.of(listOf(Tree(1))),
                Arguments.of(listOf(Tree(1), Tree(2), Tree(3), Tree(4), Tree(5))),
                Arguments.of(listOf(Tree(0), Tree(5), Tree(3), Tree(4), Tree(2), Tree(8), Tree(2)))
            )

        @JvmStatic
        private fun getDataForTestReadSingleRowWood(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "1",
                    listOf(Tree(1))
                ),
                Arguments.of(
                    "12345",
                    listOf(Tree(1), Tree(2), Tree(3), Tree(4), Tree(5))
                ),
                Arguments.of(
                    "0534282",
                    listOf(Tree(0), Tree(5), Tree(3), Tree(4), Tree(2), Tree(8), Tree(2))
                )
            )
    }
}
