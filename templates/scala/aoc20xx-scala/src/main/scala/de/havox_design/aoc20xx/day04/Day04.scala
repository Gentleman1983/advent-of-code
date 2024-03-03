package de.havox_design.aoc2022.day04

object Day04 {
  def solvePart1(filename: String): Long = {
    1L
  }

  def solvePart2(filename: String): Long = {
    1L
  }

  def main(args: Array[String]): Unit = {
    println("Solution for part1: " + solvePart1("de/havox_design/aoc20xx/day04/day04.txt"))
    println("Solution for part2: " + solvePart2("de/havox_design/aoc20xx/day04/day04.txt"))
  }

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
