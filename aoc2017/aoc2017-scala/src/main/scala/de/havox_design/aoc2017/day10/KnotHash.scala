package de.havox_design.aoc2017.day10

object KnotHash {
  def solvePart1(filename: String, elements: Int = 256): Int =
    calculateProductOfFirstTwoElements(readData(filename).next(), elements)

  def solvePart2(filename: String): String = {
    val input = if (!readData(filename).hasNext) then {
      ""
    }
    else {
      readData(filename).next()
    }

    calculateKnotHash(input)
  }

  def main(args: Array[String]): Unit = {
    def dayFileName = "day10.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  def process(initialKnotState: KnotState, lengths: Seq[Int], rounds: Int = 1): KnotState =
    (0 until rounds).foldLeft(initialKnotState) { (knotState, round) =>
      lengths
        .foldLeft(knotState)(_.reversed(_))
    }

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

  private def calculateKnotHash(input: String): String =
    convertToHexString(transformSparseHashesToDenseHash(process(KnotState(), asciiLengths(input), 64).elements))

  def transformSparseHashesToDenseHash(sparse: Seq[Int]): Seq[Int] =
    sparse
      .grouped(16)
      .map(_.reduce(_ ^ _))
      .toSeq

  private def convertToHexString(dense: Seq[Int]): String =
    dense
      .map("%02x".format(_))
      .mkString

  def asciiLengths(input: String): Seq[Int] =
    input.map(_.toInt) ++ Seq(17, 31, 73, 47, 23)

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
