package de.havox_design.aoc2017.day08

import IHeardYouLikeRegisters._
import org.scalatest.funsuite.AnyFunSuite

class Day08Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day08/day08.txt"

  test("Day 08 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 08 - Part 1 - Sample") {
    assert(solvePart1("de/havox_design/aoc2017/day08/day08Sample.txt") == 1)
  }

  test("Day 08 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 08 - Part 2 - Sample") {
    assert(solvePart2("de/havox_design/aoc2017/day08/day08Sample.txt") == 10)
  }
}
