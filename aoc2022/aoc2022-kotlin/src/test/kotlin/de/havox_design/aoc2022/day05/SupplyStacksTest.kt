package de.havox_design.aoc2022.day05

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldContainAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll
import kotlin.test.assertNotNull

class SupplyStacksTest {
    @Test
    fun testReadStacks() {
        val expectedData = getDataForTestReadStacks()
        val objectUnderTest = SupplyStacks("de/havox_design/aoc2022/day05/day05Sample.txt")

        objectUnderTest.readData()

        assertAll(
            { assertNotNull(objectUnderTest.data) },
            // Contains all stacks
            { objectUnderTest.data.keys.shouldContainAll(expectedData.keys) },
            // Contains all elements
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    objectUnderTest.data[index]!!.stack.shouldContainAll(expectedStack)
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    val objectUnderTestStack = objectUnderTest.data[index]!!.stack

                    for (i in expectedStack.indices) {
                        objectUnderTestStack[i].shouldBe(expectedStack[i])
                    }
                }
            }
        )
    }

    @Test
    fun testReadProcedures() {
        val objectUnderTest = SupplyStacks("de/havox_design/aoc2022/day05/day05Sample.txt")

        objectUnderTest.readData()
        val expectedData = getDataForTestReadProcedures(objectUnderTest.data)

        assertAll(
            { assertNotNull(objectUnderTest.procedure) },
            // All procedure steps in correct order
            {
                for (index in expectedData.indices) {
                    objectUnderTest.procedure[index].shouldBe(expectedData[index])
                }
            }
        )
    }

    @Test
    fun testRunProcedure() {
        val expectedData = getDataForTestRunProcedure()
        val objectUnderTest = SupplyStacks("de/havox_design/aoc2022/day05/day05Sample.txt")

        objectUnderTest.readData()
        objectUnderTest.followProcedure()

        assertAll(
            { assertNotNull(objectUnderTest.data) },
            { assertNotNull(objectUnderTest.procedure) },
            // All procedure steps in correct order
            // Contains all stacks
            { objectUnderTest.data.keys.shouldContainAll(expectedData.keys) },
            // Contains all elements
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    objectUnderTest.data[index]!!.stack.shouldContainAll(expectedStack)
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    val objectUnderTestStack = objectUnderTest.data[index]!!.stack

                    for (i in expectedStack.indices) {
                        objectUnderTestStack[i].shouldBe(expectedStack[i])
                    }
                }
            }
        )
    }

    @Test
    fun testRunProcedureCratemaster9001() {
        val expectedData = getDataForTestRunProcedureCratemaster9001()
        val objectUnderTest = SupplyStacks("de/havox_design/aoc2022/day05/day05Sample.txt")

        objectUnderTest.readData()
        objectUnderTest.followProcedureCratemaster9001()

        assertAll(
            { assertNotNull(objectUnderTest.data) },
            { assertNotNull(objectUnderTest.procedure) },
            // All procedure steps in correct order
            // Contains all stacks
            { objectUnderTest.data.keys.shouldContainAll(expectedData.keys) },
            // Contains all elements
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    objectUnderTest.data[index]!!.stack.shouldContainAll(expectedStack)
                }
            },
            // Contains all elements in the same order
            {
                for (index in expectedData.keys) {
                    val expectedStack = expectedData[index]!!.stack
                    val objectUnderTestStack = objectUnderTest.data[index]!!.stack

                    for (i in expectedStack.indices) {
                        objectUnderTestStack[i].shouldBe(expectedStack[i])
                    }
                }
            }
        )
    }

    @Test
    fun testSolutionPart1() =
        SupplyStacks("de/havox_design/aoc2022/day05/day05Sample.txt").evaluateTask1().shouldBe("CMZ")

    @Test
    fun testSolutionPart2() =
        SupplyStacks("de/havox_design/aoc2022/day05/day05Sample.txt").evaluateTask2().shouldBe("MCD")

    private fun getDataForTestReadStacks(): Map<Int, Stack> {
        val stack1 = Stack.emptyStackWithId(1)
        stack1.stack += Crate("Z")
        stack1.stack += Crate("N")

        val stack2 = Stack.emptyStackWithId(2)
        stack2.stack += Crate("M")
        stack2.stack += Crate("C")
        stack2.stack += Crate("D")

        val stack3 = Stack.emptyStackWithId(3)
        stack3.stack += Crate("P")

        val testData = emptyMap<Int, Stack>().toMutableMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }

    private fun getDataForTestReadProcedures(data: Map<Int, Stack>): List<Step> {
        val proc = emptyList<Step>().toMutableList()

        proc += Step(1, data[2]!!, data[1]!!)
        proc += Step(3, data[1]!!, data[3]!!)
        proc += Step(2, data[2]!!, data[1]!!)
        proc += Step(1, data[1]!!, data[2]!!)

        return proc
    }

    private fun getDataForTestRunProcedure(): Map<Int, Stack> {
        val stack1 = Stack.emptyStackWithId(1)
        stack1.stack += Crate("C")

        val stack2 = Stack.emptyStackWithId(2)
        stack2.stack += Crate("M")

        val stack3 = Stack.emptyStackWithId(3)
        stack3.stack += Crate("P")
        stack3.stack += Crate("D")
        stack3.stack += Crate("N")
        stack3.stack += Crate("Z")

        val testData = emptyMap<Int, Stack>().toMutableMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }

    private fun getDataForTestRunProcedureCratemaster9001(): Map<Int, Stack> {
        val stack1 = Stack.emptyStackWithId(1)
        stack1.stack += Crate("M")

        val stack2 = Stack.emptyStackWithId(2)
        stack2.stack += Crate("C")

        val stack3 = Stack.emptyStackWithId(3)
        stack3.stack += Crate("P")
        stack3.stack += Crate("Z")
        stack3.stack += Crate("N")
        stack3.stack += Crate("D")

        val testData = emptyMap<Int, Stack>().toMutableMap()
        testData += Pair(1, stack1)
        testData += Pair(2, stack2)
        testData += Pair(3, stack3)

        return testData
    }
}
