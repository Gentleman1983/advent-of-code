package de.havox_design.aoc2023.day19

data class Condition(
    val field: Char,
    val lessThen: Boolean,
    val value: Long,
    val successLabel: String
) {
    private val ICON_EXTREMELY_COOL_LOOKING = 'x'
    private val ICON_MUSICAL = 'm'
    private val ICON_AERODYNAMIC = 'a'
    private val ICON_SHINY = 's'

    fun test(assignment: Assignment): Boolean {
        val target = when (field) {
            ICON_EXTREMELY_COOL_LOOKING -> assignment.x
            ICON_MUSICAL -> assignment.m
            ICON_AERODYNAMIC -> assignment.a
            ICON_SHINY -> assignment.s
            else -> error(this)
        }
        return when (lessThen) {
            true -> target < value
            else -> target > value
        }
    }
}
