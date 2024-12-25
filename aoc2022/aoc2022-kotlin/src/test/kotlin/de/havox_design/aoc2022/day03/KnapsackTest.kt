package de.havox_design.aoc2022.day03

import de.havox_design.aoc.utils.kotlin.helpers.tests.shouldBe
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

class KnapsackTest {
    @ParameterizedTest
    @CsvSource(
        "vJrwpWtwJgWrhcsFMMfFFhFp,p",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,L",
        "PmmdzqPrVvPwwTWBwg,P",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,v",
        "ttgJtRGJQctTZtZT,t",
        "CrZsJsPPZsGzwwsLwLmpwMDw,s"
    )
    fun testDuplicateByContent(knapsackContent: String, expectedDuplicateSymbol: String) {
        val expectedDuplicateItem = Item(expectedDuplicateSymbol)

        val duplicates: Set<Item> = Knapsack
            .getKnapsackForString(knapsackContent)
            .findDuplicateItems()

        Assertions.assertTrue(duplicates.size == 1)
        Assertions.assertTrue(duplicates.contains(expectedDuplicateItem))
    }

    @ParameterizedTest
    @CsvSource(
        "vJrwpWtwJgWrhcsFMMfFFhFp,16",
        "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL,38",
        "PmmdzqPrVvPwwTWBwg,42",
        "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn,22",
        "ttgJtRGJQctTZtZT,20",
        "CrZsJsPPZsGzwwsLwLmpwMDw,19"
    )
    fun testGetScoresByContent(knapsackContent: String, expectedScore: Int) =
        Knapsack
            .getKnapsackForString(knapsackContent)
            .calculateScoreOfDuplicateItems()
            .shouldBe(expectedScore)

    @ParameterizedTest
    @ValueSource(
        strings = [
            "vJrwpWtwJgWrhcsFMMfFFhFp",
            "jqHRNqRjqzjGDLGLrsFMfFZSrLrFZsSL",
            "PmmdzqPrVvPwwTWBwg",
            "wMqvLMZHhHMvwLHjbvcjnnSBnvTQFn",
            "ttgJtRGJQctTZtZT",
            "CrZsJsPPZsGzwwsLwLmpwMDw"
        ]
    )
    fun testDistinctItemsForKnapsack(data: String) {
        for (index in data.indices) {
            val currentLetter = Item(data.substring(index, index + 1))

            Assertions
                .assertTrue(
                    Knapsack
                        .getKnapsackForString(data)
                        .listDistinctItems()
                        .contains(currentLetter)
                )
        }
    }
}
