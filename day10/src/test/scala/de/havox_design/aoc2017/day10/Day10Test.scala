package de.havox_design.aoc2017.day10

import Day10._
import org.scalatest.funsuite.AnyFunSuite

class Day10Test extends AnyFunSuite {
  def dayFileName = "day10.txt"

  test("Day 10 - Part 1") {
    println("Solution for part1: " + solvePart1(dayFileName))
  }

  test("Day 10 - Part 1 - Sample") {
    assert(solvePart1("part1sample.txt", 5) == 12)
  }

  test("Day 10 - Part 2") {
    println("Solution for part2: " + solvePart2(dayFileName))
  }

  test("Day 10 - Part 2 - Sample") {
    assert(solvePart2("sample.txt") == 0)
  }
}
