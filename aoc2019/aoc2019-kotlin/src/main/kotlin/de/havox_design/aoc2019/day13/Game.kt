package de.havox_design.aoc2019.day13

class Game {
    private var ball: Loc = Loc(0, 0)
    private var paddle: Loc = Loc(0, 0)
    private var score: Long = 0

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

            if (isScoreOutput(output1, output2))
                score = output3
            else if (output3.isBall())
                ball = Loc(output1.toInt(), output2.toInt())
            else if (output3.isPaddle())
                paddle = Loc(output1.toInt(), output2.toInt())
            c.resume()
        }
        return score.toInt()
    }

    private fun isScoreOutput(output1: Long, output2: Long) = output1 == -1L && output2 == 0L

    private fun joystick(counter: Int) = ball.x.compareTo(paddle.x).toLong()
}

fun Long.isBlock() = this == 2L
fun Long.isBall() = this == 4L
fun Long.isPaddle() = this == 3L
