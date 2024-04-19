package de.havox_design.aoc2020.day02

class PasswordPhilosophy(private var filename: String) {
    private val data = getResourceAsText(filename)

    fun processPart1(): Any =
        data
            .count { passwordPolicyAMatches(it) }

    fun processPart2(): Any =
        data
            .count { passwordPolicyBMatches(it) }

    private fun passwordPolicyAMatches(it: String): Boolean {
        val (policyMin, policyMax, letter, _, password) = it
            .split(' ', ':', '-')
            .map { it.trim() }
        val letterCount = password
            .count { it == letter[0] }

        return letterCount >= policyMin.toInt() && letterCount <= policyMax.toInt();
    }

    private fun passwordPolicyBMatches(it: String): Boolean {
        val (policyFirst, policyLast, letter, _, password) = it
            .split(' ', ':', '-')
            .map { it.trim() }

        return (password[policyFirst.toInt() - 1] == letter[0]) xor (password[policyLast.toInt() - 1] == letter[0])
    }

    private fun getResourceAsText(path: String): List<String> =
        this
            .javaClass
            .classLoader
            .getResourceAsStream(path)!!
            .bufferedReader()
            .readLines()
}
