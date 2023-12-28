package de.havox_design.aoc2017.day10

object Day10 {
  def solvePart1(filename: String, elements: Int = 256): Int =
    calculateProductOfFirstTwoElements(readData(filename).next(), elements)

  def solvePart2(filename: String): String =
    ""

  def main(args: Array[String]): Unit = {
    def dayFileName = "day07.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  private def process(initialKnotState: KnotState, lengths: Seq[Int]): KnotState =
    lengths
      .foldLeft(initialKnotState)(_.reversed(_))

  private def calculateProductOfFirstTwoElements(input: String, elements: Int = 256): Int = {
    val lengths = input
      .trim
      .split(",")
      .toSeq
      .map(
        _
          .trim
          .toInt
      )

    process(KnotState(Vector.range(0, elements)), lengths)
      .checkProduct
  }

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
