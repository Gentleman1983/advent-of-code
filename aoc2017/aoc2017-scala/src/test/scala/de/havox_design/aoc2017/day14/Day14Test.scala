package de.havox_design.aoc2017.day14

import DiskDefragmentation._
import org.scalatest.funsuite.AnyFunSuite

class Day14Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day14/day14.txt"

  test("Day 14 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 14 - Part 1 - Sample") {
    assert(solvePart1("de/havox_design/aoc2017/day14/day14Sample.txt") == 8108)
  }

  test("Day 14 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 14 - Part 2 - Sample") {
    assert(solvePart2("de/havox_design/aoc2017/day14/day14Sample.txt") == 1242)
  }
}
