package de.havox_design.aoc2017.day09

import StreamProcessing._
import org.scalatest.funsuite.AnyFunSuite

class Day09Test extends AnyFunSuite {
  def dayFileName = "de/havox_design/aoc2017/day09/day09.txt"

  test("Day 09 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 09 - Part 1 - Sample 1") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample1.txt") == 1)
  }

  test("Day 09 - Part 1 - Sample 2") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample2.txt") == 6)
  }

  test("Day 09 - Part 1 - Sample 3") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample3.txt") == 5)
  }

  test("Day 09 - Part 1 - Sample 4") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample4.txt") == 16)
  }

  test("Day 09 - Part 1 - Sample 5") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample5.txt") == 1)
  }

  test("Day 09 - Part 1 - Sample 6") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample6.txt") == 9)
  }

  test("Day 09 - Part 1 - Sample 7") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample7.txt") == 9)
  }

  test("Day 09 - Part 1 - Sample 8") {
    assert(solvePart1("de/havox_design/aoc2017/day09/day09Part1sample8.txt") == 3)
  }

  test("Day 09 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 09 - Part 2 - Sample 1") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample1.txt") == 0)
  }

  test("Day 09 - Part 2 - Sample 2") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample2.txt") == 17)
  }

  test("Day 09 - Part 2 - Sample 3") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample3.txt") == 3)
  }

  test("Day 09 - Part 2 - Sample 4") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample4.txt") == 2)
  }

  test("Day 09 - Part 2 - Sample 5") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample5.txt") == 0)
  }

  test("Day 09 - Part 2 - Sample 6") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample6.txt") == 0)
  }

  test("Day 09 - Part 2 - Sample 7") {
    assert(solvePart2("de/havox_design/aoc2017/day09/day09Part2sample7.txt") == 10)
  }
}
