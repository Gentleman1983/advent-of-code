package de.havox_design.aoc2017.day07

object Day07 {
  def solvePart1(filename: String): String =
    parseTree(readData(filename).toIndexedSeq)
      .name

  def solvePart2(filename: String): Int =
    60

  def main(args: Array[String]): Unit = {
    def dayFileName = "day07.txt"

    println("Solution for part1: " + solvePart1(dayFileName))
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  private def parseTree(input: Seq[String]): Node =
    val regex = """(\w+) \((\d+)\).*""".r
    val weights = input
      .map { case regex(name, weight) => name -> weight.toInt }
      .toMap
    val edges = input
      .map { line =>
        val Array(name, _, children*) = line.split("[^\\d\\w]+"): @unchecked
        name -> children
      }
      .toMap

    def helper(name: String): Node =
      val children = edges(name)
        .map(helper)
      val total = weights(name) + children.map(_.total).sum
      val unbalanced = children
        .exists(_.total != children.head.total)

      Node(name, weights(name), total, unbalanced, children)

    val nonRoot = edges
      .values
      .flatten
      .toSet
    val root = edges
      .keys
      .filterNot(nonRoot.contains)
      .head

    helper(root)
  end parseTree

  private def readData(filename: String): Iterator[String] =
    scala
      .io
      .Source
      .fromResource(filename)
      .getLines()
}
