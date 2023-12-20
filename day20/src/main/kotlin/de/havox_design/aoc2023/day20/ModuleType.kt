package de.havox_design.aoc2023.day20

enum class ModuleType(
    val symbol: String,
    val processSignal: (high: Boolean, currentSignal: Boolean, inputs: Map<String, Boolean>) -> Pulse
) {
    FLIP_FLOP("%", { high, currentSignal, _ ->
        when (high) {
            true -> Pulse.NONE
            else -> when {
                currentSignal -> Pulse.LOW
                else -> Pulse.HIGH
            }
        }
    }),
    CONJUNCTION("&", { _, _, inputs ->
        when (inputs.values.all { it }) {
            true -> Pulse.LOW
            else -> Pulse.HIGH
        }
    }),
    NONE("", { _, _, _ -> Pulse.LOW });


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
