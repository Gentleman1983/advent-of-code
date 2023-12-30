package de.havox_design.aoc2023.day22

class Day22(private var filename: String) {
    private val ID_BRICK_END_X = 3
    private val ID_BRICK_END_Y = 4
    private val ID_BRICK_END_Z = 5
    private val ID_BRICK_START_X = 0
    private val ID_BRICK_START_Y = 1
    private val ID_BRICK_START_Z = 2

    private val number = Regex("-?\\d+")

    private var results: Pair<Long, Long>? = null

    fun solvePart1(): Long {
        calculateResults()
        return results!!.first
    }

    fun solvePart2(): Long {
        calculateResults()
        return results!!.second
    }

    @SuppressWarnings("kotlin:S3776")
    private fun calculateResults() {
        if (results != null) {
            return
        }

        bricks.clear()

        for (row in getResourceAsText(filename)) {
            val numbers = number
                .findAll(row)
                .map { it.value.toInt() }
                .toList()

            bricks.add(
                Brick(
                    Triple(numbers[ID_BRICK_START_X], numbers[ID_BRICK_START_Y], numbers[ID_BRICK_START_Z]),
                    Triple(numbers[ID_BRICK_END_X], numbers[ID_BRICK_END_Y], numbers[ID_BRICK_END_Z])
                )
            )
        }

        bricks
            .sortBy { it.startPosition.third }

        for (i in 0..<bricks.size) {
            while (bricks[i].canFall) {
                bricks[i] = bricks[i].fallen
            }
        }

        var countToBeAbleToBeDisintegrated = 0L
        var countCollapsingBricks = 0L

        for (i in 0..<bricks.size) {
            val save = bricks[i]
            bricks[i] = Brick(
                Triple(-1, -1, -1),
                Triple(-1, -1, -1)
            )

            if (bricks.all { !it.canFall }) {
                countToBeAbleToBeDisintegrated++
            } else {
                val fallen = ArrayList(bricks)

                for (j in (i + 1)..<fallen.size) {
                    when {
                        fallen[j].canFall(fallen) -> {
                            countCollapsingBricks++

                            while (fallen[j].canFall(fallen)) {
                                fallen[j] = fallen[j].fallen
                            }
                        }
                    }
                }
            }

            bricks[i] = save
        }

        results = Pair(countToBeAbleToBeDisintegrated, countCollapsingBricks)
    }

    private fun getResourceAsText(path: String): List<String> =
        this.javaClass.classLoader.getResourceAsStream(path)!!.bufferedReader().readLines()

    companion object {
        private val bricks = ArrayList<Brick>()

        fun getBricks(): ArrayList<Brick> =
            bricks
    }
}