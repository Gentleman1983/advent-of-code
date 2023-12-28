package de.havox_design.aoc2017.day07

object Day07 {
  def solvePart1(filename: String): Int =
    0

  def solvePart2(filename: String): Int =
    0

  def main(args: Array[String]): Unit = {
    def dayFileName = "day07.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
