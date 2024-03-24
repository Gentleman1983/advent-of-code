package de.havox_design.aoc2019.day13

import de.havox_design.aoc.utils.kotlin.model.positions.Position2d

class Game {
    private var ball = Position2d(0, 0)
    private var paddle = Position2d(0, 0)
    private var score = 0L

    fun play(program: Intcode): Int {
        program[0] = 2
        val c = Computer(program, inputSupplier = ::joystick, resumeOnOutput = true)
        c.execute()
        while (!c.isReady()) {
            val output1 = c.output()
            c.resume()
            val output2 = c.output()
            c.resume()
            val output3 = c.output()

            when {
                isScoreOutput(output1, output2) -> score = output3
                output3.isBall() -> ball = Position2d(output1.toInt(), output2.toInt())
                output3.isPaddle() -> paddle = Position2d(output1.toInt(), output2.toInt())
            }

            c.resume()
        }
        return score.toInt()
    }

    private fun isScoreOutput(output1: Long, output2: Long) = output1 == -1L && output2 == 0L

    @SuppressWarnings("kotlin:S1172")
    private fun joystick(counter: Int) = ball.x.compareTo(paddle.x).toLong()
}

fun Long.isBlock() = this == 2L
fun Long.isBall() = this == 4L
fun Long.isPaddle() = this == 3L
