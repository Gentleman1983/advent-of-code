package de.havox_design.aoc2015.day16

data class AuntSueDescription(val id: Int, val properties: Map<String, Int>) {
    companion object {
        fun from(line: String): AuntSueDescription {
            val id = line.substringBefore(':').substringAfter("Sue ").toInt()
            val properties = line.substringAfter(": ")
                .split(", ")
                .associate { property ->
                    property
                        .split(": ", limit = 2)
                        .let { it.first() to it.last().toInt() }
                }
            return AuntSueDescription(id, properties)
        }
    }
}
