package de.havox_design.aoc2023.day20

import de.havox_design.aoc2023.day20.Module.Companion.ICON_CONJUCTION
import de.havox_design.aoc2023.day20.Module.Companion.ICON_FLIP_FLOP
import de.havox_design.aoc2023.day20.Module.Companion.ICON_NONE

enum class ModuleType(
    val symbol: String,
    val processSignal: (high: Boolean, currentSignal: Boolean, inputs: Map<String, Boolean>) -> Pulse
) {
    FLIP_FLOP(ICON_FLIP_FLOP, { high, currentSignal, _ ->
        when (high) {
            true -> Pulse.NONE
            else -> when {
                currentSignal -> Pulse.LOW
                else -> Pulse.HIGH
            }
        }
    }),
    CONJUNCTION(ICON_CONJUCTION, { _, _, inputs ->
        when (inputs.values.all { it }) {
            true -> Pulse.LOW
            else -> Pulse.HIGH
        }
    }),
    NONE(ICON_NONE, { _, _, _ -> Pulse.LOW });

    companion object {
        fun processSignal(
            moduleType: ModuleType,
            high: Boolean,
            currentSignal: Boolean,
            inputs: Map<String, Boolean>
        ): Pulse =
            moduleType.processSignal(high, currentSignal, inputs)

        fun parseModule(name: String): Pair<ModuleType, String> {
            val type = entries
                .first { name.startsWith(it.symbol) }

            return type to name.drop(type.symbol.length)
        }
    }
}
