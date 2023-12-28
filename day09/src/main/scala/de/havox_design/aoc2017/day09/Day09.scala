package de.havox_design.aoc2017.day09

object Day09 {
  def solvePart1(filename: String): Int =
    calculate(readData(filename).next().trim)
      .groupScore

  def solvePart2(filename: String): Int =
    0

  def main(args: Array[String]): Unit = {
    def dayFileName = "day07.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  private def calculate(input: String): State =
    val initial = State(1, 0, false, 0, false)

    input
      .foldLeft(initial)((state, next) => state
        .step(next))

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
