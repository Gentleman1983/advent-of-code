package de.havox_design.aoc2017.day05

import AMazeOfTwistyTrampolinesAllAlike._
import org.scalatest.funsuite.AnyFunSuite

class Day05Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day05/day05.txt"

  test("Day 05 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 05 - Part 1 - Sample") {
    assert(solvePart1("de/havox_design/aoc2017/day05/day05Sample.txt") == 5)
  }

  test("Day 05 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 05 - Part 2 - Sample") {
    assert(solvePart2("de/havox_design/aoc2017/day05/day05Sample.txt") == 10)
  }
}
