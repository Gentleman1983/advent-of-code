package de.havox_design.aoc2023.day19

data class RangeAssignment(
    val xMin: Long = 1L,
    val xMax: Long = 4000L,
    val mMin: Long = 1L,
    val mMax: Long = 4000L,
    val aMin: Long = 1L,
    val aMax: Long = 4000L,
    val sMin: Long = 1L,
    val sMax: Long = 4000L
) {
    private val ICON_EXTREMELY_COOL_LOOKING = 'x'
    private val ICON_MUSICAL = 'm'
    private val ICON_AERODYNAMIC = 'a'
    private val ICON_SHINY = 's'

    fun split(condition: Condition): Pair<RangeAssignment, RangeAssignment> =
        when (condition.field) {
            ICON_EXTREMELY_COOL_LOOKING -> when (condition.lessThen) {
                true -> copy(xMax = condition.value - 1) to copy(xMin = condition.value)
                else -> copy(xMin = condition.value + 1) to copy(xMax = condition.value)
            }

            ICON_MUSICAL -> when (condition.lessThen) {
                true -> copy(mMax = condition.value - 1) to copy(mMin = condition.value)
                else -> copy(mMin = condition.value + 1) to copy(mMax = condition.value)
            }

            ICON_AERODYNAMIC -> when (condition.lessThen) {
                true -> copy(aMax = condition.value - 1) to copy(aMin = condition.value)
                else -> copy(aMin = condition.value + 1) to copy(aMax = condition.value)
            }

            ICON_SHINY -> when (condition.lessThen) {
                true -> copy(sMax = condition.value - 1) to copy(sMin = condition.value)
                else -> copy(sMin = condition.value + 1) to copy(sMax = condition.value)
            }

            else -> error("Invalid state")
        }


    fun isValid() =
        xMin <= xMax
                && mMin <= mMax
                && aMin <= aMax
                && sMin <= sMax

    fun sum() =
        (xMax - xMin + 1) * (mMax - mMin + 1) * (aMax - aMin + 1) * (sMax - sMin + 1)
}
