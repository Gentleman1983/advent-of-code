package de.havox_design.aoc2017.day06

import MemoryReallocation._
import org.scalatest.funsuite.AnyFunSuite

class Day06Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day06/day06.txt"

  test("Day 06 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 06 - Part 1 - Sample") {
    assert(solvePart1("de/havox_design/aoc2017/day06/day06Sample.txt") == 5)
  }

  test("Day 06 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 06 - Part 2 - Sample") {
    assert(solvePart2("de/havox_design/aoc2017/day06/day06Sample.txt") == 4)
  }
}
