package de.havox_design.aoc2023.day15

data class Box(val code: Int, val lenses: MutableList<Lens> = mutableListOf()) {
    fun add(lens: Lens) {
        this
            .lenses
            .firstOrNull { it.label == lens.label }
            ?.also { it.focalLength = lens.focalLength } ?: this.lenses.addLast(lens)
    }
    fun remove(label: String) {
        this
            .lenses
            .removeIf { it.label == label }
    }
}
