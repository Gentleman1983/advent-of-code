package de.havox_design.aoc2020.day02

class PasswordPhilosophy(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .count { passwordPolicyAMatches(it) }

    fun processPart2(): Any =
        0L

    private fun passwordPolicyAMatches(it: String): Boolean {
        val (policyMin, policyMax, letter, _, password) = it
            .split(' ', ':', '-')
            .map { it.trim() }
        val letterCount = password
            .count { it == letter[0] }

        return letterCount >= policyMin.toInt() && letterCount <= policyMax.toInt();
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
