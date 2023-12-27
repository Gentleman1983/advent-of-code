package de.havox_design.aoc2017.day04

object Day04 {
  def solvePart1(filename: String): Int =
    readData(filename)
      .count{ row =>
        val rowElements = row.split(" ")
        rowElements.distinct.length == rowElements.length
      }

  def solvePart2(filename: String): Int =
    0

  def readData(filename: String): Iterator[String] =
    scala.io.Source.fromResource(filename).getLines()
}
