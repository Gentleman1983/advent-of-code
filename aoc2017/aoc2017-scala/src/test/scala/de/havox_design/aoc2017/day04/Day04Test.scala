package de.havox_design.aoc2017.day04

import HighEntropyPassphrases._
import org.scalatest.funsuite.AnyFunSuite

class Day04Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day04/day04.txt"

  test("Day 04 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 04 - Part 1 - Sample 1") {
    assert(solvePart1("de/havox_design/aoc2017/day04/day04Part1sample1.txt") == 1)
  }

  test("Day 04 - Part 1 - Sample 2") {
    assert(solvePart1("de/havox_design/aoc2017/day04/day04Part1sample2.txt") == 0)
  }

  test("Day 04 - Part 1 - Sample 3") {
    assert(solvePart1("de/havox_design/aoc2017/day04/day04Part1sample3.txt") == 1)
  }

  test("Day 04 - Part 1 - Sample 4") {
    assert(solvePart1("de/havox_design/aoc2017/day04/day04Part1sample4.txt") == 2)
  }

  test("Day 04 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 04 - Part 2 - Sample 1") {
    assert(solvePart2("de/havox_design/aoc2017/day04/day04Part2sample1.txt") == 1)
  }

  test("Day 04 - Part 2 - Sample 2") {
    assert(solvePart2("de/havox_design/aoc2017/day04/day04Part2sample2.txt") == 0)
  }

  test("Day 04 - Part 2 - Sample 3") {
    assert(solvePart2("de/havox_design/aoc2017/day04/day04Part2sample3.txt") == 1)
  }

  test("Day 04 - Part 2 - Sample 4") {
    assert(solvePart2("de/havox_design/aoc2017/day04/day04Part2sample4.txt") == 1)
  }

  test("Day 04 - Part 2 - Sample 5") {
    assert(solvePart2("de/havox_design/aoc2017/day04/day04Part2sample5.txt") == 0)
  }

  test("Day 04 - Part 2 - Sample 6") {
    assert(solvePart2("de/havox_design/aoc2017/day04/day04Part2sample6.txt") == 3)
  }
}
