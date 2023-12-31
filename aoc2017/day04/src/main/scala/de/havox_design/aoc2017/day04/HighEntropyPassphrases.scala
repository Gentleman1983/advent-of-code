package de.havox_design.aoc2017.day04

object HighEntropyPassphrases {
  def solvePart1(filename: String): Int =
    readData(filename)
      .count { row =>
        val rowElements = row
          .split(" ")
        rowElements.distinct.length == rowElements.length
      }

  def solvePart2(filename: String): Int =
    readData(filename)
      .count { row =>
        val rowElements = row
          .split(" ")
          .toList
          .map(
            _
              .toList
              .sorted
              .mkString
          )

        rowElements.distinct.length == rowElements.length
      }

  def main(args: Array[String]): Unit = {
    def dayFileName = "day04.txt"

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
