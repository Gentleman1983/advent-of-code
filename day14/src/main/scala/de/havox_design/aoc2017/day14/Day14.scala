package de.havox_design.aoc2017.day14

import de.havox_design.aoc2017.day10.Day10.*
import de.havox_design.aoc2017.day10.KnotState

type Grid[A] = Vector[Vector[A]]

object Day14 {
  def solvePart1(filename: String): Int =
    squaresUsed(readData(filename).next())

  def solvePart2(filename: String): String = {
    ""
  }

  def main(args: Array[String]): Unit = {
    def dayFileName = "day14.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  def squaresUsed(key: String): Int =
    hashGrid(key)
      .map(_.count(x => x))
      .sum

  def hashGrid(key: String): Grid[Boolean] = {
    (0 until 128)
      .map(row => bytes2bits(hashRow(key, row)))
      .toVector
  }

  def hashRow(key: String, row: Int): Seq[Byte] =
    knotHash(s"$key-$row")
      .map(_.toByte)

  def knotHash(input: String): Seq[Int] =
    transformSparseHashesToDenseHash(process(KnotState(), asciiLengths(input), 64).elements)

  def bytes2bits(bytes: Seq[Byte]): Vector[Boolean] =
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
