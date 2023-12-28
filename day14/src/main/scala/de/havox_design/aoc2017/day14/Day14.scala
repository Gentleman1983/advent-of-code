package de.havox_design.aoc2017.day14

import de.havox_design.aoc2017.day10.Day10.*
import de.havox_design.aoc2017.day10.KnotState

type Grid[A] = Vector[Vector[A]]

object Day14 {
  def solvePart1(filename: String): Int =
    squaresUsed(readData(filename).next())

  def solvePart2(filename: String): Int =
    countRegions(readData(filename).next())

  def main(args: Array[String]): Unit = {
    def dayFileName = "day14.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part1: " + solvePart2(dayFileName))
  }

  private def squaresUsed(input: String): Int = {
    hashGrid(input)
      .map(_.count(x => x))
      .sum
  }

  private def countRegions(input: String): Int = {
    val points = binary(input)
      .zipWithIndex
      .flatMap { case (row, y) =>
      for x <- 0 to 127 if row(x) == '1' yield Point(x, y)
    }
    
    val cliques = points
      .foldLeft(Set.empty[Set[Point]]) { (groups, point) =>
      val (other, linked) = groups
        .partition(_.intersect(point.neighbours).isEmpty)
      
        other + (linked.flatten + point)
    }
    
    cliques
      .size
  }

  private def binary(input: String): Seq[String] =
    (0 to 127)
      .map(index => 
        knotHash(s"$input-$index")
        .map(s => "%08d".format(s.toBinaryString.toInt))
        .mkString
      )

  private def hashGrid(input: String): Grid[Boolean] = {
    (0 until 128)
      .map(row => bytes2bits(hashRow(input, row)))
      .toVector
  }

  private def hashRow(input: String, row: Int): Seq[Byte] =
    knotHash(s"$input-$row")
      .map(_.toByte)

  private def knotHash(input: String): Seq[Int] =
    transformSparseHashesToDenseHash(process(KnotState(), asciiLengths(input), 64).elements)

  private def bytes2bits(bytes: Seq[Byte]): Vector[Boolean] =
    bytes
      .flatMap(byte =>
        (0 until 8)
          .foldLeft(Vector.empty[Boolean])((acc, i) =>
            (((byte >> i) & 1) != 0) +: acc
          )
      )
      .toVector

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
