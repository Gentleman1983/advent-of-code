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

    fun processPart2(): Any =
        0L

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

        private val INGRIEDIENT_REGEX = """([a-z ]+) \(contains ([a-z ,]+)\)""".toRegex()
    }
}
