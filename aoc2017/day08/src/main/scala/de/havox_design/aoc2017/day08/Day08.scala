package de.havox_design.aoc2017.day08

object Day08 {
  def solvePart1(filename: String): Int =
    compute(readData(filename)
      .toIndexedSeq)
      ._1
      .values
      .max

  def solvePart2(filename: String): Int =
    compute(readData(filename)
      .toIndexedSeq)
      ._2

  def main(args: Array[String]): Unit = {
    def dayFileName = "day08.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  def compute(input: Seq[String]): (Map[String, Int], Int) =
    val initial = Map
      .empty[String, Int]
      .withDefaultValue(0) -> 0

    input
      .map(_.split(" "))
      .foldLeft(initial) { case ((registers, max), instruction) =>
        val Array(destination, operation, amount, _, test, condition, threshold) = instruction
        val left = registers(test)
        val right = threshold
          .toInt
        val valid = condition match
          case ">" => left > right
          case "<" => left < right
          case ">=" => left >= right
          case "<=" => left <= right
          case "==" => left == right
          case "!=" => left != right

        val modifier = if !valid then {
          0
        }
        else {
          if operation != "inc" then {
            -1
          }
          else {
            1
          }
        }

        val next = registers(destination) + amount.toInt * modifier
        registers
          .updated(destination, next) -> max
          .max(next)
      }

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
