package de.havox_design.aoc2020.day04

import de.havox_design.aoc2020.day04.PassportFields.Companion.ICON_CENTIMETERS
import de.havox_design.aoc2020.day04.PassportFields.Companion.ICON_INCHES
import de.havox_design.aoc2020.day04.PassportFields.Companion.REGEX_HAIR_COLOR
import de.havox_design.aoc2020.day04.PassportFields.Companion.REGEX_PASSPORT_ID

enum class PassportFields(val key: String, val isValid: (String) -> Boolean) {
    BIRTH_YEAR("byr", { byr -> byr.toInt() in 1920..2002 }),
    ISSUE_YEAR("iyr", { iyr -> iyr.toInt() in 2010..2020 }),
    EXPIRATION_YEAR("eyr", { eyr -> eyr.toInt() in 2020..2030 }),
    HEIGHT("hgt", { hgt -> isHeightValid(hgt) }),
    HAIR_COLOR("hcl", { hcl -> isHairColorValid(hcl) }),
    EYE_COLOR("ecl", { ecl -> ecl in PassportEyeColors.entries.map { color -> color.key }.toSet() }),
    PASSPORT_ID("pid", { pid -> isPassportIdValid(pid) }),
    COUNTRY_ID("cid", { true });

    companion object {
        const val ICON_CENTIMETERS = "cm"
        const val ICON_INCHES = "in"
        val REGEX_HAIR_COLOR = "#[0-9a-f]{6}"
            .toRegex()
        val REGEX_PASSPORT_ID = "\\d{9}"
            .toRegex()
    }
}

private fun isHairColorValid(value: String) =
    value
        .matches(REGEX_HAIR_COLOR)

private fun isHeightValid(value: String): Boolean =
    if (value.endsWith(ICON_CENTIMETERS)) {
        isHeightValidCentimeters(value)
    } else if (value.endsWith(ICON_INCHES)) {
        isHeightValidInches(value)
    } else {
        false
    }

private fun isHeightValidCentimeters(value: String) =
    value
        .substringBefore(ICON_CENTIMETERS).toInt() in 150..193

private fun isHeightValidInches(value: String) =
    value
        .substringBefore(ICON_INCHES).toInt() in 59..76

private fun isPassportIdValid(value: String) =
    value
        .matches(REGEX_PASSPORT_ID)
