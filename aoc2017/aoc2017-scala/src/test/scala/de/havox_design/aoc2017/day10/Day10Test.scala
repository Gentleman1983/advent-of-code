package de.havox_design.aoc2017.day10

import KnotHash._
import org.scalatest.funsuite.AnyFunSuite

class Day10Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day10/day10.txt"

  test("Day 10 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 10 - Part 1 - Sample") {
    assert(solvePart1("de/havox_design/aoc2017/day10/day10Part1sample.txt", 5) == 12)
  }

  test("Day 10 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 10 - Part 2 - Sample 1") {
    assert(solvePart2("de/havox_design/aoc2017/day10/day10Part2sample1.txt") == "a2582a3a0e66e6e86e3812dcb672a272")
  }

  test("Day 10 - Part 2 - Sample 2") {
    assert(solvePart2("de/havox_design/aoc2017/day10/day10Part2sample2.txt") == "33efeb34ea91902bb2f59c9920caa6cd")
  }

  test("Day 10 - Part 2 - Sample 3") {
    assert(solvePart2("de/havox_design/aoc2017/day10/day10Part2sample3.txt") == "3efbe78a8d82f29979031a4aa0b16a9d")
  }

  test("Day 10 - Part 2 - Sample 4") {
    assert(solvePart2("de/havox_design/aoc2017/day10/day10Part2sample4.txt") == "63960835bcdc130f0b66d7ff4f6a5a8e")
  }
}
