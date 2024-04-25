package de.havox_design.aoc2020.day21

class AllergenAssessment(private var filename: String) {
    private val data = getResourceAsText(filename)

    @SuppressWarnings("kotlin:S6611")
    fun processPart1(): Any {
        val allIngredients = mutableListOf<String>()
        val dictionary = mutableMapOf<String, MutableSet<String>>()

        data
            .forEach { line ->
                val (ingredients, allergens) = buildRecipe(line)
                allIngredients.addAll(ingredients)

                allergens
                    .forEach {
                        dictionary.putIfAbsent(it, ingredients.toMutableSet())
                        dictionary[it]!!.removeIf { ingredient -> ingredient !in ingredients }
                    }
            }

        allIngredients.removeAll(dictionary.values.flatten())

        return allIngredients
            .size
    }

    @SuppressWarnings("kotlin:S6611")
    fun processPart2(): Any {
        val dictionary = mutableMapOf<String, MutableSet<String>>()

        data
            .forEach { line ->
            val (ingredients, allergens) = buildRecipe(line)

                allergens
                    .forEach {
                dictionary.putIfAbsent(it, ingredients.toMutableSet())
                dictionary[it]!!.removeIf { ingredient -> ingredient !in ingredients }
            }
        }

        val reversedDictionary = mutableMapOf<String, MutableSet<String>>()

        dictionary
            .entries
            .forEach { (allergen, possibleIngredients) ->
            possibleIngredients
                .forEach {
                reversedDictionary.putIfAbsent(it, mutableSetOf())
                reversedDictionary[it]!!.add(allergen)
            }
        }

        val solution = mutableMapOf<String, String>()

        while (reversedDictionary.any { (_, value) -> value.size == 1 }) {
            val (ingredient, possibleAllergens) = reversedDictionary
                .entries
                .first { (_, value) -> value.size == 1 }
            val allergen = possibleAllergens
                .first()

            solution[allergen] = ingredient
            reversedDictionary
                .remove(ingredient)
            reversedDictionary
                .values
                .forEach { it.remove(allergen) }
        }

        return solution
            .entries
            .sortedBy { it.key }
            .joinToString(separator = JOINER_ALLERGEN_INGREDIENT) { it.value }
    }

    private fun buildRecipe(it: String): Pair<Set<String>, Set<String>> {
        val matchResult = INGRIEDIENT_REGEX
            .matchEntire(it)
        val ingredients = matchResult!!
            .groupValues[ID_INGREDIENT]
            .split(DELIMITER_INGREDIENT)
            .map(String::trim)
            .toSet()
        val allergens = matchResult
            .groupValues[ID_ALLERGEN]
            .split(DELIMITER_ALLERGEN)
            .map(String::trim)
            .toSet()

        return Pair(ingredients, allergens)
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()

    companion object {
        private const val DELIMITER_ALLERGEN = ","
        private const val DELIMITER_INGREDIENT = " "
        private const val ID_ALLERGEN = 2
        private const val ID_INGREDIENT = 1
        private const val JOINER_ALLERGEN_INGREDIENT = ","

        private val INGRIEDIENT_REGEX = """([a-z ]+) \(contains ([a-z ,]+)\)""".toRegex()
    }
}
