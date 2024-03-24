package de.havox_design.aoc2019.day13

data class ComputerState(
    var counter: Int = 0,
    var inputCounter: Int = 0,
    var relativeAddressBase: Int = 0,
    var isReady: Boolean = false,
    var outputs: MutableList<Long> = mutableListOf(),
    var pause: Boolean = false
) {
    fun increaseCounter(c: Int) {
        counter += c
    }

    fun increaseInputCounter() {
        inputCounter += 1
    }

    fun adjustRelativeAddress(offset: Int) {
        relativeAddressBase += offset
    }

    fun addOutput(value: Long) {
        outputs.add(value)
    }
}
