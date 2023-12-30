package de.havox_design.aoc2023.day20

data class Module(val name: String, val type: ModuleType, val connections: List<String>) {
    companion object {
        val ICON_BROADCASTER = "broadcaster"
        val ICON_BUTTON = "button"
        val ICON_CONJUCTION = "&"
        val ICON_FLIP_FLOP = "%"
        val ICON_NONE = ""
        val ICON_RECEIVER = "rx"
    }
}
