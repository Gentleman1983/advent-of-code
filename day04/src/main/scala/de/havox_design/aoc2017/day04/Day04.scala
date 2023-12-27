package de.havox_design.aoc2017.day04

object Day04 {
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

  private def readData(filename: String): Iterator[String] =
    scala.io.Source.fromResource(filename).getLines()
}
