package de.havox_design.aoc2022.day11

import nl.jqno.equalsverifier.EqualsVerifier
import nl.jqno.equalsverifier.Warning
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

class Day11Test {
    @Test
    fun testMainClass() {
        MainClass.main(arrayOf())
    }

    @ParameterizedTest
    @MethodSource("getDataForTestReadData")
    fun testReadData(filename: String, monkeys: List<Monkey>) =
        MonkeyInTheMiddle(filename).monkeys.shouldContainAll(monkeys)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1ItemMovement")
    fun testProcessPart1ItemMovement(filename: String, numberOfRounds: Int, expectedItems: Map<Int, List<Item>>) {
        MonkeyInTheMiddle(filename).processPart1(numberOfRounds)

        for (monkeyId in expectedItems.keys) {
            val monkey = Monkey.getMonkeyForId(monkeyId)!!
            monkey.startingItems.shouldContainAll(
                expectedItems[monkeyId]!!,
                "expected monkey $monkeyId to hold all items ${expectedItems[monkeyId]} but was ${monkey.startingItems} after $numberOfRounds rounds."
            )
        }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1Inspections")
    fun testProcessPart1Inspections(filename: String, numberOfRounds: Int, expectedInspections: Map<Int, Long>) {
        MonkeyInTheMiddle(filename).processPart1(numberOfRounds)

        for (monkeyId in expectedInspections.keys) {
            val monkey = Monkey.getMonkeyForId(monkeyId)!!
            monkey.numberOfInspectedItems.shouldBe(expectedInspections[monkeyId]!!)
        }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart1")
    fun testProcessPart1(filename: String, numberOfRounds: Int, expectedInspections: Long) =
        MonkeyInTheMiddle(filename).processPart1(numberOfRounds).shouldBe(expectedInspections)

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2Inspections")
    fun testProcessPart2Inspections(filename: String, numberOfRounds: Int, expectedInspections: Map<Int, Long>) {
        MonkeyInTheMiddle(filename).processPart2(numberOfRounds)

        for (monkeyId in expectedInspections.keys) {
            val monkey = Monkey.getMonkeyForId(monkeyId)!!
            monkey.numberOfInspectedItems.shouldBe(expectedInspections[monkeyId]!!)
        }
    }

    @ParameterizedTest
    @MethodSource("getDataForTestProcessPart2")
    fun testProcessPart2(filename: String, numberOfRounds: Int, expectedInspections: Long) =
        MonkeyInTheMiddle(filename).processPart2(numberOfRounds).shouldBe(expectedInspections)

    @Test
    fun verifyEqualsContractOnGridClass() {
        EqualsVerifier.forClass(Monkey.Companion::class.java).suppress(Warning.INHERITED_DIRECTLY_FROM_OBJECT).verify()
    }

    companion object {
        @JvmStatic
        private fun getDataForTestReadData(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    listOf(
                        createMonkey(
                            0,
                            toItemList(79, 98),
                            { old: Long -> old * 19 },
                            23,
                            2,
                            3
                        ),
                        createMonkey(
                            1,
                            toItemList(54, 65, 75, 74),
                            { old: Long -> old + 6 },
                            19,
                            2,
                            0
                        ),
                        createMonkey(
                            2,
                            toItemList(79, 60, 97),
                            { old: Long -> old * old },
                            13,
                            1,
                            3
                        ),
                        createMonkey(
                            3,
                            toItemList(74),
                            { old: Long -> old + 3 },
                            17,
                            0,
                            1
                        )
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart1ItemMovement(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    1,
                    mapOf(
                        Pair(0, toItemList(20, 23, 27, 26)),
                        Pair(1, toItemList(2080, 25, 167, 207, 401, 1046)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    2,
                    mapOf(
                        Pair(0, toItemList(695, 10, 71, 135, 350)),
                        Pair(1, toItemList(43, 49, 58, 55, 362)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    3,
                    mapOf(
                        Pair(0, toItemList(16, 18, 21, 20, 122)),
                        Pair(1, toItemList(1468, 22, 150, 286, 739)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    4,
                    mapOf(
                        Pair(0, toItemList(491, 9, 52, 97, 248, 34)),
                        Pair(1, toItemList(39, 45, 43, 258)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    5,
                    mapOf(
                        Pair(0, toItemList(15, 17, 16, 88, 1037)),
                        Pair(1, toItemList(20, 110, 205, 524, 72)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    6,
                    mapOf(
                        Pair(0, toItemList(8, 70, 176, 26, 34)),
                        Pair(1, toItemList(481, 32, 36, 186, 2190)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    7,
                    mapOf(
                        Pair(0, toItemList(162, 12, 14, 64, 732, 17)),
                        Pair(1, toItemList(148, 372, 55, 72)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    8,
                    mapOf(
                        Pair(0, toItemList(51, 126, 20, 26, 136)),
                        Pair(1, toItemList(343, 26, 30, 1546, 36)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    9,
                    mapOf(
                        Pair(0, toItemList(116, 10, 12, 517, 14)),
                        Pair(1, toItemList(108, 267, 43, 55, 288)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    10,
                    mapOf(
                        Pair(0, toItemList(91, 16, 20, 98)),
                        Pair(1, toItemList(481, 245, 22, 26, 1092, 30)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    15,
                    mapOf(
                        Pair(0, toItemList(83, 44, 8, 184, 9, 20, 26, 102)),
                        Pair(1, toItemList(110, 36)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    20,
                    mapOf(
                        Pair(0, toItemList(10, 12, 14, 26, 34)),
                        Pair(1, toItemList(245, 93, 53, 199, 115)),
                        Pair(2, toItemList()),
                        Pair(3, toItemList())
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart1Inspections(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    1,
                    mapOf(
                        Pair(0, 2),
                        Pair(1, 4),
                        Pair(2, 3),
                        Pair(3, 5)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    20,
                    mapOf(
                        Pair(0, 101),
                        Pair(1, 95),
                        Pair(2, 7),
                        Pair(3, 105)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart1(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    1,
                    20
                ),
                Arguments.of(
                    "sample1.txt",
                    20,
                    10605
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart2Inspections(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    1,
                    mapOf(
                        Pair(0, 2),
                        Pair(1, 4),
                        Pair(2, 3),
                        Pair(3, 6)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    20,
                    mapOf(
                        Pair(0, 99),
                        Pair(1, 97),
                        Pair(2, 8),
                        Pair(3, 103)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    1000,
                    mapOf(
                        Pair(0, 5204),
                        Pair(1, 4792),
                        Pair(2, 199),
                        Pair(3, 5192)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    2000,
                    mapOf(
                        Pair(0, 10419),
                        Pair(1, 9577),
                        Pair(2, 392),
                        Pair(3, 10391)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    3000,
                    mapOf(
                        Pair(0, 15638),
                        Pair(1, 14358),
                        Pair(2, 587),
                        Pair(3, 15593)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    4000,
                    mapOf(
                        Pair(0, 20858),
                        Pair(1, 19138),
                        Pair(2, 780),
                        Pair(3, 20797)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    5000,
                    mapOf(
                        Pair(0, 26075),
                        Pair(1, 23921),
                        Pair(2, 974),
                        Pair(3, 26000)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    6000,
                    mapOf(
                        Pair(0, 31294),
                        Pair(1, 28702),
                        Pair(2, 1165),
                        Pair(3, 36400)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    7000,
                    mapOf(
                        Pair(0, 36508),
                        Pair(1, 33488),
                        Pair(2, 1360),
                        Pair(3, 36400)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    8000,
                    mapOf(
                        Pair(0, 41728),
                        Pair(1, 38268),
                        Pair(2, 1553),
                        Pair(3, 41606)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    9000,
                    mapOf(
                        Pair(0, 46945),
                        Pair(1, 43051),
                        Pair(2, 1746),
                        Pair(3, 46807)
                    )
                ),
                Arguments.of(
                    "sample1.txt",
                    10000,
                    mapOf(
                        Pair(0, 52166),
                        Pair(1, 47830),
                        Pair(2, 1938),
                        Pair(3, 52013)
                    )
                )
            )

        @JvmStatic
        private fun getDataForTestProcessPart2(): Stream<Arguments> =
            Stream.of(
                Arguments.of(
                    "sample1.txt",
                    1,
                    24
                ),
                Arguments.of(
                    "sample1.txt",
                    20,
                    99*103
                ),
                Arguments.of(
                    "sample1.txt",
                    1000,
                    5204*5192
                ),
                Arguments.of(
                    "sample1.txt",
                    2000,
                    10419*10391
                ),
                Arguments.of(
                    "sample1.txt",
                    3000,
                    15638*15593
                ),
                Arguments.of(
                    "sample1.txt",
                    4000,
                    20858*20797
                ),
                Arguments.of(
                    "sample1.txt",
                    5000,
                    26075*26000
                ),
                Arguments.of(
                    "sample1.txt",
                    6000,
                    31294*36400
                ),
                Arguments.of(
                    "sample1.txt",
                    7000,
                    36508*36400
                ),
                Arguments.of(
                    "sample1.txt",
                    8000,
                    41728L*41606L
                ),
                Arguments.of(
                    "sample1.txt",
                    9000,
                    46945L*46807L
                ),
                Arguments.of(
                    "sample1.txt",
                    10000,
                    52166L*52013L
                )
            )

        private fun toItemList(vararg items: Long): List<Item> =
            toItemList(items.asList())

        private fun toItemList(startingItems: List<Long> = emptyList()): List<Item> {
            val itemList = emptyList<Item>().toMutableList()

            for (item in startingItems) {
                itemList += Item(item)
            }

            return itemList
        }

        private fun createMonkey(
            id: Int,
            startingItems: List<Item> = emptyList(),
            operation: (Long) -> Long,
            test: Long,
            trueMonkey: Int,
            falseMonkey: Int
        ): Monkey {
            val monkey = Monkey(id, startingItems)
            monkey.operation = operation
            monkey.addTestParameter(test, trueMonkey, falseMonkey)
            return monkey
        }
    }
}

private fun Long.shouldBe(expectation: Long) = Assertions.assertEquals(expectation, this)
private fun Collection<*>.shouldContainAll(expectation: Collection<*>) =
    Assertions.assertTrue(this.containsAll(expectation))

private fun Collection<*>.shouldContainAll(expectation: Collection<*>, message: String) =
    Assertions.assertTrue(this.containsAll(expectation), message)
