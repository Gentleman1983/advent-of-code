package de.havox_design.aoc2017.day06

import scala.annotation.tailrec

object Day06 {
  def solvePart1(filename: String): Int =
    findCycle(parseData(filename))

  def solvePart2(filename: String): Int =
    0

  def main(args: Array[String]): Unit = {
    def dayFileName = "day06.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  private def findCycle(data: Seq[Int]): Int =
    calculateCycle(data, Map(), 0)

  @tailrec
  private def calculateCycle(current: Seq[Int], previous: Map[Seq[Int], Int], depth: Int): Int =
    if (previous.contains(current)) {
      depth
    }
    else {
      calculateCycle(calculateStep(current), previous.updated(current, depth), depth + 1)
    }

  private def calculateStep(banks: Seq[Int]): Seq[Int] = {
    val max = banks
      .max
    val start = banks
      .indexWhere(_ == max)
    (1 to max)
      .foldLeft(banks.updated(start, 0)) { (banks, offset) =>
        val index = (start + offset) % banks.size
        banks
          .updated(index, banks(index) + 1)
      }
  }

  private def parseData(filename: String): IndexedSeq[Int] = {
    readData(filename)
      .next()
      .split("\\s+")
      .map(_.trim)
      .toIndexedSeq
      .map(_.toInt)
  }

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
