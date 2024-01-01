package de.havox_design.aoc2017.day05

object AMazeOfTwistyTrampolinesAllAlike {
  def solvePart1(filename: String): Int = {
    val initialState = OffsetState(parseData(filename))
    val iterator = new OffsetIterator(initialState)

    iterator.size
  }

  def solvePart2(filename: String): Int = {
    val initialState = OffsetState(parseData(filename), true)
    val iterator = new OffsetIterator(initialState)

    iterator.size
  }

  def main(args: Array[String]): Unit = {
    def dayFileName = "day05.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  private def parseData(filename: String): Seq[Int] =
    readData(filename)
      .map(_.toInt)
      .toIndexedSeq

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
